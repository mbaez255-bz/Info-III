package Tp3.utils;

/**
 * Ejercicio 1 - prueba de PilaArreglo
 */
public class Ej1_Pila {
    public static void ejecutar() {
        System.out.println("-- Ej1: Pila con arreglo --");
        PilaArreglo p = new PilaArreglo(10);
        p.push(10); p.push(20); p.push(30); p.push(40);
        System.out.println("Apilé: 10,20,30,40");
        System.out.println("Pop: " + p.pop());
        System.out.println("Pop: " + p.pop());
        System.out.println("Top actual: " + p.top());
    }
}
