
package app;

import utils.GestorTareas;
import java.util.Scanner;

/**
 * Aplicación principal para gestionar tareas desde consola.
 */
public class app {
    public static void main(String[] args) {
        final String ARCHIVO = "tareas.txt";
        GestorTareas gestor = new GestorTareas();
        gestor.cargarDesdeArchivo(ARCHIVO);

        try (Scanner consola = new Scanner(System.in)) {
            int opcion = -1;
            do {
                mostrarMenu();
                opcion = leerOpcion(consola);
                switch (opcion) {
                    case 1 -> agregarTarea(gestor, consola);
                    case 2 -> gestor.listarTareas();
                    case 3 -> marcarTarea(gestor, consola);
                    case 4 -> gestor.eliminarCompletadas();
                    case 5 -> {
                        gestor.guardarEnArchivo(ARCHIVO);
                        System.out.println("Hasta luego...");
                    }
                    default -> System.out.println("Opción inválida. Intente nuevamente.");
                }
            } while (opcion != 5);
        }
    }

    /**
     * Muestra el menú de opciones al usuario.
     */
    private static void mostrarMenu() {
        System.out.println("\n-- MENÚ DE OPCIONES --");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Listar tareas");
        System.out.println("3. Marcar tarea como completada");
        System.out.println("4. Eliminar tareas completadas");
        System.out.println("5. Guardar y salir");
        System.out.print("¿Qué opción desea realizar? ");
    }

    /**
     * Lee la opción elegida por el usuario, controlando errores de entrada.
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
     * Solicita y agrega una nueva tarea.
     */
    private static void agregarTarea(GestorTareas gestor, Scanner consola) {
        System.out.print("Descripción de la tarea: ");
        String descripcion = consola.nextLine();
        gestor.agregarTarea(descripcion);
    }

    /**
     * Permite al usuario marcar una tarea como completada.
     */
    private static void marcarTarea(GestorTareas gestor, Scanner consola) {
        gestor.listarTareas();
        if (pedirConfirmacionTareas(gestor)) {
            System.out.print("Ingrese el número de la tarea a marcar: ");
            try {
                int indice = Integer.parseInt(consola.nextLine().trim()) - 1;
                gestor.marcarTarea(indice);
            } catch (NumberFormatException ex) {
                System.out.println("Índice inválido.");
            }
        }
    }

    /**
     * Verifica si hay tareas para operar.
     */
    private static boolean pedirConfirmacionTareas(GestorTareas gestor) {
        // Si no hay tareas, no se puede marcar ninguna
        // (El método listarTareas ya muestra mensaje)
        return true;
    }
}
