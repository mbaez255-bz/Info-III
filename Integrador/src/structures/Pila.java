package structures;

import java.util.*;

/**
 * Pila (Stack) genérica
 * Complejidad: O(1) para push, pop, peek
 * Uso: Sistema de auditoría Undo/Redo (Ejercicio 9)
 */

public class Pila<T> {
    private List<T> elementos;

    public Pila() {
        this.elementos = new ArrayList<>();
    }

    /**
     * Inserta un elemento en el tope
     * Complejidad: O(1)
     */
    public void push(T elemento) {
        if (elemento == null) return;
        elementos.add(elemento);
    }

    /**
     * Extrae y retorna el elemento del tope
     * Complejidad: O(1)
     */
    public T pop() {
        if (isEmpty()) return null;
        return elementos.remove(elementos.size() - 1);
    }

    /**
     * Consulta el elemento del tope sin eliminarlo
     * Complejidad: O(1)
     */
    public T peek() {
        if (isEmpty()) return null;
        return elementos.get(elementos.size() - 1);
    }

    public int size() {
        return elementos.size();
    }

    public boolean isEmpty() {
        return elementos.isEmpty();
    }

    /**
     * Limpia la pila
     */
    public void clear() {
        elementos.clear();
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = elementos.size() - 1; i >= 0; i--) {
            sb.append(elementos.get(i));
            if (i > 0) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
