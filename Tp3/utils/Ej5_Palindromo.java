package Tp3.utils;

import java.util.Scanner;

/**
 * Ejercicio 5 - determinar palíndromo usando pila y cola (interno)
 */
public class Ej5_Palindromo {
    public static void ejecutar(Scanner sc) {
        System.out.println("-- Ej5: Palíndromo con pila y cola --");
        System.out.print("Ingrese una palabra: ");
    String s = sc.nextLine().replaceAll("\\s+", "").toLowerCase();
        int n = s.length();
        // usar arrays locales como pila/cola
        char[] pila = new char[n]; int top=-1;
    char[] cola = new char[n]; int front=0, rear=-1;
    for (char c: s.toCharArray()) { pila[++top]=c; rear=(rear+1)%n; cola[rear]=c; }
        boolean pal=true;
        for (int i=0;i<n;i++) {
            char a = pila[top--];
            char b = cola[(front+i)%n];
            if (a!=b) { pal=false; break; }
        }
        System.out.println(pal?"Es palíndromo":"No es palíndromo");
    }
}
