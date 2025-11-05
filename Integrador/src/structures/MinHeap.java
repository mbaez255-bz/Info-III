package structures;

import java.util.*;

/**
 * Min-Heap genérico
 * Complejidad: O(log n) para push/pop, O(1) para peek
 * Uso: Priority Queue para recordatorios (Ejercicio 5) 
 * y planificador de quirófano (Ejercicio 10)
 */

public class MinHeap<T extends Comparable<T>> {
    private List<T> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    /**
     * Inserta un elemento en el heap
     * Complejidad: O(log n)
     */
    public void push(T elemento) {
        if (elemento == null) return;
        
        heap.add(elemento);
        heapifyUp(heap.size() - 1);
    }

    /**
     * Extrae y retorna el mínimo elemento
     * Complejidad: O(log n)
     */
    public T pop() {
        if (isEmpty()) return null;

        T minimo = heap.get(0);
        T ultimo = heap.remove(heap.size() - 1);

        if (!isEmpty()) {
            heap.set(0, ultimo);
            heapifyDown(0);
        }

        return minimo;
    }

    /**
     * Consulta el mínimo sin eliminarlo
     * Complejidad: O(1)
     */
    public T peek() {
        return isEmpty() ? null : heap.get(0);
    }

    /**
     * Reorganiza hacia arriba (para inserción)
     */
    private void heapifyUp(int indice) {
        while (indice > 0) {
            int padre = (indice - 1) / 2;
            
            if (heap.get(indice).compareTo(heap.get(padre)) >= 0) {
                break;
            }

            // Swap
            swap(indice, padre);
            indice = padre;
        }
    }

    /**
     * Reorganiza hacia abajo (para extracción)
     */
    private void heapifyDown(int indice) {
        while (true) {
            int izq = 2 * indice + 1;
            int der = 2 * indice + 2;
            int minimo = indice;

            if (izq < heap.size() && heap.get(izq).compareTo(heap.get(minimo)) < 0) {
                minimo = izq;
            }

            if (der < heap.size() && heap.get(der).compareTo(heap.get(minimo)) < 0) {
                minimo = der;
            }

            if (minimo == indice) break;

            swap(indice, minimo);
            indice = minimo;
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Devuelve todos los elementos (no ordenados)
     */
    public List<T> getElementos() {
        return new ArrayList<>(heap);
    }
}
