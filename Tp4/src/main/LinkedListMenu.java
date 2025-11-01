package Tp4.src.main;

import java.util.Scanner;

import Tp4.src.utils.LinkedList;

public class LinkedListMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

    //Para otro tipo de dato, cambiar los Integer por el tipo deseado
    LinkedList<Integer> list = new LinkedList<>();
        boolean goOut = false;

        while (!goOut) {
            printMenu();
            int option = readInteger("Seleccione una opcion: ");
            System.out.println();

            try {
                switch (option) {
                    case 1: // Agregar al inicio
                        list.addBeginning(readInteger("Valor a agregar al inicio: "));
                        System.out.println("OK: agregado al inicio.");
                        break;
                    case 2: // Agregar al final
                        list.addEnd(readInteger("Valor a agregar al final: "));
                        System.out.println("OK: agregado al final.");
                        break;
                    case 3: // Agregar en posición
                        int valor = readInteger("Valor a agregar: ");
                        int pos = readInteger("Posicion (0.." + (list.getSize() - 1) + "): ");
                        list.addPosition(valor, pos);
                        System.out.println("OK: agregado en posicion " + pos + ".");
                        break;
                    case 4: // Eliminar al inicio
                        System.out.println(list.deleteBeginning() ? "OK: eliminado al inicio." : "La lista esta vacia.");
                        break;
                    case 5: // Eliminar al final
                        System.out.println(list.deleteEnd() ? "OK: eliminado al final." : "La lista esta vacia.");
                        break;
                    case 6: // Eliminar en posición
                        int posDel = readInteger("Posicion a eliminar (0.." + (list.getSize() - 1) + "): ");
                        try {
                            boolean ok = list.deletePosition(posDel);
                            System.out.println(ok ? "OK: eliminado en posicion " + posDel + "." : "Posicion invalida o lista vacia.");
                        } catch (IndexOutOfBoundsException ex) {
                            System.out.println("Error: " + ex.getMessage());
                        }
                        break;
                    case 7: // Eliminar por valor
                        int valDel = readInteger("Valor a eliminar: ");
                        System.out.println(list.deleteValue(valDel) ? "OK: valor eliminado." : "Valor no encontrado.");
                        break;
                    case 8: // Eliminar duplicados
                        System.out.println(list.deleteDuplicates() ? "OK: duplicados eliminados (si habia)." : "La lista está vacia.");
                        break;
                    case 9: // Buscar valor
                        int valBus = readInteger("Valor a buscar: ");
                        System.out.println(list.search(valBus) ? "Encontrado." : "No encontrado.");
                        break;
                    case 10: // Invertir lista
                        list.invest();
                        System.out.println("OK: lista invertida.");
                        System.out.println("Lista actual: " + list);
                        break;
                    case 11: // Mostrar lista
                        if(list.isEmpty()) {
                            System.out.println("La lista esta vacia.");
                        } else{
                            System.out.println("Lista: " + list);
                        }
                        break;
                    case 12: // Tamaño
                        System.out.println("Tamanio: " + list.getSize());
                        break;
                    case 13: // ¿Vacía?
                        System.out.println(list.isEmpty() ? "Si, está vacia." : "No, tiene elementos.");
                        break;
                    case 0:
                        goOut = true;
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion invalida. Intente nuevamente.");
                }
            } catch (IndexOutOfBoundsException ex) {
                // Captura de addPosition cuando la posición no es válida
                System.out.println("Error: " + ex.getMessage());
            }

            if (!goOut) {
                System.out.println();
            }
        }

        // Cerrar scanner al finalizar
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("================= MENU LinkedList =================");
        System.out.println("1) Agregar al inicio");
        System.out.println("2) Agregar al final");
        System.out.println("3) Agregar en posicion");
        System.out.println("4) Eliminar al inicio");
        System.out.println("5) Eliminar al final");
        System.out.println("6) Eliminar en posicion");
        System.out.println("7) Eliminar por valor");
        System.out.println("8) Eliminar duplicados");
        System.out.println("9) Buscar valor");
        System.out.println("10) Invertir lista");
        System.out.println("11) Mostrar lista");
        System.out.println("12) Mostrar tamanio");
        System.out.println("13) ¿Esta vacia?");
        System.out.println("0) Salir");
        System.out.println("===================================================");
    }

    private static int readInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Ingrese un numero entero.");
            }
        }
    }
}
