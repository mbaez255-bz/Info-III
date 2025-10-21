
package utils;

import java.util.Scanner;

/**
 * Clase utilitaria para sumar los elementos de un arreglo y mostrar suma y promedio.
 */
public class SumaElementos {

    /**
     * Solicita al usuario la cantidad de elementos, los lee, suma y calcula el promedio.
     * Muestra los resultados por consola.
     * @param sc Scanner para entrada de datos
     */
    public static void ejecutar(Scanner sc) {
        System.out.println("*** SUMA DE ELEMENTOS ***");
        try {
            // Leer cantidad de elementos
            System.out.print("¿Cuántos elementos tendrá el arreglo? ");
            int n = Integer.parseInt(sc.nextLine().trim());
            int[] arr = new int[n];
            // Leer cada elemento del arreglo
            for (int i = 0; i < n; i++) {
                System.out.print("Elemento[" + i + "]: ");
                arr[i] = Integer.parseInt(sc.nextLine().trim());
            }
            // Calcular suma
            int suma = 0;
            for (int v : arr) suma += v;
            // Calcular promedio
            double promedio = n == 0 ? 0 : (double) suma / n;
            System.out.println("Suma: " + suma);
            System.out.println("Promedio: " + promedio);
        } catch (Exception e) {
            System.out.println("Entrada inválida.");
        }
    }
}
/*
Secuencia de ejemplo:
arr = [5, 7, 8]
suma = 0
suma += 5 → suma = 5
suma += 7 → suma = 12
suma += 8 → suma = 20
promedio = suma / 3 = 6.666...
*/