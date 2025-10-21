package main;

import utils.*;
import java.util.Scanner;

/**
 * Clase principal del proyecto. Muestra un menú interactivo para ejecutar distintos ejercicios.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        // Bucle principal del menú
        do {
            mostrarMenu();
            opcion = leerOpcion(sc);

            // El switch ejecuta el ejercicio correspondiente según la opción elegida
            switch (opcion) {
                case 1 -> BusquedaArreglo.ejecutar(sc); // Búsqueda en arreglo
                case 2 -> CadenaCapicua.ejecutar(sc);   // Cadena capicúa
                case 3 -> ConteoDigitos.ejecutar(sc);   // Conteo de dígitos
                case 4 -> ConversionBinaria.ejecutar(sc); // Conversión binaria
                case 5 -> InvertirCadena.ejecutar(sc);  // Invertir cadena
                case 6 -> MCD.ejecutar(sc);             // Máximo común divisor
                case 7 -> NumerosFibonacci.ejecutar(sc);// Serie Fibonacci
                case 8 -> SumaElementos.ejecutar(sc);   // Suma de elementos
                case 0 -> System.out.println("Saliendo del programa..."); // Salir
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);

        sc.close();
    }

    /**
     * Muestra el menú de opciones por consola.
     */
    private static void mostrarMenu() {
        System.out.println("\n========= MENÚ EJERCICIOS =========");
        System.out.println("1. Búsqueda en arreglo");
        System.out.println("2. Cadena capicúa");
        System.out.println("3. Conteo de dígitos");
        System.out.println("4. Conversión binaria");
        System.out.println("5. Invertir cadena");
        System.out.println("6. Máximo común divisor (MCD)");
        System.out.println("7. Serie Fibonacci");
        System.out.println("8. Suma de elementos");
        System.out.println("0. Salir");
        System.out.print("Ingrese una opción: ");
    }

    /**
     * Lee la opción elegida por el usuario y controla errores de entrada.
     */
    private static int leerOpcion(Scanner sc) {
        int op = -1;
        try {
            op = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException ex) {
            // Devuelve -1 para opción inválida
        }
        return op;
    }
}
