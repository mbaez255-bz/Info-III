package Tp3.src.main;

import java.util.Scanner;

import Tp3.src.utils.Queue;

public class QueueMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Ingrese la capacidad de la cola: ");
        int capacity = readInt();
        
        Queue<Integer> queue = new Queue<>(capacity);
        boolean goOut = false;

        while (!goOut) {
            printMenu();
            int option = readInt("Seleccione una opcion: ");
            System.out.println();

            switch (option) {
                case 1: // Enqueue
                    if(queue.isFull()) {
                        System.out.println("Error: La cola esta llena.");
                        break;
                    }else{
                        int value = readInt("Valor a agregar: ");
                        queue.enqueue(value);
                        System.out.println("OK: elemento agregado a la cola.");
                    }
                    break;
                case 2: // Dequeue
                    Integer removed = queue.dequeue();
                    if (removed != null) {
                        System.out.println("OK: elemento " + removed + " eliminado de la cola.");
                    } else {
                        System.out.println("Error: La cola esta vacia.");
                    }
                    break;
                case 3: // Peek
                    Integer front = queue.peek();
                    if (front != null) {
                        System.out.println("Elemento al frente: " + front);
                    } else {
                        System.out.println("La cola esta vacia.");
                    }
                    break;
                case 4: // Buscar
                    if(queue.isEmpty()) {
                        System.out.println("La cola esta vacia.");
                        break;
                    }
                    int searchVal = readInt("Valor a buscar: ");
                    System.out.println(queue.search(searchVal) ? "Encontrado." : "No encontrado.");
                    break;
                case 5: // Mostrar cola
                    if (!queue.isEmpty()) {
                        System.out.println("Cola: " + queue);
                    } else {
                        System.out.println("La cola esta vacia.");
                    }
                    break;
                case 6: // Tamaño
                    System.out.println("Tamanio: " + queue.getSize() + "/" + queue.getCapacity());
                    break;
                case 7: // ¿Vacía?
                    System.out.println(queue.isEmpty() ? "Si, esta vacia." : "No, tiene elementos.");
                    break;
                case 8: // ¿Llena?
                    System.out.println(queue.isFull() ? "Si, esta llena." : "No, tiene espacio.");
                    break;
                case 9: // Limpiar
                    queue.clear();
                    System.out.println("OK: cola limpiada.");
                    break;
                case 10: // Invertir
                    if(queue.isEmpty()) {
                        System.out.println("La cola esta vacia.");
                    } else {
                        queue.reverse();
                        System.out.println("OK: cola invertida.");
                        System.out.println("Cola actual: " + queue);
                    }
                    break;
                case 0:
                    goOut = true;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }

            if (!goOut) {
                System.out.println();
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("================= MENU Queue (Arreglo) =================");
        System.out.println("1) Enqueue (agregar)");
        System.out.println("2) Dequeue (eliminar del frente)");
        System.out.println("3) Peek (ver frente)");
        System.out.println("4) Buscar valor");
        System.out.println("5) Mostrar cola");
        System.out.println("6) Mostrar tamanio");
        System.out.println("7) ¿Esta vacia?");
        System.out.println("8) ¿Esta llena?");
        System.out.println("9) Limpiar cola");
        System.out.println("10) Invertir cola");
        System.out.println("0) Salir");
        System.out.println("========================================================");
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
}
