
package utils;

import java.util.Scanner;

/**
 * Clase utilitaria para invertir una cadena de caracteres.
 */
public class InvertirCadena {

    /**
     * Solicita una cadena al usuario, la invierte y muestra el resultado.
     * @param sc Scanner para entrada de datos
     */
    public static void ejecutar(Scanner sc) {
        System.out.println("*** INVERTIR CADENA ***");
        System.out.print("Ingrese una cadena: ");
        String s = sc.nextLine();
        System.out.println("Cadena invertida: " + invertir(s));
    }

    /**
     * Invierte el orden de los caracteres de una cadena.
     * @param s Cadena a invertir
     * @return Cadena invertida
     */
    public static String invertir(String s) {
        if (s == null || s.length() <= 1) return s;
        return new StringBuilder(s).reverse().toString();
    }
}
/*
Secuencia de ejemplo:
invertir("hola"):
    StringBuilder("hola") → reverse() → "aloh"
Devuelve: "aloh"
*/