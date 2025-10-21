package Tp3.app;

// Si usas packages, asegúrate que la estructura de carpetas coincida. Ej: package Tp3.app;

import java.util.Scanner;
import Tp3.utils.*;

/**
 * Aplicación principal para gestionar pedidos de una pizzería.
 */
public class app {
      public static void main(String[] args) {
        Pizzeria pizzeria = new Pizzeria();
        try (Scanner consola = new Scanner(System.in)) {
            int opcion;
            do {
                mostrarMenu();
                opcion = leerOpcion(consola);
                // El switch evalúa la opción ingresada y ejecuta la acción correspondiente
                switch (opcion) {
                    case 1 -> agregarPedido(pizzeria, consola); // Agrega un nuevo pedido
                    case 2 -> eliminarPedido(pizzeria, consola); // Elimina un pedido por índice
                    case 3 -> pizzeria.listarPedidos(); // Lista todos los pedidos
                    case 4 -> mostrarTiempoOrdenacion("tiempo", () -> pizzeria.ordenarPorTiempo()); // Ordena por tiempo
                    case 5 -> mostrarTiempoOrdenacion("precio", () -> pizzeria.ordenarPorPrecio()); // Ordena por precio
                    case 6 -> mostrarTiempoOrdenacion("nombre", () -> pizzeria.ordenarPorNombre()); // Ordena por nombre
                    case 7 -> System.out.println("Hasta luego..."); // Sale del programa
                    default -> System.out.println("Opción inválida. Intente nuevamente."); // Opción no válida
                }
            } while (opcion != 7);
        }
    }

    /**
     * Muestra el menú de opciones por consola.
     */
    
    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ PIZZERÍA ---");
        System.out.println("1. Agregar pedido");
        System.out.println("2. Eliminar pedido");
        System.out.println("3. Listar pedidos");
        System.out.println("4. Ordenar por tiempo de preparación (Inserción)");
        System.out.println("5. Ordenar por precio total (Shellsort)");
        System.out.println("6. Ordenar por nombre de cliente (Quicksort)");
        System.out.println("7. Salir");
        System.out.print("¿Qué opción desea realizar? ");
    }

    /**
     * Lee la opción elegida por el usuario y controla errores de entrada.
     */
    
    private static int leerOpcion(Scanner consola) {
        int op = -1;
        try {
            op = Integer.parseInt(consola.nextLine().trim());
        } catch (NumberFormatException ex) {
            // Devuelve -1 para opción inválida
        }
        return op;
    }

    /**
     * Solicita los datos y agrega un nuevo pedido a la pizzería.
     */
    
    private static void agregarPedido(Pizzeria pizzeria, Scanner consola) {
        System.out.print("Nombre del cliente: ");
        String nombre = consola.nextLine();
        int tiempo = leerEntero(consola, "Tiempo de preparación (min): ");
        double precio = leerDouble(consola, "Precio total: ");
        pizzeria.agregarPedido(new Pedido(nombre, tiempo, precio));
    }

    /**
     * Elimina un pedido según el índice ingresado por el usuario.
     */
    
    private static void eliminarPedido(Pizzeria pizzeria, Scanner consola) {
        pizzeria.listarPedidos();
        int indice = leerEntero(consola, "Número de pedido a eliminar: ") - 1;
        pizzeria.eliminarPedido(indice);
    }

    /**
     * Lee un número entero desde consola, mostrando un mensaje y validando la entrada.
     */
    
    private static int leerEntero(Scanner consola, String mensaje) {
        int valor = -1;
        boolean valido = false;
        do {
            System.out.print(mensaje);
            String input = consola.nextLine();
            try {
                valor = Integer.parseInt(input.trim());
                valido = true;
            } catch (NumberFormatException ex) {
                System.out.println("Valor inválido. Intente nuevamente.");
            }
        } while (!valido);
        return valor;
    }

    /**
     * Lee un número decimal (double) desde consola, mostrando un mensaje y validando la entrada.
     */
    
    private static double leerDouble(Scanner consola, String mensaje) {
        double valor = -1;
        boolean valido = false;
        do {
            System.out.print(mensaje);
            String input = consola.nextLine();
            try {
                valor = Double.parseDouble(input.trim());
                valido = true;
            } catch (NumberFormatException ex) {
                System.out.println("Valor inválido. Intente nuevamente.");
            }
        } while (!valido);
        return valor;
    }

    /**
     * Ejecuta el método de ordenación recibido, mide el tiempo que tarda y muestra el resultado.
     * @param criterio Descripción del criterio de ordenación (por ejemplo, "tiempo", "precio", "nombre")
     * @param metodo   Método de ordenación a ejecutar (como una función lambda)
     */
    private static void mostrarTiempoOrdenacion(String criterio, Runnable metodo) {
        long tiempo = TiempoOrdenamiento.medirTiempo(metodo);
        System.out.println("Pedidos ordenados por " + criterio + ". Tiempo: " + tiempo + " ms");
    }
}

