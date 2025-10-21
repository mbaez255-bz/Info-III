
package utils;

import java.util.Scanner;

/**
 * Clase utilitaria para generar la serie de Fibonacci hasta n términos.
 */
public class NumerosFibonacci {

    /**
     * Solicita al usuario la cantidad de términos y muestra la serie de Fibonacci.
     * @param sc Scanner para entrada de datos
     */
    public static void ejecutar(Scanner sc) {
        System.out.println("*** NÚMEROS FIBONACCI ***");
        System.out.print("Ingrese la cantidad de términos (n >= 1): ");
        try {
            int n = Integer.parseInt(sc.nextLine().trim());
            if (n <= 0) {
                System.out.println("Ingrese un número mayor que 0.");
                return;
            }
            // Imprime la serie de Fibonacci hasta n términos
            for (int i = 0; i < n; i++) {
                System.out.print(fibo(i) + (i < n-1 ? ", " : "\n"));
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }
    }

    /**
     * Calcula el término n de la serie de Fibonacci de forma iterativa.
     * @param n Índice del término
     * @return Valor del término n
     */
    public static long fibo(int n) {
        if (n <= 1) return n;
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long t = a + b;
            a = b;
            b = t;
        }
        return b;
    }
}
/*
Secuencia de ejemplo:
fibo(5):
    a=0, b=1
    i=2: t=0+1=1 → a=1, b=1
    i=3: t=1+1=2 → a=1, b=2
    i=4: t=1+2=3 → a=2, b=3
    i=5: t=2+3=5 → a=3, b=5
Devuelve: 5
*/