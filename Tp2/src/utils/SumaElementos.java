package utils;

import java.util.Scanner;
/**
 * Suma los elementos de un arreglo y muestra suma y promedio.
 */
public class SumaElementos {
    public static void ejecutar(Scanner sc) {
        System.out.println("*** SUMA DE ELEMENTOS ***");
        try {
            System.out.print("¿Cuántos elementos tendrá el arreglo? ");
            int n = Integer.parseInt(sc.nextLine().trim());
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                System.out.print("Elemento[" + i + "]: ");
                arr[i] = Integer.parseInt(sc.nextLine().trim());
            }
            int suma = 0;
            for (int v : arr) suma += v;
            double promedio = n == 0 ? 0 : (double) suma / n;
            System.out.println("Suma: " + suma);
            System.out.println("Promedio: " + promedio);
        } catch (Exception e) {
            System.out.println("Entrada inválida.");
        }
    }
}
