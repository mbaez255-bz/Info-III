package utils;

import java.util.Scanner;
/**
 * Búsqueda en arreglo (recursiva) y funciones auxiliares.
 */
public class BusquedaArreglo {

    public static void ejecutar(Scanner sc) {
        System.out.println("*** BUSCAR EN UN ARREGLO ***");
        try {
            int[] arreglo = leerArreglo(sc);
            mostrarArreglo(arreglo);

            System.out.print("Ingrese el número a buscar: ");
            int objetivo = Integer.parseInt(sc.nextLine().trim());

            boolean encontrado = buscarArreglo(arreglo, objetivo, 0);
            if (encontrado) {
                System.out.println("El número " + objetivo + " si está en el arreglo");
            } else {
                System.out.println("El número " + objetivo + " no está en el arreglo");
            }

            int suma = sumarArreglo(arreglo, 0);
            double promedio = promedioArreglo(arreglo, 0, 0);
            System.out.println("Suma de elementos: " + suma);
            System.out.println("Promedio de elementos: " + promedio);

        } catch (Exception e) {
            System.out.println("Error: Ingrese valores válidos.");
        }
    }

    public static int[] leerArreglo(Scanner sc) {
        System.out.print("¿Cuántos elementos tendrá el arreglo? ");
        int n = Integer.parseInt(sc.nextLine().trim());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Elemento[" + i + "]: ");
            arr[i] = Integer.parseInt(sc.nextLine().trim());
        }
        return arr;
    }

    public static void mostrarArreglo(int[] arreglo) {
        System.out.print("Arreglo: [");
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print(arreglo[i] + (i < arreglo.length - 1 ? ", " : ""));
            //Si i no es el último índice, pone ", ".Si es el último, ""
        }
        System.out.println("]");
    }

    //prueba mi rama

    public static boolean buscarArreglo(int[] arreglo, int objetivo, int i) {
        //llegmos al final
        if (i >= arreglo.length) return false;
        // encontramos el elemento
        if (arreglo[i] == objetivo) return true;
        return buscarArreglo(arreglo, objetivo, i + 1);
    }

    public static int sumarArreglo(int[] arreglo, int i) {
        if (i >= arreglo.length) return 0;
        return arreglo[i] + sumarArreglo(arreglo, i + 1);
    }

    public static double promedioArreglo(int[] arreglo, int i, int sumaPrevia) {
        if (i == arreglo.length) {
            return (double) sumaPrevia / arreglo.length;
        }
        return promedioArreglo(arreglo, i + 1, sumaPrevia + arreglo[i]);
    }
        /*buscar([3,5,7,9], 7, 0)
        ¿posición 0 == 4? No → sigue
        ¿3 == 7? No → sigue
         buscar([3,5,7,9], 7, 1)*/
}
