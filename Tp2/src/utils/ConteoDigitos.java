
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
/*
Secuencia de ejemplo:
contarDigitos(1234):
    cnt=0, n=1234
    n=1234 → cnt=1, n=123
    n=123  → cnt=2, n=12
    n=12   → cnt=3, n=1
    n=1    → cnt=4, n=0
Devuelve: 4
*/
// Ejemplo de funcionamiento:
/*
Entrada:
    Ingrese un número entero: 12345
Secuencia:
    - contarDigitos(12345): 5 dígitos
Salida:
    Cantidad de dígitos: 5
*/