
package utils;

import java.util.Scanner;

/**
 * Clase utilitaria para calcular el Máximo Común Divisor (MCD) usando el algoritmo de Euclides.
 */
public class MCD {

    /**
     * Solicita dos enteros al usuario y muestra el MCD calculado.
     * @param sc Scanner para entrada de datos
     */
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

    /**
     * Calcula el MCD de dos números usando recursividad.
     * @param a Primer número
     * @param b Segundo número
     * @return MCD de a y b
     */
    public static int mcd(int a, int b) {
        if (b == 0) return a;
        return mcd(b, a % b);
    }
}

/*
Secuencia de ejemplo:
mcd(24, 36):
    b != 0 → mcd(36, 24)
    b != 0 → mcd(24, 12)
    b != 0 → mcd(12, 0)
    b == 0 → retorna 12
*/