package services;

import models.Medico;
import models.Turno;
import structures.ArbolAVL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio de Agenda por Médico (Ejercicio 2)
 * Usa Árbol AVL balanceado por fechaHora
 * Complejidad: O(log n) insert, O(log n) remove, O(log n) siguiente
 */

public class AgendaMedico {
    
    private Map<String, ArbolAVL> agendasPorMedico;
    private Map<String, Medico> medicos;

    public AgendaMedico(Map<String, Medico> medicos) {
        this.agendasPorMedico = new HashMap<>();
        this.medicos = medicos;
        
        // Inicializar agenda para cada médico
        for (String matricula : medicos.keySet()) {
            agendasPorMedico.put(matricula, new ArbolAVL());
        }
    }

    /**
     * Agenda un turno
     * Complejidad: O(log n)
     */
    public boolean agendar(Turno turno) {
        if (turno == null) return false;
        
        String matricula = turno.getMatriculaMedico();
        ArbolAVL agenda = agendasPorMedico.get(matricula);
        
        if (agenda == null) {
            System.out.println("ERROR: Médico no existe");
            return false;
        }

        // Verificar que no haya conflicto (solapamiento)
        List<Turno> turnosExistentes = agenda.inorden();
        for (Turno existente : turnosExistentes) {
            if (turno.seSolapaCon(existente)) {
                System.out.println("ERROR: Conflicto de horario con turno " + existente.getId());
                return false;
            }
        }

        return agenda.insertar(turno);
    }

    /**
     * Cancela un turno por ID
     * Complejidad: O(log n)
     */
    public boolean cancelar(String idTurno, String matriculaMedico) {
        ArbolAVL agenda = agendasPorMedico.get(matriculaMedico);
        if (agenda == null) return false;
        
        return agenda.eliminar(idTurno);
    }

    /**
     * Busca el siguiente turno disponible >= tiempo dado
     * Complejidad: O(log n)
     */
    public Turno siguienteDisponible(String matriculaMedico, LocalDateTime tiempo) {
        ArbolAVL agenda = agendasPorMedico.get(matriculaMedico);
        if (agenda == null) return null;
        
        return agenda.siguienteDisponible(tiempo);
    }

    /**
     * Obtiene todos los turnos de un médico ordenados
     */
    public List<Turno> obtenerAgenda(String matriculaMedico) {
        ArbolAVL agenda = agendasPorMedico.get(matriculaMedico);
        if (agenda == null) return new ArrayList<>();
        
        return agenda.inorden();
    }

    /**
     * Muestra la agenda completa de un médico
     */
    public void mostrarAgenda(String matriculaMedico) {
        Medico medico = medicos.get(matriculaMedico);
        if (medico == null) {
            System.out.println("ERROR: Médico no encontrado");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("AGENDA DEL " + medico.getNombre().toUpperCase() + " - " + medico.getEspecialidad().toUpperCase());
        System.out.println("========================================");

        List<Turno> turnos = obtenerAgenda(matriculaMedico);
        
        if (turnos.isEmpty()) {
            System.out.println("\nNo hay turnos agendados.");
        } else {
            System.out.println("\nTurnos ordenados por fecha (AVL Tree):\n");
            
            for (int i = 0; i < turnos.size(); i++) {
                System.out.println((i + 1) + ". " + turnos.get(i));
            }
            
            System.out.println("\nSiguiente disponible -> " + 
                             LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM HH:mm")) + 
                             " hs -> ");
            
            Turno siguiente = siguienteDisponible(matriculaMedico, LocalDateTime.now());
            if (siguiente != null) {
                System.out.println("   " + siguiente.getFechaHora().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + " hs");
            } else {
                System.out.println("   (sin turnos futuros)");
            }
        }

        System.out.println("========================================\n");
    }

    /**
     * Carga turnos iniciales
     */
    public void cargarTurnos(List<Turno> turnos) {
        for (Turno turno : turnos) {
            agendar(turno);
        }
    }
}
