package services;

import structures.ColaCircular;

/**
 * Servicio de Sala de Espera (Ejercicio 4)
 * Usa Cola Circular con overflow control
 * Complejidad: O(1) para todas las operaciones
 */

public class SalaEspera {
    
    private ColaCircular cola;

    public SalaEspera(int capacidad) {
        this.cola = new ColaCircular(capacidad);
    }

    /**
     * Un paciente llega a la sala
     * Si está llena, elimina al más antiguo automáticamente
     * Complejidad: O(1)
     */
    public void llegaPaciente(String dni) {
        boolean estabaLlena = cola.isFull();
        
        if (estabaLlena) {
            String eliminado = cola.peek();
            System.out.println(" Cola llena -> Desborda, se elimina el más antiguo: " + eliminado);
        }
        
        cola.llega(dni);
    }

    /**
     * Atiende al siguiente paciente (FIFO)
     * Complejidad: O(1)
     */
    public String atiendePaciente() {
        String dni = cola.atiende();
        if (dni != null) {
            System.out.println(" Atendiendo paciente " + dni);
        } else {
            System.out.println(" Cola vacía");
        }
        return dni;
    }

    /**
     * Consulta el siguiente sin atenderlo
     * Complejidad: O(1)
     */
    public String consultarSiguiente() {
        return cola.peek();
    }

    /**
     * Tamaño actual
     */
    public int size() {
        return cola.size();
    }

    /**
     * Muestra el estado actual de la sala
     */
    public void mostrarEstado() {
        System.out.println("\n============================================");
        System.out.println("SIMULACIÓN DE SALA DE ESPERA (Cola Circular)");
        System.out.println("============================================");
        System.out.println("Capacidad máxima: " + cola.getCapacidad());
        System.out.println("\nEstado actual:");
        System.out.println("FRONT -> " + cola.getEstado() + " <- REAR");
        System.out.println("Tamaño actual: " + cola.size());
        System.out.println("========================================\n");
    }
}
