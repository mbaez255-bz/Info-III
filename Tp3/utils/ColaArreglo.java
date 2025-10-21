package Tp3.utils;

/**
 * Cola simple usando arreglo (no circular).
 */
public class ColaArreglo {
    private int[] datos;
    private int frente;
    private int rear;
    private int count;

    public ColaArreglo(int capacidad) {
        datos = new int[capacidad];
        frente = 0;
        rear = -1;
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == datos.length;
    }

    public void enqueue(int dato) {
        if (isFull()) throw new IllegalStateException("Cola llena");
        rear = (rear + 1) % datos.length;
        datos[rear] = dato;
        count++;
    }

    public int dequeue() {
        if (isEmpty()) throw new IllegalStateException("Cola vacía");
        int val = datos[frente];
        frente = (frente + 1) % datos.length;
        count--;
        return val;
    }

    public int top() {
        if (isEmpty()) throw new IllegalStateException("Cola vacía");
        return datos[frente];
    }

    public int size() { return count; }
}
