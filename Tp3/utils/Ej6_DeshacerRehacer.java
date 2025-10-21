package Tp3.utils;

/**
 * Ejercicio 6 - deshacer/rehacer con PilaString
 */
public class Ej6_DeshacerRehacer {
    public static void ejecutar() {
        System.out.println("-- Ej6: Deshacer/Rehacer --");
        PilaString undo = new PilaString(20);
        PilaString redo = new PilaString(20);
        // Simular 5 acciones
        undo.push("Escribir 'Hola'");
        undo.push("Escribir ' Mundo'");
        undo.push("Borrar últimos 6 chars");
        undo.push("Escribir 'Java'");
        undo.push("Copiar línea 1");
        System.out.println("Top undo: " + (undo.isEmpty()?"(vacía)":undo.top()));
        // Deshacer 2 acciones
        String a1 = undo.pop(); redo.push(a1);
        String a2 = undo.pop(); redo.push(a2);
        System.out.println("Después de deshacer, top undo: " + (undo.isEmpty()?"(vacía)":undo.top()));
        System.out.println("Top redo: " + redo.top());
        // Rehacer 1 acción
        String r = redo.pop(); undo.push(r);
        System.out.println("Después de rehacer, top undo: " + undo.top());
    }
}
