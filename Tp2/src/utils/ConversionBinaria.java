package utils;

import java.util.Scanner;
/**
 * Conversión de número decimal a binario (iterativa y recursiva).
 */
public class ConversionBinaria {
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
