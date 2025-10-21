package Tp3.utils;

/**
 * Cola para Strings (circular).
 */
public class ColaString {
    private String[] datos;
    private int frente, rear, count;

    public ColaString(int capacidad) {
        datos = new String[capacidad];
        frente = 0; rear = -1; count = 0;
    }

    public boolean isEmpty() { return count == 0; }
    public boolean isFull() { return count == datos.length; }

    public void enqueue(String s) {
        if (isFull()) throw new IllegalStateException("Cola llena");
        rear = (rear + 1) % datos.length;
        datos[rear] = s; count++;
    }

    public String dequeue() {
        if (isEmpty()) throw new IllegalStateException("Cola vacía");
        String v = datos[frente]; frente = (frente + 1) % datos.length; count--; return v;
    }

    public String peek() { if (isEmpty()) throw new IllegalStateException("Cola vacía"); return datos[frente]; }

    public int size() { return count; }

    public String[] snapshot() {
        String[] out = new String[count];
        for (int i=0;i<count;i++) out[i] = datos[(frente + i) % datos.length];
        return out;
    }
}
