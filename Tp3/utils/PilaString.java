package Tp3.utils;

/**
 * Pila para Strings sencilla.
 */
public class PilaString {
    private String[] datos;
    private int top;

    public PilaString(int capacidad) {
        datos = new String[capacidad];
        top = -1;
    }

    public boolean isEmpty() { return top == -1; }
    public boolean isFull() { return top == datos.length - 1; }
    public void push(String s) { if (isFull()) throw new IllegalStateException("Pila llena"); datos[++top] = s; }
    public String pop() { if (isEmpty()) throw new IllegalStateException("Pila vacía"); return datos[top--]; }
    public String top() { if (isEmpty()) throw new IllegalStateException("Pila vacía"); return datos[top]; }
    public int size() { return top + 1; }
}
