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
            System.out.println("\n***************** OPERACIONES CON AVLTree *****************");
            System.out.println("1. Insertar nodo");
            System.out.println("2. Buscar por clave");
            System.out.println("3. Eliminar por clave");
            System.out.println("4. Salir");
            int op = leerEntero("Seleccione una opción (1-4): ");
            System.out.println("----------------------------");
            switch (op) {
                case 1 -> {
                    String clave = leerTexto("Ingrese la clave a insertar: ");
                    arbol.insertar(clave);
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
                    // Salir del menú
                    SC.close();
                    return;
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
