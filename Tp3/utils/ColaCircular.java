package Tp3.utils;

/**
 * Cola circular que sobrescribe el elemento más antiguo al estar llena.
 */
public class ColaCircular {
    private String[] datos; private int frente, rear, count;
    public ColaCircular(int capacidad) { datos=new String[capacidad]; frente=0; rear=-1; count=0; }
    public boolean isEmpty() { return count==0; }
    public boolean isFull() { return count==datos.length; }
    public void enqueueOverwrite(String s) {
        if (!isFull()) {
            rear=(rear+1)%datos.length; datos[rear]=s; count++; return;
        }
        // sobrescribir más antiguo: avanzar frente y escribir en rear+1
        frontAdvance(); rear=(rear+1)%datos.length; datos[rear]=s;
    }
    private void frontAdvance() { frente=(frente+1)%datos.length; }
    public String[] snapshot() { String[] out=new String[count]; for (int i=0;i<count;i++) out[i]=datos[(frente+i)%datos.length]; return out; }
}
