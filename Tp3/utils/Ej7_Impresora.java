package Tp3.utils;

/**
 * Ejercicio 7 - simulación de impresora con ColaString
 */
public class Ej7_Impresora {
    public static void ejecutar() {
        System.out.println("-- Ej7: Simulación de impresora --");
        ColaString cola = new ColaString(10);
        for (int i=1;i<=5;i++) cola.enqueue("Doc"+i);
        System.out.println("Cola inicial:"); for (String s: cola.snapshot()) System.out.print(s+" "); System.out.println();
        for (int i=0;i<3;i++) System.out.println("Imprimiendo: " + cola.dequeue());
        System.out.println("Cola restante:"); for (String s: cola.snapshot()) System.out.print(s+" "); System.out.println();
    }
}
