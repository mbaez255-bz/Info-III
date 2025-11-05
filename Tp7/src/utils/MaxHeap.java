package Tp7.src.utils;

import java.util.Arrays;

/**
 * Simple MaxHeap for integers.
 */
public class MaxHeap {
    int[] heap;
    int size;

    public MaxHeap() {
        heap = new int[10];//capacidad inicial 10
        size = 0;
    }

    private void ensureCapacity() {
        //si necesita más espacio, duplica el tamaño del arreglo
        if (size >= heap.length) heap = Arrays.copyOf(heap, heap.length * 2);
    }

    // Indices para la representacion por arreglo (padre/izquierda/derecha)
    private int parent(int i) { return (i-1)/2; }
    private int left(int i) { return 2*i+1; }
    private int right(int i) { return 2*i+2; }

    /**
     * Inserta un valor en el max-heap. Se sube el elemento mientras sea mayor que su padre.
     * Complejidad: O(log n).
     */
    public void add(int v) {
        ensureCapacity();
        heap[size] = v; int i = size; size++;
        while (i > 0 && heap[i] > heap[parent(i)]) {
            int p = parent(i);
            System.out.println("Intercambio arriba (max): " + heap[i] + " <-> " + heap[p]);
            int t = heap[i]; heap[i] = heap[p]; heap[p] = t;
            i = p;
            //intercambia mientras el hijo sea mayor que el padre, mientras no se llegue a la raíz
        }
    }/*Insertar 8:

Se añade al final: heap[3] = 8. Ahora temporalmente:
heap = [15, 3, 10, 8]
i = 3 (índice del nuevo elemento)

Calculamos parent(3):
parent(3) = (3 - 1) / 2 = 2 / 2 = 1 (entero)
parent index = 1 → heap[1] = 3

Comparamos hijo vs padre:
heap[3] = 8 y heap[1] = 3
Como 8 > 3 en un MaxHeap, debemos intercambiarlos.

Swap: heap[3] ↔ heap[1]
Resultado tras el swap:
heap = [15, 8, 10, 3]

(aquí se imprimiría: "Intercambio arriba (max): 8 <-> 3")

Actualizamos i = parent(i) → i = 1. */

    /**
     * Extrae el máximo (raíz) del heap. Reemplaza la raíz con el último
     * elemento y aplica percolateDown para restaurar la propiedad.
     * Complejidad: O(log n).
     * @return el máximo del heap
     */
    public int poll() {
        if (size==0) throw new RuntimeException("Heap vacío");
        int res = heap[0];
        System.out.println("Antes de extraer (max): " + Arrays.toString(Arrays.copyOf(heap, size)));
        heap[0] = heap[size-1]; size--;
        percolateDown(0);
        System.out.println("Después de extraer (max): " + Arrays.toString(Arrays.copyOf(heap, size)));
        return res;
    }

    /**
     * Baja el elemento en índice i intercambiándolo con el hijo mayor hasta
     * restaurar la propiedad de max-heap.
     */
    private void percolateDown(int i) {
        while (true) {
            int l = left(i), r = right(i), largest = i;
            if (l < size && heap[l] > heap[largest]) largest = l;
            if (r < size && heap[r] > heap[largest]) largest = r;
            if (largest != i) {
                System.out.println("Intercambio abajo (max): " + heap[i] + " <-> " + heap[largest]);
                int t = heap[i]; heap[i] = heap[largest]; heap[largest] = t;
                i = largest;
            } else break;
        }//resultado:el mayor de los elementos queda en la raíz, y se mantiene la propiedad del max-heap.
    }

    public boolean isEmpty() { return size==0; }

}
