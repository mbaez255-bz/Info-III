package Tp5.main;

import java.util.Scanner;
import Tp5.utils.ArbolAVL;

/**
 * Menú interactivo para probar la clase ArbolAVL .
 */
public class Main {
    private static final Scanner SC = new Scanner(System.in, "UTF-8");

    public static void main(String[] args) {
        ArbolAVL<String> arbol = new ArbolAVL<>();
        while (true) {
            System.out.println("\n***************** OPERACIONES CON AVLTree *****************\n");
            System.out.println("1. Insertar nodo");
            System.out.println("2. Buscar por clave");
            System.out.println("3. Eliminar por clave");
            System.out.println("4. Mostrar recorrido in-order");
            System.out.println("5. Mostrar altura del árbol");
            System.out.println("6. Volver al menú principal");
            System.out.println("7. Visualizar árbol");
            int op = leerEntero("Seleccione una opción (1-7): ");
            System.out.println("\n");
            switch (op) {
                case 1 -> {
                    String clave = leerTexto("Ingrese la clave a insertar: ");
                    arbol.insertar(clave);
                    System.out.println("Insertado: " + clave);
                    leerTexto("Presione ENTER para continuar...");
                }
                case 2 -> {
                    String clave = leerTexto("Ingrese la clave a buscar: ");
                    boolean existe = arbol.buscar(clave);
                    System.out.println(existe ? "Encontrado: " + clave : "No encontrado: " + clave);
                    leerTexto("Presione ENTER para continuar...");
                }
                case 3 -> {
                    String clave = leerTexto("Ingrese la clave a eliminar: ");
                    arbol.eliminar(clave);
                    System.out.println("Eliminado (si existía): " + clave);
                    leerTexto("Presione ENTER para continuar...");
                }
                case 4 -> {
                    System.out.println("Recorrido in-order: " + arbol.recorridoEnOrden());
                    leerTexto("Presione ENTER para continuar...");
                }
                case 5 -> {
                    System.out.println("Altura del árbol: " + arbol.obtenerAltura());
                    leerTexto("Presione ENTER para continuar...");
                }
                case 6 -> {
                    // Volver al menú principal: salir de este Main
                    SC.close();
                    return;
                }
                case 7 -> {
                    System.out.println("Visualización del árbol:");
                    arbol.imprimirArbol();
                    leerTexto("Presione ENTER para continuar...");
                }
                default -> {
                    System.out.println("Opción no válida.");
                    leerTexto("Presione ENTER para continuar...");
                }
            }
        }
    }

     private static int leerEntero(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = SC.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (Exception e) {
                System.out.println("Entrada no válida. Intente de nuevo.");
            }
        }
    }

    private static String leerTexto(String prompt) {
        System.out.print(prompt);
        return SC.nextLine();
    }
}
