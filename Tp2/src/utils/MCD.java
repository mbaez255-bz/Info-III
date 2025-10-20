package utils;

import java.util.Scanner;

/**
 * Cálculo del Máximo Común Divisor (MCD) usando Euclides.
 */

public class MCD {
    public static void ejecutar(Scanner sc) {
        System.out.println("*** MÁXIMO COMÚN DIVISOR (MCD) ***");
        System.out.print("Ingrese el primer entero: ");
        try {
            int a = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Ingrese el segundo entero: ");
            int b = Integer.parseInt(sc.nextLine().trim());
            int r = mcd(Math.abs(a), Math.abs(b));
            System.out.println("MCD: " + r);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }
    }

    public static int mcd(int a, int b) {
        if (b == 0) return a;
        return mcd(b, a % b);
    }
}

