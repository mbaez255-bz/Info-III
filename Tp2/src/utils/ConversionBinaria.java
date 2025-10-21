
package utils;

import java.util.Scanner;

/**
 * Clase utilitaria para convertir un número decimal a binario.
 */
public class ConversionBinaria {

    /**
     * Solicita un número entero al usuario y muestra su representación binaria.
     * @param sc Scanner para entrada de datos
     */
    public static void ejecutar(Scanner sc) {
        System.out.println("*** CONVERSIÓN A BINARIO ***");
        System.out.print("Ingrese un número entero positivo: ");
        try {
            int n = Integer.parseInt(sc.nextLine().trim());
            if (n < 0) {
                System.out.println("Ingrese un número no negativo.");
                return;
            }
            String bin = aBinario(n);
            System.out.println("Binario: " + bin);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }
    }

    /**
     * Convierte un número decimal a su representación binaria como String.
     * @param n Número decimal
     * @return Cadena con el número en binario
     */
    public static String aBinario(int n) {
        if (n == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.insert(0, (n % 2));
            n /= 2;
        }
        return sb.toString();
    }
}
/*
Secuencia de ejemplo:
aBinario(13):
    n=13 → sb="1" (13%2)
    n=6  → sb="01" (6%2)
    n=3  → sb="101" (3%2)
    n=1  → sb="1101" (1%2)
Devuelve: "1101"
*/