package Tp7.src.utils.integrador;

import java.util.Comparator;

/**
 * Agenda de tareas implementada sobre un MinHeapGeneric<Task>.
 * Provee operaciones básicas: agregar, ver próxima, completar y mostrar todas.
 */
public class TaskAgenda {
    private MinHeapGeneric<Task> heap;

    /**
     * Crea la agenda con un Comparator que considera menor valor como más urgente.
     */
    public TaskAgenda() {
        // Comparador: la menor prioridad numérica == mayor urgencia
        heap = new MinHeapGeneric<>(Comparator.comparingInt(task -> task.priority));
    }

    /**
     * Agrega una tarea a la agenda.
     */
    public void add(Task task) { heap.add(task); }

    /**
     * Devuelve la próxima tarea sin extraerla (máxima prioridad).
     */
    public Task next() { return heap.peek(); }

    /**
     * Completa (extrae) la próxima tarea.
     */
    public Task complete() { return heap.poll(); }

    /**
     * Muestra todas las tareas en orden (consume temporalmente y las reinserta)
     * Nota: hace O(n log n) porque poll/reinsert utilizan log n cada una.
     */
    public void showAll() {
        // temporalmente consumir y reinsertar para que no se pierdan las tareas
        java.util.List<Task> tmp = new java.util.ArrayList<>();
        while (!heap.isEmpty()) {
            Task t = heap.poll();
            System.out.println(t);
            tmp.add(t);
        }
        for (Task t: tmp) heap.add(t);
    }

}
