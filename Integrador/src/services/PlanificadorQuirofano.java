package services;

import models.SolicitudCirugia;
import structures.MinHeap;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio de Planificación de Quirófano (Ejercicio 10)
 * Asigna cirugías al quirófano con menor tiempo libre usando Min-Heap
 * Mantiene top-K médicos más bloqueados
 * Complejidad: O(log Q + log K) por asignación
 */

public class PlanificadorQuirofano {
    
    private static class Quirofano implements Comparable<Quirofano> {
        String id;
        LocalDateTime proximoLibre;
        
        Quirofano(String id, LocalDateTime proximoLibre) {
            this.id = id;
            this.proximoLibre = proximoLibre;
        }

        @Override
        public int compareTo(Quirofano otro) {
            return this.proximoLibre.compareTo(otro.proximoLibre);
        }

        @Override
        public String toString() {
            return id + " (libre a las " + 
                   proximoLibre.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM HH:mm")) + " hs)";
        }
    }
    
    private static class MedicoBloqueado implements Comparable<MedicoBloqueado> {
        String matricula;
        String nombre;
        int minutosAcumulados;
        
        MedicoBloqueado(String matricula, String nombre, int minutos) {
            this.matricula = matricula;
            this.nombre = nombre;
            this.minutosAcumulados = minutos;
        }

        @Override
        public int compareTo(MedicoBloqueado otro) {
            // Para max-heap, invertimos el orden
            return Integer.compare(otro.minutosAcumulados, this.minutosAcumulados);
        }

        @Override
        public String toString() {
            return String.format("%s - %s - %d hs bloqueadas", 
                               matricula, nombre, minutosAcumulados / 60);
        }
    }

    private MinHeap<Quirofano> heapQuirofanos;
    private Map<String, Integer> tiempoPorMedico;
    private int K; // Top-K médicos más ocupados

    public PlanificadorQuirofano(List<String> quirofanos, int K, LocalDateTime horaInicio) {
        this.heapQuirofanos = new MinHeap<>();
        this.tiempoPorMedico = new HashMap<>();
        this.K = K;
        
        // Inicializar quirófanos
        for (String id : quirofanos) {
            heapQuirofanos.push(new Quirofano(id, horaInicio));
        }
    }

    /**
     * Procesa una solicitud de cirugía
     * Asigna al quirófano que esté libre más temprano
     * Complejidad: O(log Q)
     */
    public String procesarSolicitud(SolicitudCirugia solicitud) {
        if (heapQuirofanos.isEmpty()) {
            return null;
        }

        // Obtener quirófano con menor tiempo libre
        Quirofano quirofano = heapQuirofanos.pop();
        
        // Asignar cirugía
        LocalDateTime horaAsignacion = quirofano.proximoLibre.isBefore(LocalDateTime.now()) 
                                      ? LocalDateTime.now() 
                                      : quirofano.proximoLibre;
        
        // Actualizar próximo tiempo libre
        quirofano.proximoLibre = horaAsignacion.plusMinutes(solicitud.getDuracionMin());
        
        // Reinsertar en heap
        heapQuirofanos.push(quirofano);
        
        // Actualizar tiempo del médico
        tiempoPorMedico.merge(solicitud.getMatriculaMedico(), 
                             solicitud.getDuracionMin(), 
                             Integer::sum);
        
        System.out.println( solicitud + " -> Asignado a " + quirofano.id + 
                         " a las " + horaAsignacion.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM HH:mm")) + " hs");
        
        return quirofano.id;
    }

    /**
     * Obtiene los top-K médicos más bloqueados
     * Complejidad: O(M log K) donde M = total de médicos
     */
    public List<MedicoBloqueado> topKMedicosBloqueados(Map<String, String> matriculaANombre) {
        // Crear heap de los K más ocupados (usando MinHeap manual)
        MinHeap<MedicoBloqueado> topK = new MinHeap<>();
        
        for (Map.Entry<String, Integer> entry : tiempoPorMedico.entrySet()) {
            String matricula = entry.getKey();
            int minutos = entry.getValue();
            String nombre = matriculaANombre.getOrDefault(matricula, "Desconocido");
            
            MedicoBloqueado medico = new MedicoBloqueado(matricula, nombre, minutos);
            
            if (topK.size() < K) {
                topK.push(medico);
            } else if (topK.peek() != null && minutos > topK.peek().minutosAcumulados) {
                topK.pop();
                topK.push(medico);
            }
        }
        
        // Convertir a lista ordenada descendentemente
        List<MedicoBloqueado> resultado = new ArrayList<>(topK.getElementos());
        resultado.sort((a, b) -> Integer.compare(b.minutosAcumulados, a.minutosAcumulados));
        
        return resultado;
    }

    /**
     * Muestra el estado actual
     */
    public void mostrarEstado(Map<String, String> matriculaANombre) {
        System.out.println("\n============================================");
        System.out.println("PLANIFICADOR DE QUIRÓFANO (MinHeap + Top-K)");
        System.out.println("============================================\n");
        
        System.out.println("Quirófanos disponibles: " + heapQuirofanos.size());
        
        if (!heapQuirofanos.isEmpty()) {
            System.out.println("\nPróximo quirófano libre:");
            System.out.println("  " + heapQuirofanos.peek());
        }
        
        System.out.println("\nTop " + K + " médicos más ocupados:");
        List<MedicoBloqueado> topK = topKMedicosBloqueados(matriculaANombre);
        
        for (int i = 0; i < topK.size(); i++) {
            System.out.println("  " + (i + 1) + ") " + topK.get(i));
        }

        System.out.println("========================================\n");
    }
}
