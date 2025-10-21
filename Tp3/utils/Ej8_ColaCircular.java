package Tp3.utils;

/**
 * Ejercicio 8 - Cola circular para llamadas
 */
public class Ej8_ColaCircular {
    public static void ejecutar() {
        System.out.println("-- Ej8: Cola circular para llamadas --");
        ColaCircular cola = new ColaCircular(5);
        for (int i=1;i<=8;i++) {
            cola.enqueueOverwrite("Llamada"+i);
            System.out.println("Llegó: Llamada"+i);
        }
        System.out.println("Estado final de la cola:");
        for (String s: cola.snapshot()) System.out.print(s+" ");
        System.out.println();
    }
}
