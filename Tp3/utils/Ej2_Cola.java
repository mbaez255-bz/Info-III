package Tp3.utils;

/**
 * Ejercicio 2 - prueba de ColaArreglo
 */
public class Ej2_Cola {
    public static void ejecutar() {
        System.out.println("-- Ej2: Cola con arreglo --");
        ColaArreglo c = new ColaArreglo(10);
        c.enqueue(1); c.enqueue(2); c.enqueue(3); c.enqueue(4);
        System.out.println("Encolé: 1,2,3,4");
        System.out.println("Dequeue: " + c.dequeue());
        System.out.println("Frente actual: " + c.top());
    }
}
