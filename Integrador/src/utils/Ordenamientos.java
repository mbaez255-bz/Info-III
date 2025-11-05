package utils;

import models.Turno;
import java.util.*;

/**
 * Implementación de algoritmos de ordenamiento (Ejercicio 8)
 * - Insertion Sort: O(n²) estable
 * - Shellsort (Lomuto): O(n^1.5) aproximado
 * - Quicksort: O(n log n) promedio
 */

public class Ordenamientos {

    /**
     * Insertion Sort - Estable
     * Complejidad: O(n²)
     * Mejor caso: O(n) cuando está casi ordenado
     */
    public static void insertionSort(List<Turno> lista, Comparator<Turno> comparador) {
        for (int i = 1; i < lista.size(); i++) {
            Turno clave = lista.get(i);
            int j = i - 1;

            while (j >= 0 && comparador.compare(lista.get(j), clave) > 0) {
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j + 1, clave);
        }
    }

    /**
     * Shellsort con secuencia de Knuth
     * Complejidad: O(n^1.5) aproximado
     */
    public static void shellsort(List<Turno> lista, Comparator<Turno> comparador) {
        int n = lista.size();
        
        // Calcular gaps con secuencia de Knuth: 1, 4, 13, 40, 121, ...
        int gap = 1;
        while (gap < n / 3) {
            gap = 3 * gap + 1;
        }

        while (gap >= 1) {
            // Insertion sort con gap
            for (int i = gap; i < n; i++) {
                Turno temp = lista.get(i);
                int j = i;
                
                while (j >= gap && comparador.compare(lista.get(j - gap), temp) > 0) {
                    lista.set(j, lista.get(j - gap));
                    j -= gap;
                }
                lista.set(j, temp);
            }
            gap /= 3;
        }
    }

    /**
     * Quicksort con partición de Lomuto
     * Complejidad: O(n log n) promedio, O(n²) peor caso
     */
    public static void quicksort(List<Turno> lista, Comparator<Turno> comparador) {
        quicksortRec(lista, 0, lista.size() - 1, comparador);
    }

    private static void quicksortRec(List<Turno> lista, int low, int high, Comparator<Turno> comparador) {
        if (low < high) {
            int pi = partition(lista, low, high, comparador);
            quicksortRec(lista, low, pi - 1, comparador);
            quicksortRec(lista, pi + 1, high, comparador);
        }
    }

    /**
     * Partición de Lomuto
     */
    private static int partition(List<Turno> lista, int low, int high, Comparator<Turno> comparador) {
        Turno pivot = lista.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparador.compare(lista.get(j), pivot) <= 0) {
                i++;
                swap(lista, i, j);
            }
        }
        swap(lista, i + 1, high);
        return i + 1;
    }

    private static void swap(List<Turno> lista, int i, int j) {
        Turno temp = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, temp);
    }

    /**
     * Mide el tiempo de ejecución de un algoritmo
     */
    public static long medirTiempo(Runnable algoritmo) {
        long inicio = System.nanoTime();
        algoritmo.run();
        long fin = System.nanoTime();
        return (fin - inicio) / 1_000_000; // Convertir a ms
    }
}
