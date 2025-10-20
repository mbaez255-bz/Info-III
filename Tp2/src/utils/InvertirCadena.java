package utils;

import java.util.Scanner;
/**
 * Invierte una cadena de caracteres.
 */
public class InvertirCadena {
    public static void ejecutar(Scanner sc) {
        System.out.println("*** INVERTIR CADENA ***");
        System.out.print("Ingrese una cadena: ");
        String s = sc.nextLine();
        System.out.println("Cadena invertida: " + invertir(s));
    }

    public static String invertir(String s) {
        if (s == null || s.length() <= 1) return s;
        return new StringBuilder(s).reverse().toString();
    }
}
