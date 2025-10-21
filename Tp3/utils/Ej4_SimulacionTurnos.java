package Tp3.utils;

/**
 * Ejercicio 4 - simulación de turnos con ColaString
 */
public class Ej4_SimulacionTurnos {
    public static void ejecutar() {
        System.out.println("-- Ej4: Simulación de turnos --");
        ColaString cola = new ColaString(10);
        cola.enqueue("Ana"); cola.enqueue("Luis"); cola.enqueue("Marta"); cola.enqueue("Pedro");
        System.out.println("Cola antes:");
        for (String s : cola.snapshot()) System.out.print(s + " ");
        System.out.println();
        System.out.println("Atendiendo: " + cola.dequeue());
        System.out.println("Atendiendo: " + cola.dequeue());
        System.out.println("Cola después:");
        for (String s : cola.snapshot()) System.out.print(s + " ");
        System.out.println();
    }
}
