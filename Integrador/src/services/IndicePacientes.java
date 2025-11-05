package services;

import models.Paciente;
import structures.HashMapPacientes;
import java.util.Map;

/**
 * Servicio de Índice de Pacientes (Ejercicio 6)
 * Usa Hash con Chaining + Rehash
 * Complejidad: O(1) promedio para put, get, remove
 */

public class IndicePacientes {
    
    private HashMapPacientes mapa;

    public IndicePacientes() {
        this.mapa = new HashMapPacientes(10);
    }

    /**
     * Registra o actualiza un paciente
     * Complejidad: O(1) promedio
     */
    public void put(String dni, Paciente paciente) {
        mapa.put(dni, paciente);
    }

    /**
     * Busca un paciente por DNI
     * Complejidad: O(1) promedio
     */
    public Paciente get(String dni) {
        return mapa.get(dni);
    }

    /**
     * Elimina un paciente
     * Complejidad: O(1) promedio
     */
    public boolean remove(String dni) {
        return mapa.remove(dni);
    }

    /**
     * Verifica si existe un paciente
     * Complejidad: O(1) promedio
     */
    public boolean containsKey(String dni) {
        return mapa.containsKey(dni);
    }

    public int size() {
        return mapa.size();
    }

    /**
     * Carga pacientes desde un mapa
     */
    public void cargarPacientes(Map<String, Paciente> pacientes) {
        for (Map.Entry<String, Paciente> entry : pacientes.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Muestra estadísticas del hash
     */
    public void mostrarEstadisticas() {
        mapa.printStats();
    }

    /**
     * Busca y muestra un paciente
     */
    public void buscarYMostrar(String dni) {
        System.out.println("\n========================================");
        System.out.println("BÚSQUEDA EN ÍNDICE DE PACIENTES (Hash)");
        System.out.println("========================================");
        
        System.out.println("Buscando DNI: " + dni);
        
        long inicio = System.nanoTime();
        Paciente p = get(dni);
        long fin = System.nanoTime();
        
        if (p != null) {
            System.out.println("\n[Bucket " + Math.abs(dni.hashCode() % 10) + "] -> " + p);
        } else {
            System.out.println("\nNo encontrado");
        }
        
        System.out.println("\nTiempo: " + (fin - inicio) + " ns");
        System.out.println("========================================\n");
    }
}
