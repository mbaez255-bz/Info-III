package Tp4.src.main;

import java.util.Scanner;

import Tp4.src.utils.LinkedList;

public class LinkedListMenuGeneric {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Seleccione el tipo de dato para la lista:");
        System.out.println("1) Integer");
        System.out.println("2) Double");
        System.out.println("3) String");
        System.out.println("0) Salir");

        int type = readInt("Opcion: ");
        System.out.println();

        switch (type) {
            case 1: // Integer
                runIntegerMenu();
                break;
            case 2: // Double
                runDoubleMenu();
                break;
            case 3: // String
                runStringMenu();
                break;
            case 0:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opcion invalida.");
        }

        scanner.close();
    }

    // ======================= Menús por tipo =======================

    private static void runIntegerMenu() {
        LinkedList<Integer> list = new LinkedList<>();
        boolean goOut = false;
        while (!goOut) {
            printMenu("Integer");
            int option = readInt("Seleccione una opcion: ");
            System.out.println();
            try {
                switch (option) {
                    case 1:
                        list.addBeginning(readInt("Valor a agregar al inicio: "));
                        System.out.println("OK: agregado al inicio.");
                        break;
                    case 2:
                        list.addEnd(readInt("Valor a agregar al final: "));
                        System.out.println("OK: agregado al final.");
                        break;
                    case 3:
                        int v = readInt("Valor a agregar: ");
                        int pos = readInt("Posicion (0.." + (list.getSize() - 1) + "): ");
                        list.addPosition(v, pos);
                        System.out.println("OK: agregado en posicion " + pos + ".");
                        break;
                    case 4:
                        System.out.println(list.deleteBeginning() ? "OK: eliminado al inicio." : "La lista esta vacia.");
                        break;
                    case 5:
                        System.out.println(list.deleteEnd() ? "OK: eliminado al final." : "La lista esta vacia.");
                        break;
                    case 6:
                        int posDel = readInt("Posicion a eliminar (0.." + (list.getSize() - 1) + "): ");
                        boolean ok = list.deletePosition(posDel);
                        System.out.println(ok ? "OK: eliminado en posicion " + posDel + "." : "Posicion invalida o lista vacia.");
                        break;
                    case 7:
                        int valDel = readInt("Valor a eliminar: ");
                        System.out.println(list.deleteValue(valDel) ? "OK: valor eliminado." : "Valor no encontrado.");
                        break;
                    case 8:
                        System.out.println(list.deleteDuplicates() ? "OK: duplicados eliminados (si habia)." : "La lista esta vacia.");
                        break;
                    case 9:
                        int valBus = readInt("Valor a buscar: ");
                        System.out.println(list.search(valBus) ? "Encontrado." : "No encontrado.");
                        break;
                    case 10:
                        list.invest();
                        System.out.println("OK: lista invertida.");
                        System.out.println("Lista actual: " + list);
                        break;
                    case 11:
                        System.out.println(list.isEmpty() ? "La lista esta vacia." : ("Lista: " + list));
                        break;
                    case 12:
                        System.out.println("Tamanio: " + list.getSize());
                        break;
                    case 13:
                        System.out.println(list.isEmpty() ? "Si, esta vacia." : "No, tiene elementos.");
                        break;
                    case 0:
                        goOut = true;
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                }
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            if (!goOut) System.out.println();
        }
    }

    private static void runDoubleMenu() {
        LinkedList<Double> list = new LinkedList<>();
        boolean goOut = false;
        while (!goOut) {
            printMenu("Double");
            int option = readInt("Seleccione una opcion: ");
            System.out.println();
            try {
                switch (option) {
                    case 1:
                        list.addBeginning(readDouble("Valor a agregar al inicio: "));
                        System.out.println("OK: agregado al inicio.");
                        break;
                    case 2:
                        list.addEnd(readDouble("Valor a agregar al final: "));
                        System.out.println("OK: agregado al final.");
                        break;
                    case 3:
                        double v = readDouble("Valor a agregar: ");
                        int pos = readInt("Posicion (0.." + list.getSize() + "): ");
                        list.addPosition(v, pos);
                        System.out.println("OK: agregado en posición " + pos + ".");
                        break;
                    case 4:
                        System.out.println(list.deleteBeginning() ? "OK: eliminado al inicio." : "La lista esta vacia.");
                        break;
                    case 5:
                        System.out.println(list.deleteEnd() ? "OK: eliminado al final." : "La lista esta vacia.");
                        break;
                    case 6:
                        int posDel = readInt("Posicion a eliminar (0.." + (list.getSize() - 1) + "): ");
                        boolean ok = list.deletePosition(posDel);
                        System.out.println(ok ? "OK: eliminado en posicion " + posDel + "." : "Posicion invalida o lista vacia.");
                        break;
                    case 7:
                        double valDel = readDouble("Valor a eliminar (primera ocurrencia): ");
                        System.out.println(list.deleteValue(valDel) ? "OK: valor eliminado." : "Valor no encontrado.");
                        break;
                    case 8:
                        System.out.println(list.deleteDuplicates() ? "OK: duplicados eliminados (si habia)." : "La lista esta vacia.");
                        break;
                    case 9:
                        double valBus = readDouble("Valor a buscar: ");
                        System.out.println(list.search(valBus) ? "Encontrado." : "No encontrado.");
                        break;
                    case 10:
                        list.invest();
                        System.out.println("OK: lista invertida.");
                        System.out.println("Lista actual: " + list);
                        break;
                    case 11:
                        System.out.println(list.isEmpty() ? "La lista esta vacia." : ("Lista: " + list));
                        break;
                    case 12:
                        System.out.println("Tamanio: " + list.getSize());
                        break;
                    case 13:
                        System.out.println(list.isEmpty() ? "Si, esta vacia." : "No, tiene elementos.");
                        break;
                    case 0:
                        goOut = true;
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                }
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            if (!goOut) System.out.println();
        }
    }

    private static void runStringMenu() {
        LinkedList<String> list = new LinkedList<>();
        boolean goOut = false;
        while (!goOut) {
            printMenu("String");
            int option = readInt("Seleccione una opcion: ");
            System.out.println();
            try {
                switch (option) {
                    case 1:
                        list.addBeginning(readLine("Valor a agregar al inicio: "));
                        System.out.println("OK: agregado al inicio.");
                        break;
                    case 2:
                        list.addEnd(readLine("Valor a agregar al final: "));
                        System.out.println("OK: agregado al final.");
                        break;
                    case 3:
                        String v = readLine("Valor a agregar: ");
                        int pos = readInt("Posicion (0.." + list.getSize() + "): ");
                        list.addPosition(v, pos);
                        System.out.println("OK: agregado en posición " + pos + ".");
                        break;
                    case 4:
                        System.out.println(list.deleteBeginning() ? "OK: eliminado al inicio." : "La lista esta vacia.");
                        break;
                    case 5:
                        System.out.println(list.deleteEnd() ? "OK: eliminado al final." : "La lista esta vacia.");
                        break;
                    case 6:
                        int posDel = readInt("Posicion a eliminar (0.." + (list.getSize() - 1) + "): ");
                        boolean ok = list.deletePosition(posDel);
                        System.out.println(ok ? "OK: eliminado en posicion " + posDel + "." : "Posicion invalida o lista vacia.");
                        break;
                    case 7:
                        String valDel = readLine("Valor a eliminar (primera ocurrencia): ");
                        System.out.println(list.deleteValue(valDel) ? "OK: valor eliminado." : "Valor no encontrado.");
                        break;
                    case 8:
                        System.out.println(list.deleteDuplicates() ? "OK: duplicados eliminados (si habia)." : "La lista esta vacia.");
                        break;
                    case 9:
                        String valBus = readLine("Valor a buscar: ");
                        System.out.println(list.search(valBus) ? "Encontrado." : "No encontrado.");
                        break;
                    case 10:
                        list.invest();
                        System.out.println("OK: lista invertida.");
                        System.out.println("Lista actual: " + list);
                        break;
                    case 11:
                        System.out.println(list.isEmpty() ? "La lista esta vacia." : ("Lista: " + list));
                        break;
                    case 12:
                        System.out.println("Tamanio: " + list.getSize());
                        break;
                    case 13:
                        System.out.println(list.isEmpty() ? "Si, esta vacia." : "No, tiene elementos.");
                        break;
                    case 0:
                        goOut = true;
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                }
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            if (!goOut) System.out.println();
        }
    }

    // ======================= Utilitarios =======================

    private static void printMenu(String tipo) {
        System.out.println("================= MENU LinkedList<" + tipo + "> =================");
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

    private static int readInt(String prompt) {
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

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Ingrese un numero (formato: 12.34).");
            }
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
