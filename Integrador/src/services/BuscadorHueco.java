package services;

import models.Turno;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Servicio para buscar hueco libre (Ejercicio 3)
 * Dado un médico y duración, halla el primer hueco libre >= t0
 * Complejidad objetivo: O(log n + k) donde k = turnos saltados
 */

public class BuscadorHueco {
    
    private AgendaMedico agendaMedico;

    public BuscadorHueco(AgendaMedico agendaMedico) {
        this.agendaMedico = agendaMedico;
    }

    /**
     * Busca el primer hueco libre de duracionMin minutos
     * a partir de tiempo t0, considerando horario laboral
     * 
     * @param matriculaMedico Matrícula del médico
     * @param t0 Tiempo desde el cual buscar
     * @param duracionMin Duración requerida en minutos
     * @return LocalDateTime del hueco encontrado, o null si no hay
     */
    public LocalDateTime primerHueco(String matriculaMedico, LocalDateTime t0, int duracionMin) {
        List<Turno> agenda = agendaMedico.obtenerAgenda(matriculaMedico);
        
        if (agenda.isEmpty()) {
            // No hay turnos, cualquier hora laboral es válida
            return ajustarAHorarioLaboral(t0);
        }

        // Ordenar por fecha (ya viene ordenado del AVL)
        LocalDateTime candidato = ajustarAHorarioLaboral(t0);
        
        for (Turno turno : agenda) {
            // Si el turno es antes del candidato, ignorar
            if (turno.getFechaHoraFin().isBefore(candidato) || 
                turno.getFechaHoraFin().isEqual(candidato)) {
                continue;
            }
            
            // Si hay espacio antes del turno
            if (turno.getFechaHora().isAfter(candidato.plusMinutes(duracionMin)) ||
                turno.getFechaHora().isEqual(candidato.plusMinutes(duracionMin))) {
                // Hay hueco!
                return candidato;
            }
            
            // No hay hueco, avanzar al final de este turno
            candidato = ajustarAHorarioLaboral(turno.getFechaHoraFin());
        }
        
        // Si llegamos aquí, el hueco está después del último turno
        return candidato;
    }

    /**
     * Ajusta un tiempo al horario laboral más cercano
     * Horario laboral: 8:00 - 18:00 de lunes a viernes
     * Si no hay hueco en el día, continúa al día siguiente
     */
    private LocalDateTime ajustarAHorarioLaboral(LocalDateTime tiempo) {
        LocalDateTime ajustado = tiempo;
        
        // Si es antes de las 8:00, ajustar a las 8:00
        if (ajustado.getHour() < 8) {
            ajustado = ajustado.withHour(8).withMinute(0).withSecond(0);
        }
        
        // Si es después de las 18:00, pasar al día siguiente a las 8:00
        if (ajustado.getHour() >= 18) {
            ajustado = ajustado.plusDays(1).withHour(8).withMinute(0).withSecond(0);
        }
        
        // Saltar fines de semana (sábado = 6, domingo = 7)
        while (ajustado.getDayOfWeek().getValue() >= 6) {
            ajustado = ajustado.plusDays(1).withHour(8).withMinute(0).withSecond(0);
        }
        
        return ajustado;
    }

    /**
     * Busca y muestra el primer hueco libre
     */
    public void buscarYMostrar(String matriculaMedico, LocalDateTime t0, int duracionMin) {
        System.out.println("\n========================================");
        System.out.println("BÚSQUEDA DE HUECO LIBRE DE X MINUTOS");
        System.out.println("========================================\n");
        
        System.out.println("Médico: " + matriculaMedico);
        System.out.println("Desde: " + t0.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        System.out.println("Duración requerida: " + duracionMin + " min");
        
        long inicio = System.nanoTime();
        LocalDateTime hueco = primerHueco(matriculaMedico, t0, duracionMin);
        long fin = System.nanoTime();
        
        if (hueco != null) {
            System.out.println("\n Primer hueco libre:");
            System.out.println("  " + hueco.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + " hs hasta " +
                             hueco.plusMinutes(duracionMin).format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")) + " hs");
        } else {
            System.out.println("\n No hay hueco disponible en el día");
        }

        System.out.println("\nTiempo de búsqueda: " + (fin - inicio) / 1_000 + " microsegundos");
        System.out.println("========================================\n");
    }
}
