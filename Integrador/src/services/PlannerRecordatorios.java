package services;

import models.Recordatorio;
import structures.MinHeap;
import java.time.LocalDateTime;

/**
 * Servicio de Planificación de Recordatorios (Ejercicio 5)
 * Usa Min-Heap para obtener el más próximo
 * Complejidad: O(log n) push/pop
 */

public class PlannerRecordatorios {
    
    private MinHeap<Recordatorio> heap;

    public PlannerRecordatorios() {
        this.heap = new MinHeap<>();
    }

    /**
     * Programa un recordatorio
     * Complejidad: O(log n)
     */
    public void programar(Recordatorio recordatorio) {
        heap.push(recordatorio);
        System.out.println("Recordatorio programado: " + recordatorio);
    }

    /**
     * Obtiene el próximo recordatorio sin eliminarlo
     * Complejidad: O(1)
     */
    public Recordatorio proximo() {
        return heap.peek();
    }

    /**
     * Extrae y procesa el próximo recordatorio
     * Complejidad: O(log n)
     */
    public Recordatorio procesarProximo() {
        Recordatorio rec = heap.pop();
        if (rec != null) {
            System.out.println("Procesando recordatorio: " + rec);
        }
        return rec;
    }

    /**
     * Reprograma un recordatorio (actualiza su fecha)
     * Complejidad: O(n log n) - extraer todos, modificar, reinsertar
     */
    public boolean reprogramar(String id, LocalDateTime nuevaFecha) {
        if (heap.isEmpty()) {
            System.out.println("No hay recordatorios para reprogramar");
            return false;
        }

        // Extraer todos los elementos
        MinHeap<Recordatorio> nuevoHeap = new MinHeap<>();
        boolean encontrado = false;

        while (!heap.isEmpty()) {
            Recordatorio rec = heap.pop();
            
            if (rec.getId().equals(id)) {
                // Actualizar fecha del recordatorio encontrado
                rec.setFecha(nuevaFecha);
                encontrado = true;
                System.out.println("Recordatorio reprogramado: " + rec);
            }
            
            // Reinsertar en el nuevo heap
            nuevoHeap.push(rec);
        }

        // Reemplazar el heap antiguo
        this.heap = nuevoHeap;

        if (!encontrado) {
            System.out.println("Recordatorio con ID '" + id + "' no encontrado");
        }

        return encontrado;
    }

    public int size() {
        return heap.size();
    }

    /**
     * Muestra el estado del planner
     */
    public void mostrarEstado() {
        System.out.println("\n========================================");
        System.out.println("PLANNER DE RECORDATORIOS (Min-Heap)");
        System.out.println("========================================");
        
        if (heap.isEmpty()) {
            System.out.println("\nNo hay recordatorios programados.");
        } else {
            System.out.println("\nTotal programados: " + heap.size());
            System.out.println("\nMás próximo:");
            Recordatorio proximo = heap.peek();
            if (proximo != null) {
                System.out.println("  " + proximo);
            }
        }
        
        System.out.println("========================================\n");
    }
}
