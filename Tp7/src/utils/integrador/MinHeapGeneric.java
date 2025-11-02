package Tp7.src.utils.integrador;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Montículo mínimo genérico que usa un Comparator<T> para definir el orden.
 * Permite almacenar cualquier tipo y proporcionar la prioridad mediante el Comparator.
 */
public class MinHeapGeneric<T> {
    private Object[] heap;
    private int size;
    private Comparator<T> cmp;

    public MinHeapGeneric(Comparator<T> cmp) {
        this.cmp = cmp;
        this.heap = new Object[10];
        this.size = 0;
    }

    /**
     * Inserta un elemento genérico en el heap. Usa percolateUp para
     * mantener la propiedad de min-heap según el Comparator.
     * Complejidad: O(log n).
     * @param val valor a insertar
     */
    public void add(T val) {
        ensureCapacity();
        heap[size] = val;
        percolateUp(size);
        size++;
    }

    @SuppressWarnings("unchecked")
    /**
     * Sube el elemento en índice i mientras sea menor que su padre según cmp.
     */
    private void percolateUp(int i) {
        int idx = i;
        while (idx > 0) {
            int p = (idx-1)/2;
            T a = (T)heap[idx];
            T b = (T)heap[p];
            if (cmp.compare(a,b) < 0) {
                Object tmp = heap[idx]; heap[idx] = heap[p]; heap[p] = tmp;
                idx = p;
            } else break;
        }
    }

    @SuppressWarnings("unchecked")
    /**
     * Extrae y devuelve el elemento mínimo (según cmp), o null si está vacío.
     * Complejidad: O(log n).
     * @return elemento mínimo o null
     */
    public T poll() {
        if (size==0) return null;
        T res = (T)heap[0];
        heap[0] = heap[size-1]; heap[size-1]=null; size--;
        percolateDown(0);
        return res;
    }

    @SuppressWarnings("unchecked")
    /**
     * Baja el elemento en índice i intercambiándolo con el hijo de menor orden
     * según cmp hasta restaurar la propiedad de heap.
     */
    private void percolateDown(int i) {
        int idx = i;
        while (true) {
            int l = 2*idx+1, r = 2*idx+2, smallest = idx;
            if (l < size && cmp.compare((T)heap[l], (T)heap[smallest]) < 0) smallest = l;
            if (r < size && cmp.compare((T)heap[r], (T)heap[smallest]) < 0) smallest = r;
            if (smallest != idx) { Object tmp = heap[idx]; heap[idx] = heap[smallest]; heap[smallest] = tmp; idx = smallest; }
            else break;
        }
    }

    /**
     * Devuelve el mínimo sin extraerlo o null si está vacío.
     */
    public T peek() {
        if (size == 0) return null;
        return (T) heap[0];
    }

    /**
     * Indica si el heap está vacío.
     */
    public boolean isEmpty() { return size == 0; }

    /**
     * Aumenta la capacidad del arreglo interno si es necesario.
     */
    private void ensureCapacity() { if (size >= heap.length) heap = Arrays.copyOf(heap, heap.length*2); }
}
