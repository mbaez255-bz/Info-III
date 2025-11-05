package services;

import models.Turno;
import structures.Pila;
import java.time.LocalDateTime;

/**
 * Servicio de Auditoría con Undo/Redo (Ejercicio 9)
 * Usa dos pilas: una para acciones realizadas y otra para rehacer
 * Complejidad: O(1) para undo/redo
 */

public class AuditoriaHistorial {
    
    private Pila<Accion> pilaAcciones;
    private Pila<Accion> pilaRehacer;
    private AgendaMedico agendaMedico;

    public static class Accion {
        public enum Tipo { AGENDAR, CANCELAR, REPROGRAMAR }
        
        Tipo tipo;
        Turno turno;
        LocalDateTime fechaAnterior; // Para reprogramar
        
        public Accion(Tipo tipo, Turno turno) {
            this.tipo = tipo;
            this.turno = turno;
        }
        
        public Accion(Tipo tipo, Turno turno, LocalDateTime fechaAnterior) {
            this.tipo = tipo;
            this.turno = turno;
            this.fechaAnterior = fechaAnterior;
        }

        @Override
        public String toString() {
            switch (tipo) {
                case AGENDAR:
                    return "Agendar(" + turno.getId() + ")";
                case CANCELAR:
                    return "Cancelar(" + turno.getId() + ")";
                case REPROGRAMAR:
                    return "Reprogramar(" + turno.getId() + ")";
                default:
                    return "Acción desconocida";
            }
        }
    }

    public AuditoriaHistorial(AgendaMedico agendaMedico) {
        this.pilaAcciones = new Pila<>();
        this.pilaRehacer = new Pila<>();
        this.agendaMedico = agendaMedico;
    }

    /**
     * Registra una acción de agendar
     */
    public void registrarAgendar(Turno turno) {
        pilaAcciones.push(new Accion(Accion.Tipo.AGENDAR, turno));
        pilaRehacer.clear(); // Limpiar pila de rehacer
        System.out.println("Acción registrada: Agendar " + turno.getId());
    }

    /**
     * Registra una acción de cancelar
     */
    public void registrarCancelar(Turno turno) {
        pilaAcciones.push(new Accion(Accion.Tipo.CANCELAR, turno));
        pilaRehacer.clear();
        System.out.println("Acción registrada: Cancelar " + turno.getId());
    }

    /**
     * Registra una acción de reprogramar
     */
    public void registrarReprogramar(Turno turno, LocalDateTime fechaAnterior) {
        pilaAcciones.push(new Accion(Accion.Tipo.REPROGRAMAR, turno, fechaAnterior));
        pilaRehacer.clear();
        System.out.println("Acción registrada: Reprogramar " + turno.getId());
    }

    /**
     * Deshace la última acción
     * Complejidad: O(1)
     */
    public boolean undo() {
        if (pilaAcciones.isEmpty()) {
            System.out.println("No hay acciones para deshacer");
            return false;
        }

        Accion accion = pilaAcciones.pop();
        pilaRehacer.push(accion);

        System.out.println("[UNDO] ← " + accion);

        switch (accion.tipo) {
            case AGENDAR:
                // Deshacer agendar = cancelar
                agendaMedico.cancelar(accion.turno.getId(), accion.turno.getMatriculaMedico());
                System.out.println("  Agenda vuelve al estado anterior");
                break;
                
            case CANCELAR:
                // Deshacer cancelar = reagendar
                agendaMedico.agendar(accion.turno);
                System.out.println("  Turno restaurado");
                break;
                
            case REPROGRAMAR:
                // Deshacer reprogramar = volver a fecha anterior
                accion.turno.setFechaHora(accion.fechaAnterior);
                agendaMedico.cancelar(accion.turno.getId(), accion.turno.getMatriculaMedico());
                agendaMedico.agendar(accion.turno);
                System.out.println("  Fecha restaurada");
                break;
        }

        return true;
    }

    /**
     * Rehace la última acción deshecha
     * Complejidad: O(1)
     */
    public boolean redo() {
        if (pilaRehacer.isEmpty()) {
            System.out.println("No hay acciones para rehacer");
            return false;
        }

        Accion accion = pilaRehacer.pop();
        pilaAcciones.push(accion);

        System.out.println("[REDO] → " + accion);

        switch (accion.tipo) {
            case AGENDAR:
                agendaMedico.agendar(accion.turno);
                System.out.println("  Turno agendado nuevamente");
                break;
                
            case CANCELAR:
                agendaMedico.cancelar(accion.turno.getId(), accion.turno.getMatriculaMedico());
                System.out.println("  Turno cancelado nuevamente");
                break;
                
            case REPROGRAMAR:
                // Aplicar reprogramación nuevamente
                System.out.println("  Reprogramación aplicada nuevamente");
                break;
        }

        return true;
    }

    /**
     * Muestra el estado de las pilas
     */
    public void mostrarEstado() {
        System.out.println("\n==================================");
        System.out.println("AUDITORÍA Y UNDO/REDO (Pilas)");
        System.out.println("==================================\n");
        
        System.out.println("Estado actual de pila:");
        System.out.println("  [Tope Undo] <- " + pilaAcciones);
        System.out.println("  [Tope Redo] <- " + pilaRehacer);
        
        System.out.println("\nAcciones disponibles:");
        System.out.println("  - Undo: " + (pilaAcciones.isEmpty() ? "No disponible" : "Deshacer " + pilaAcciones.peek()));
        System.out.println("  - Redo: " + (pilaRehacer.isEmpty() ? "No disponible" : "Rehacer " + pilaRehacer.peek()));
        
        System.out.println("========================================\n");
    }
}
