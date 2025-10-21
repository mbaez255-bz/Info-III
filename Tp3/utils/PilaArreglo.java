package Tp3.utils;

/**
 * Implementación simple de una pila usando arreglo.
 */
public class PilaArreglo {
    private int[] datos;
    private int top;

    public PilaArreglo(int capacidad) {
        datos = new int[capacidad];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == datos.length - 1;
    }

    public void push(int dato) {
        if (isFull()) throw new IllegalStateException("Pila llena");
        datos[++top] = dato;
    }

    public int pop() {
        if (isEmpty()) throw new IllegalStateException("Pila vacía");
        return datos[top--];
    }

    public int top() {
        if (isEmpty()) throw new IllegalStateException("Pila vacía");
        return datos[top];
    }

    public int size() {
        return top + 1;
    }
}
