package Tp7.src.utils;

import java.util.Arrays;

/**
 * MinHeap para enteros (implementación con arreglo dinámico).
 */
public class MinHeap {
    int[] heap;
    int size;

    public MinHeap() {
        heap = new int[10];
        size = 0;
    }
    //constructor monticulo binario a partir de un arreglo
    
    /**
     * Constructor que recibe un arreglo y lo convierte en un min-heap
     * usando el algoritmo "heapify" (bottom-up). Imprime pasos intermedios
     * para fines didácticos.
     * @param data arreglo de entrada
     */
    public MinHeap(int[] data) {
        heap = Arrays.copyOf(data, Math.max(data.length, 10));
        size = data.length;
    System.out.println("Construcción heapify desde: " + Arrays.toString(Arrays.copyOf(data, size)));
        for (int i = parent(size - 1); i >= 0; i--) {
            percolateDown(i, true);
            System.out.println("Paso heapify (i=" + i + "): " + Arrays.toString(Arrays.copyOf(heap, size)));
        }
    }

    private void ensureCapacity() {
        if (size >= heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2*i + 1; }
    private int right(int i) { return 2*i + 2; }

    //inserta un elemento (usa percolateUp).
    public void add(int value) {
        ensureCapacity();
        heap[size] = value;
    System.out.println("Insertando " + value + " en índice " + size);
        percolateUp(size);
        size++;
        printArray();
        printTree();
    }

    private void percolateUp(int idx) {
        int i = idx;
        while (i > 0) {
            int p = parent(i);
            if (heap[i] < heap[p]) {
                System.out.println("Intercambio arriba: " + heap[i] + " <-> " + heap[p]);
                int tmp = heap[i]; heap[i] = heap[p]; heap[p] = tmp;
                i = p;
            } else break;
        }
    }
    //extrae el mínimo (raíz) y reorganiza con percolateDown.
    public int poll() {
        if (isEmpty()) throw new RuntimeException("Heap vacío");
    System.out.println("Antes de extraer: " + java.util.Arrays.toString(Arrays.copyOf(heap, size)));
        int res = heap[0];
        heap[0] = heap[size-1];
        size--;
        percolateDown(0, false);
    System.out.println("Después de extraer: " + java.util.Arrays.toString(Arrays.copyOf(heap, size)));
        printTree();
        return res;
    }

    private void percolateDown(int idx, boolean silent) {
        int i = idx;
        while (true) {
            int l = left(i), r = right(i);
            int smallest = i;
            if (l < size && heap[l] < heap[smallest]) smallest = l;
            if (r < size && heap[r] < heap[smallest]) smallest = r;
            if (smallest != i) {
                if (!silent) System.out.println("Intercambio abajo: " + heap[i] + " <-> " + heap[smallest]);
                int tmp = heap[i]; heap[i] = heap[smallest]; heap[smallest] = tmp;
                i = smallest;
            } else break;
        }
    }

    public int peek() { if (isEmpty()) throw new RuntimeException("Heap vacío"); return heap[0]; }
    public boolean isEmpty() { return size == 0; }

    public void printArray() {
    System.out.println("Array interno: " + java.util.Arrays.toString(Arrays.copyOf(heap, size)));
    }

    public void printTree() {
        int level = 0;
        int i = 0;
        while (i < size) {
            int levelCount = (int)Math.pow(2, level);
            int printed = 0;
            StringBuilder sb = new StringBuilder();
            while (printed < levelCount && i < size) {
                if (printed > 0) sb.append(' ');
                sb.append(heap[i]);
                printed++; i++;
            }
            System.out.println(sb.toString());
            level++;
        }
    }

    public static void heapsort(int[] arr) {
        MinHeap h = new MinHeap(arr);
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr[i] = h.poll();
        }
    }
}
