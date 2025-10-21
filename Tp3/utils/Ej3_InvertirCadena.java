package Tp3.utils;

import java.util.Scanner;

/**
 * Ejercicio 3 - invertir cadena usando PilaArreglo (pushing char codes)
 */
public class Ej3_InvertirCadena {
    public static void ejecutar(Scanner sc) {
        System.out.println("-- Ej3: Invertir cadena con pila --");
        System.out.print("Ingrese una cadena: ");
        String s = sc.nextLine();
        PilaArreglo pila = new PilaArreglo(s.length());
        for (char ch : s.toCharArray()) pila.push((int) ch);
        StringBuilder sb = new StringBuilder();
        while (!pila.isEmpty()) sb.append((char) pila.pop());
        System.out.println("Invertida: " + sb.toString());
    }
}
