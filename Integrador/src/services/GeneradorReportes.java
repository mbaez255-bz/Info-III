package services;

import models.Turno;
import utils.Ordenamientos;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Servicio de Generación de Reportes (Ejercicio 8)
 * Implementa múltiples ordenamientos con medición de tiempos
 * Complejidades:
 * - Insertion Sort: O(n²) estable
 * - Shellsort: O(n^1.5) aproximado
 * - Quicksort: O(n log n) promedio
 */

public class GeneradorReportes {

    /**
     * Genera reporte ordenado por hora (estable)
     */
    public void reportePorHora(List<Turno> turnos) {
        System.out.println("\n============================================");
        System.out.println("REPORTE POR HORA (Insertion Sort - Estable)");
        System.out.println("============================================\n");

        List<Turno> copia = new ArrayList<>(turnos);
        
        long tiempo = Ordenamientos.medirTiempo(() -> {
            Ordenamientos.insertionSort(copia, Comparator.comparing(Turno::getFechaHora));
        });

        System.out.println("Turnos ordenados por hora:\n");
        for (int i = 0; i < Math.min(10, copia.size()); i++) {
            System.out.println((i + 1) + ". " + copia.get(i));
        }

        System.out.println("\nTiempo de ejecución: " + tiempo + " ms");
        System.out.println("========================================\n");
    }

    /**
     * Genera reporte ordenado por duración (gap sequence)
     */
    public void reportePorDuracion(List<Turno> turnos) {
        System.out.println("\n========================================");
        System.out.println("REPORTE POR DURACIÓN (Shellsort)");
        System.out.println("========================================\n");

        List<Turno> copia = new ArrayList<>(turnos);
        
        long tiempo = Ordenamientos.medirTiempo(() -> {
            Ordenamientos.shellsort(copia, Comparator.comparingInt(Turno::getDuracionMin));
        });

        System.out.println("Turnos ordenados por duración (ascendente):\n");
        for (int i = 0; i < Math.min(10, copia.size()); i++) {
            System.out.println((i + 1) + ". " + copia.get(i).getId() + " - " + 
                             copia.get(i).getDuracionMin() + " min");
        }

        System.out.println("\nTiempo de ejecución: " + tiempo + " ms");
        System.out.println("========================================\n");
    }

    /**
     * Genera reporte ordenado por apellido de paciente (pivot final)
     */
    public void reportePorApellido(List<Turno> turnos, Map<String, String> dniANombre) {
        System.out.println("\n==========================================");
        System.out.println("REPORTE POR APELLIDO PACIENTE (Quicksort)");
        System.out.println("==========================================\n");

        List<Turno> copia = new ArrayList<>(turnos);
        
        long tiempo = Ordenamientos.medirTiempo(() -> {
            Ordenamientos.quicksort(copia, (t1, t2) -> {
                String nombre1 = dniANombre.getOrDefault(t1.getDniPaciente(), "");
                String nombre2 = dniANombre.getOrDefault(t2.getDniPaciente(), "");
                return nombre1.compareTo(nombre2);
            });
        });

        System.out.println("Turnos ordenados por apellido del paciente:\n");
        for (int i = 0; i < Math.min(10, copia.size()); i++) {
            String nombre = dniANombre.getOrDefault(copia.get(i).getDniPaciente(), "Desconocido");
            System.out.println((i + 1) + ". " + nombre + " - " + copia.get(i).getId());
        }

        System.out.println("\nTiempo de ejecución: " + tiempo + " ms");
        System.out.println("========================================\n");
    }

    /**
     * Comparación de los 3 algoritmos con diferentes tamaños
     */
    public void compararAlgoritmos(List<Turno> turnos) {
        System.out.println("\n==========================================");
        System.out.println("COMPARACIÓN DE ALGORITMOS DE ORDENAMIENTO");
        System.out.println("==========================================\n");

        int[] tamaños = {10, 50, turnos.size()};
        
        for (int n : tamaños) {
            if (n > turnos.size()) continue;
            
            List<Turno> muestra = new ArrayList<>(turnos.subList(0, n));
            
            System.out.println("Con " + n + " turnos:");
            
            // Insertion Sort
            List<Turno> copia1 = new ArrayList<>(muestra);
            long t1 = Ordenamientos.medirTiempo(() -> {
                Ordenamientos.insertionSort(copia1, Comparator.comparing(Turno::getFechaHora));
            });
            System.out.println("  - Insertion Sort: " + t1 + " ms");
            
            // Shellsort
            List<Turno> copia2 = new ArrayList<>(muestra);
            long t2 = Ordenamientos.medirTiempo(() -> {
                Ordenamientos.shellsort(copia2, Comparator.comparing(Turno::getFechaHora));
            });
            System.out.println("  - Shellsort:      " + t2 + " ms");
            
            // Quicksort
            List<Turno> copia3 = new ArrayList<>(muestra);
            long t3 = Ordenamientos.medirTiempo(() -> {
                Ordenamientos.quicksort(copia3, Comparator.comparing(Turno::getFechaHora));
            });
            System.out.println("  - Quicksort:      " + t3 + " ms");
            System.out.println();
        }

        System.out.println("========================================\n");
    }
}
