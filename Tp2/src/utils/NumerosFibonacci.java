package utils;

import java.util.Scanner;

/**
 * Genera la serie Fibonacci hasta n términos.
 */
public class NumerosFibonacci {
    public static void ejecutar(Scanner sc) {
        System.out.println("*** NÚMEROS FIBONACCI ***");
        System.out.print("Ingrese la cantidad de términos (n >= 1): ");
        try {
            int n = Integer.parseInt(sc.nextLine().trim());
            if (n <= 0) {
                System.out.println("Ingrese un número mayor que 0.");
                return;
            }
            for (int i = 0; i < n; i++) {
                System.out.print(fibo(i) + (i < n-1 ? ", " : "\n"));
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }
    }

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
