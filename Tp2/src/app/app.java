package app;

import utils.*;

import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n========= MENÚ EJERCICIOS=========");
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
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> BusquedaArreglo.ejecutar(sc);
                case 2 -> CadenaCapicua.ejecutar(sc);
                case 3 -> ConteoDigitos.ejecutar(sc);
                case 4 -> ConversionBinaria.ejecutar(sc);
                case 5 -> InvertirCadena.ejecutar(sc);
                case 6 -> MCD.ejecutar(sc);
                case 7 -> NumerosFibonacci.ejecutar(sc);
                case 8 -> SumaElementos.ejecutar(sc);
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
