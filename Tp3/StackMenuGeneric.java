package Tp3;

import java.util.Scanner;

public class StackMenuGeneric {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Seleccione el tipo de dato para la pila:");
        System.out.println("1) Integer");
        System.out.println("2) Double");
        System.out.println("3) String");
        System.out.println("0) Salir");

        int type = readInt("Opcion: ");
        System.out.println();

        if (type == 0) {
            System.out.println("Saliendo...");
            scanner.close();
            return;
        }

        System.out.print("Ingrese la capacidad de la pila: ");
        int capacity = readInt();
        System.out.println();

        switch (type) {
            case 1: // Integer
                runIntegerMenu(capacity);
                break;
            case 2: // Double
                runDoubleMenu(capacity);
                break;
            case 3: // String
                runStringMenu(capacity);
                break;
            default:
                System.out.println("Opcion invalida.");
        }

        scanner.close();
    }

    // ======================= Menus por tipo =======================

    private static void runIntegerMenu(int capacity) {
        Stack<Integer> stack = new Stack<>(capacity);
        boolean goOut = false;
        while (!goOut) {
            printMenu("Integer", stack);
            int option = readInt("Seleccione una opcion: ");
            System.out.println();

            switch (option) {
                case 1: // Push
                    if(stack.isFull()) {
                        System.out.println("Error: La pila esta llena.");
                        break;
                    }else{
                        int value = readInt("Valor a agregar: ");
                        stack.push(value);
                        System.out.println("OK: elemento agregado a la pila.");
                    }
                    break;
                case 2: // Pop
                    Integer removed = stack.pop();
                    if (removed != null) {
                        System.out.println("OK: elemento " + removed + " eliminado de la pila.");
                    } else {
                        System.out.println("Error: La pila esta vacia.");
                    }
                    break;
                case 3: // Peek
                    Integer topElem = stack.peek();
                    if (topElem != null) {
                        System.out.println("Elemento en el tope: " + topElem);
                    } else {
                        System.out.println("La pila esta vacia.");
                    }
                    break;
                case 4: // Buscar
                    if(stack.isEmpty()) {
                        System.out.println("La pila esta vacia.");
                        break;
                    }
                    int searchVal = readInt("Valor a buscar: ");
                    System.out.println(stack.search(searchVal) ? "Encontrado." : "No encontrado.");
                    break;
                case 5: // Mostrar pila
                    if (!stack.isEmpty()) {
                        System.out.println("Pila: " + stack);
                    } else {
                        System.out.println("La pila esta vacia.");
                    }
                    break;
                case 6: // Tamaño
                    System.out.println("Tamanio: " + stack.getSize() + "/" + stack.getCapacity());
                    break;
                case 7: // ¿Vacía?
                    System.out.println(stack.isEmpty() ? "Si, esta vacia." : "No, tiene elementos.");
                    break;
                case 8: // ¿Llena?
                    System.out.println(stack.isFull() ? "Si, esta llena." : "No, tiene espacio.");
                    break;
                case 9: // Limpiar
                    stack.clear();
                    System.out.println("OK: pila limpiada.");
                    break;
                case 10: // Invertir
                    if(stack.isEmpty()) {
                        System.out.println("La pila esta vacia.");
                    } else {
                        stack.reverse();
                        System.out.println("OK: pila invertida.");
                        System.out.println("Pila actual: " + stack);
                    }
                    break;
                case 0:
                    goOut = true;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }

            if (!goOut) System.out.println();
        }
    }

    private static void runDoubleMenu(int capacity) {
        Stack<Double> stack = new Stack<>(capacity);
        boolean goOut = false;
        while (!goOut) {
            printMenu("Double", stack);
            int option = readInt("Seleccione una opcion: ");
            System.out.println();

            switch (option) {
                case 1: // Push
                    if(stack.isFull()) {
                        System.out.println("Error: La pila esta llena.");
                        break;
                    }else{
                        double value = readDouble("Valor a agregar: ");
                        stack.push(value);
                        System.out.println("OK: elemento agregado a la pila.");
                    }
                    break;
                case 2: // Pop
                    Double removed = stack.pop();
                    if (removed != null) {
                        System.out.println("OK: elemento " + removed + " eliminado de la pila.");
                    } else {
                        System.out.println("Error: La pila esta vacia.");
                    }
                    break;
                case 3: // Peek
                    Double topElem = stack.peek();
                    if (topElem != null) {
                        System.out.println("Elemento en el tope: " + topElem);
                    } else {
                        System.out.println("La pila esta vacia.");
                    }
                    break;
                case 4: // Buscar
                    if(stack.isEmpty()) {
                        System.out.println("La pila esta vacia.");
                        break;
                    }
                    double searchVal = readDouble("Valor a buscar: ");
                    System.out.println(stack.search(searchVal) ? "Encontrado." : "No encontrado.");
                    break;
                case 5: // Mostrar pila
                    if (!stack.isEmpty()) {
                        System.out.println("Pila: " + stack);
                    } else {
                        System.out.println("La pila esta vacia.");
                    }
                    break;
                case 6: // Tamaño
                    System.out.println("Tamanio: " + stack.getSize() + "/" + stack.getCapacity());
                    break;
                case 7: // ¿Vacía?
                    System.out.println(stack.isEmpty() ? "Si, esta vacia." : "No, tiene elementos.");
                    break;
                case 8: // ¿Llena?
                    System.out.println(stack.isFull() ? "Si, esta llena." : "No, tiene espacio.");
                    break;
                case 9: // Limpiar
                    stack.clear();
                    System.out.println("OK: pila limpiada.");
                    break;
                case 10: // Invertir
                    if(stack.isEmpty()) {
                        System.out.println("La pila esta vacia.");
                    } else {
                        stack.reverse();
                        System.out.println("OK: pila invertida.");
                        System.out.println("Pila actual: " + stack);
                    }
                    break;
                case 0:
                    goOut = true;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }

            if (!goOut) System.out.println();
        }
    }

    private static void runStringMenu(int capacity) {
        Stack<String> stack = new Stack<>(capacity);
        boolean goOut = false;
        while (!goOut) {
            printMenu("String", stack);
            int option = readInt("Seleccione una opcion: ");
            System.out.println();

            switch (option) {
                case 1: // Push
                    if(stack.isFull()) {
                        System.out.println("Error: La pila esta llena.");
                        break;
                    }else{
                        String value = readLine("Valor a agregar: ");
                        stack.push(value);
                        System.out.println("OK: elemento agregado a la pila.");
                    }
                    break;
                case 2: // Pop
                    String removed = stack.pop();
                    if (removed != null) {
                        System.out.println("OK: elemento " + removed + " eliminado de la pila.");
                    } else {
                        System.out.println("Error: La pila esta vacia.");
                    }
                    break;
                case 3: // Peek
                    String topElem = stack.peek();
                    if (topElem != null) {
                        System.out.println("Elemento en el tope: " + topElem);
                    } else {
                        System.out.println("La pila esta vacia.");
                    }
                    break;
                case 4: // Buscar
                    if(stack.isEmpty()) {
                        System.out.println("La pila esta vacia.");
                        break;
                    }
                    String searchVal = readLine("Valor a buscar: ");
                    System.out.println(stack.search(searchVal) ? "Encontrado." : "No encontrado.");
                    break;
                case 5: // Mostrar pila
                    if (!stack.isEmpty()) {
                        System.out.println("Pila: " + stack);
                    } else {
                        System.out.println("La pila esta vacia.");
                    }
                    break;
                case 6: // Tamaño
                    System.out.println("Tamanio: " + stack.getSize() + "/" + stack.getCapacity());
                    break;
                case 7: // ¿Vacía?
                    System.out.println(stack.isEmpty() ? "Si, esta vacia." : "No, tiene elementos.");
                    break;
                case 8: // ¿Llena?
                    System.out.println(stack.isFull() ? "Si, esta llena." : "No, tiene espacio.");
                    break;
                case 9: // Limpiar
                    stack.clear();
                    System.out.println("OK: pila limpiada.");
                    break;
                case 10: // Invertir
                    if(stack.isEmpty()) {
                        System.out.println("La pila esta vacia.");
                    } else {
                        stack.reverse();
                        System.out.println("OK: pila invertida.");
                        System.out.println("Pila actual: " + stack);
                    }
                    break;
                case 0:
                    goOut = true;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }

            if (!goOut) System.out.println();
        }
    }

    // ======================= Utilitarios =======================

    private static void printMenu(String tipo, Stack<?> stack) {
        System.out.println("================= MENU Stack<" + tipo + "> (Arreglo) =================");
        System.out.println("1) Push (agregar al tope)");
        System.out.println("2) Pop (eliminar del tope)");
        System.out.println("3) Peek (ver tope)");
        System.out.println("4) Buscar valor");
        System.out.println("5) Mostrar pila");
        System.out.println("6) Mostrar tamanio [" + stack.getSize() + "/" + stack.getCapacity() + "]");
        System.out.println("7) ¿Esta vacia?");
        System.out.println("8) ¿Esta llena?");
        System.out.println("9) Limpiar pila");
        System.out.println("10) Invertir pila");
        System.out.println("0) Salir");
        System.out.println("===================================================================");
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

    private static int readInt() {
        return readInt("");
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
