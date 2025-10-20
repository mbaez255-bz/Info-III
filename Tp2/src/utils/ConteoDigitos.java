package utils;

import java.util.Scanner;
/**
 * Cuenta la cantidad de dígitos de un número entero (absoluto).
 */

public class ConteoDigitos {
    public static void ejecutar(Scanner sc) {
        System.out.println("*** CONTEO DE DÍGITOS ***");
        System.out.print("Ingrese un número entero: ");
        try {
            long n = Long.parseLong(sc.nextLine().trim());
            int dig = contarDigitos(Math.abs(n));
            System.out.println("Cantidad de dígitos: " + dig);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }
    }

    public static int contarDigitos(long n) {
        if (n == 0) return 1;
        int cnt = 0;
        while (n > 0) {
            cnt++;
            n /= 10;
        }
        return cnt;
    }
}
