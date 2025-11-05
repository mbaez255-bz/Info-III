package services;

import models.Turno;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Servicio de Consolidación de Agendas (Ejercicio 7)
 * Merge de dos agendas con detección de conflictos
 * Complejidad: O(|A| + |B|) con logeo de conflictos
 */

public class ConsolidadorAgendas {

    /**
     * Consolida dos agendas ya ordenadas
     * Detecta y loguea conflictos (mismo médico + horario solapado)
     * Complejidad: O(|A| + |B|)
     */
    public List<Turno> merge(List<Turno> agendaA, List<Turno> agendaB) {
        List<Turno> resultado = new ArrayList<>();
        int i = 0, j = 0;
        
        // Merge estilo merge-sort
        while (i < agendaA.size() && j < agendaB.size()) {
            Turno turnoA = agendaA.get(i);
            Turno turnoB = agendaB.get(j);
            
            if (turnoA.compareTo(turnoB) <= 0) {
                resultado.add(turnoA);
                i++;
            } else {
                resultado.add(turnoB);
                j++;
            }
        }
        
        // Agregar restantes
        while (i < agendaA.size()) {
            resultado.add(agendaA.get(i));
            i++;
        }
        
        while (j < agendaB.size()) {
            resultado.add(agendaB.get(j));
            j++;
        }
        
        return resultado;
    }

    /**
     * Detecta conflictos: mismo médico + mismo id o choques de horario
     */
    public List<String> detectarConflictos(List<Turno> agendaA, List<Turno> agendaB) {
        List<String> conflictos = new ArrayList<>();
        
        // Agrupar por médico
        Map<String, List<Turno>> turnosPorMedico = new HashMap<>();
        
        for (Turno t : agendaA) {
            turnosPorMedico.computeIfAbsent(t.getMatriculaMedico(), k -> new ArrayList<>()).add(t);
        }
        
        for (Turno t : agendaB) {
            turnosPorMedico.computeIfAbsent(t.getMatriculaMedico(), k -> new ArrayList<>()).add(t);
        }
        
        // Verificar conflictos dentro de cada médico
        for (Map.Entry<String, List<Turno>> entry : turnosPorMedico.entrySet()) {
            String medico = entry.getKey();
            List<Turno> turnos = entry.getValue();
            
            // Ordenar por fecha
            turnos.sort(Comparator.comparing(Turno::getFechaHora));
            
            // Detectar duplicados de ID
            Set<String> idsVistos = new HashSet<>();
            for (Turno t : turnos) {
                if (idsVistos.contains(t.getId())) {
                    conflictos.add("DUPLICADO: ID = " + t.getId() + " (mismo médico)");
                }
                idsVistos.add(t.getId());
            }
            
            // Detectar solapamientos
            for (int i = 0; i < turnos.size() - 1; i++) {
                Turno actual = turnos.get(i);
                Turno siguiente = turnos.get(i + 1);
                
                if (actual.seSolapaCon(siguiente)) {
                    conflictos.add("SOLAPAMIENTO: " + actual.getId() + " con " + siguiente.getId() + 
                                 " (Médico: " + medico + ")");
                }
            }
        }
        
        return conflictos;
    }

    /**
     * Consolidación completa con reporte
     */
    public void consolidarYMostrar(List<Turno> agendaLocal, List<Turno> agendaNube, String nombreLocal, String nombreNube) {
        System.out.println("\n================================================");
        System.out.println("CONSOLIDACIÓN DE AGENDAS (Merge + Deduplicación)");
        System.out.println("================================================");
        
        System.out.println("\n" + nombreLocal + ": " + agendaLocal.size() + " turnos");
        System.out.println(nombreNube + ": " + agendaNube.size() + " turnos");
        
        // Detectar conflictos
        List<String> conflictos = detectarConflictos(agendaLocal, agendaNube);
        
        if (!conflictos.isEmpty()) {
            System.out.println("\nCONFLICTOS DETECTADOS:");
            for (String conflicto : conflictos) {
                System.out.println("  - " + conflicto);
            }
        } else {
            System.out.println("\nSin conflictos detectados");
        }
        
        // Merge
        List<Turno> consolidada = merge(agendaLocal, agendaNube);
        
        System.out.println("\nAgenda consolidada: " + consolidada.size() + " turnos");
        System.out.println("\nPrimeros 5 turnos:");
        for (int i = 0; i < Math.min(5, consolidada.size()); i++) {
            System.out.println("  " + (i + 1) + ". " + consolidada.get(i));
        }
        
        System.out.println("========================================\n");
    }
}
