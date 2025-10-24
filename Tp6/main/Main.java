package Tp6.main;

import java.util.List;
import java.util.Scanner;
import Tp6.utils.ArbolRN;
/*"Usar fix al insertar" controla si, al construir el árbol desde una lista manual, las inserciones hacen solamente la inserción de BST (insertBST) o además aplican la corrección de Red‑Black (insertarYArreglar, que hace insertBST + fixInsert).
Si respondes "s" se usa la versión con fix (árbol resultante mantiene las reglas R‑N); si respondes "n" se usa sólo BST (sin recoloraciones/rotaciones automáticas).*/

public class Main {
    // Modo manual persistente: si el usuario ingresa una lista manualmente se guarda
    // y las siguientes opciones usarán ese árbol sin preguntar demo/manual.
    private static boolean modoManualPersistente = false;
    private static ArbolRN<Integer,String> arbolPersistente = null;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArbolRN<Integer,String> arbol = new ArbolRN<>();

        while (true) {
            System.out.println("\n--- PRÁCTICO ÁRBOL ROJINEGRO — Menú de pruebas ---");
            System.out.println("1) Crear NIL/root y mostrar estado");
            System.out.println("2) Rotación izquierda (demo)");
            System.out.println("3) Rotación derecha (demo)");
            System.out.println("4) Insertar como BST (sin balance)");
            System.out.println("5) Clasificar caso de un nodo (necesita nodo existente)");
            System.out.println("6) Recolorear por tío rojo (demo mediante insertarYArreglar)");
            System.out.println("7) Rotación simple/doble (demo -- 8insert + fix)");
            System.out.println("8) Successor / Predecessor");
            System.out.println("9) Consulta por rango [a,b]");
            System.out.println("10) Verificadores de invariantes");
            System.out.println("11) Mostrar árbol");
            System.out.println("12) Desactivar modo manual persistente");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            int op = Integer.parseInt(sc.nextLine().trim());
            // Si hay un modo manual persistente activo, usamos ese árbol por defecto
            if (modoManualPersistente && arbolPersistente != null) {
                arbol = arbolPersistente;
            }
            if (op == 0) break;
            switch (op) {
                case 1:
                    if (askDemoOrManual(sc)) {
                        System.out.println("Estado inicial (árbol vacío con NIL):");
                        arbol = new ArbolRN<>();
                        System.out.println("Raíz presente? " + (arbol.getRoot() != null));
                        System.out.println("Imprimiendo árbol (vacío al inicio):");
                        arbol.imprimirArbol();
                    } else {
                        System.out.println("Inserte una lista de enteros separados por comas para construir el árbol (usar fixInsert? s/n):");
                        System.out.print("Usar fixInsert al insertar? (s/n): ");
                        boolean usarFix = sc.nextLine().trim().toLowerCase().startsWith("s");
                        System.out.println("Ingrese la lista ahora:");
                        arbol = buildTreeFromList(sc, usarFix);
                        // activar modo manual persistente
                        modoManualPersistente = true;
                        arbolPersistente = arbol;
                        System.out.println("Árbol construido:"); arbol.imprimirArbol();
                        System.out.println("Modo manual persistente activado. Para desactivarlo elija opción 12.");
                    }
                    break;
                case 2:
                    // Demo fija: rotación izquierda sólo en demo (no se permite sobre lista ingresada)
                    System.out.println("Demo rotateLeft: insertamos 10,5,15,12 y rotamos izquierda en 10");
                    arbol = new ArbolRN<>();
                    arbol.insertBST(10, "A");
                    arbol.insertBST(5, "B");
                    arbol.insertBST(15, "C");
                    arbol.insertBST(12, "D");
                    System.out.println("Antes:"); arbol.imprimirArbol();
                    ArbolRN<Integer,String>.RBNode n10 = arbol.buscarNodo(10);
                    arbol.rotateLeft(n10);
                    System.out.println("Después de rotateLeft(10):"); arbol.imprimirArbol();
                    break;
                case 3:
                    // Demo fija: rotación derecha sólo en demo (no se permite sobre lista ingresada)
                    System.out.println("Demo rotateRight: insertamos 10,5,15,3 y rotamos derecha en 10");
                    arbol = new ArbolRN<>();
                    arbol.insertBST(10, "A");
                    arbol.insertBST(5, "B");
                    arbol.insertBST(15, "C");
                    arbol.insertBST(3, "D");
                    System.out.println("Antes:"); arbol.imprimirArbol();
                    ArbolRN<Integer,String>.RBNode n10r = arbol.buscarNodo(10);
                    arbol.rotateRight(n10r);
                    System.out.println("Después de rotateRight(10):"); arbol.imprimirArbol();
                    break;
                case 4:
                    System.out.print("Clave entera a insertar (BST sin balance): ");
                    int k = Integer.parseInt(sc.nextLine().trim());
                    arbol.insertBST(k, "v"+k);
                    System.out.println("Insertado como BST (sin fix). Árbol resultante:");
                    arbol.imprimirArbol();
                    break;
                case 5:
                    System.out.print("Clave existente para clasificar su caso (p/ej 10): ");
                    int kc = Integer.parseInt(sc.nextLine().trim());
                    ArbolRN<Integer,String>.RBNode nodoC = arbol.buscarNodo(kc);
                    if (nodoC == null) { System.out.println("Nodo no encontrado."); break; }
                    try {
                        System.out.println("Caso: " + arbol.clasificar(nodoC));
                    } catch (Exception e) { System.out.println("No se pudo clasificar (posible nodo sin abuelo)." ); }
                    break;
                case 6:
                    System.out.println("Demo insertarYArreglar: insertamos 10,5,15,1,6 y arreglamos cada inserción");
                    arbol = new ArbolRN<>();
                    int[] demo = {10,5,15,1,6};
                    for (int v: demo) {
                        arbol.insertarYArreglar(v, "v"+v);
                    }
                    arbol.imprimirArbol();
                    break;
                case 7:
                    System.out.println("Rotación simple vs doble: insertamos 30,20,10 (cadena LL) y arreglamos");
                    arbol = new ArbolRN<>();
                    arbol.insertarYArreglar(30, "v30");
                    arbol.insertarYArreglar(20, "v20");
                    arbol.insertarYArreglar(10, "v10");
                    arbol.imprimirArbol();
                    System.out.println("Ahora insertamos 25 para forzar LR en otra rama:");
                    arbol.insertarYArreglar(25, "v25");
                    arbol.imprimirArbol();
                    break;
                case 8:
                    // Successor / Predecessor — preferir árbol persistente o lista ingresada
                    if (modoManualPersistente && arbolPersistente != null) {
                        arbol = arbolPersistente;
                        System.out.println("Usando árbol persistente. Árbol actual:"); arbol.imprimirArbol();
                        System.out.print("Ingrese la clave para calcular successor/predecessor: ");
                        try {
                            int clave = Integer.parseInt(sc.nextLine().trim());
                            ArbolRN<Integer,String>.RBNode nodo = arbol.buscarNodo(clave);
                            if (nodo == null) System.out.println("Nodo no encontrado.");
                            else {
                                ArbolRN<Integer,String>.RBNode s = arbol.successor(nodo);
                                ArbolRN<Integer,String>.RBNode p = arbol.predecessor(nodo);
                                System.out.println("Successor de " + clave + ": " + (s!=null? s.getKey() : "null"));
                                System.out.println("Predecessor de " + clave + ": " + (p!=null? p.getKey() : "null"));
                            }
                        } catch (Exception e) { System.out.println("Entrada inválida."); }
                    } else if (askDemoOrManual(sc)) {
                        System.out.println("Demo Successor/Predecessor con claves 5,10,15");
                        arbol = new ArbolRN<>(); arbol.insertarYArreglar(10,"v10"); arbol.insertarYArreglar(5,"v5"); arbol.insertarYArreglar(15,"v15");
                        ArbolRN<Integer,String>.RBNode n5 = arbol.buscarNodo(5);
                        ArbolRN<Integer,String>.RBNode n10s = arbol.successor(n5);
                        System.out.println("Successor de 5: " + (n10s!=null? n10s.getKey() : "null"));
                        ArbolRN<Integer,String>.RBNode n15 = arbol.buscarNodo(15);
                        ArbolRN<Integer,String>.RBNode p15 = arbol.predecessor(n15);
                        System.out.println("Predecessor de 15: " + (p15!=null? p15.getKey() : "null"));
                    } else {
                        System.out.println("Inserte una lista de enteros separados por comas para construir el árbol:");
                        arbol = buildTreeFromList(sc, true); // por defecto usar fix al construir lista manual para operaciones
                        modoManualPersistente = true; arbolPersistente = arbol;
                        System.out.println("Árbol construido:"); arbol.imprimirArbol();
                        System.out.print("Ingrese la clave para calcular successor/predecessor: ");
                        try {
                            int clave = Integer.parseInt(sc.nextLine().trim());
                            ArbolRN<Integer,String>.RBNode nodo = arbol.buscarNodo(clave);
                            if (nodo == null) System.out.println("Nodo no encontrado.");
                            else {
                                ArbolRN<Integer,String>.RBNode s = arbol.successor(nodo);
                                ArbolRN<Integer,String>.RBNode p = arbol.predecessor(nodo);
                                System.out.println("Successor de " + clave + ": " + (s!=null? s.getKey() : "null"));
                                System.out.println("Predecessor de " + clave + ": " + (p!=null? p.getKey() : "null"));
                            }
                        } catch (Exception e) { System.out.println("Entrada inválida."); }
                    }
                    break;
                case 9:
                    // Consulta por rango — permitir usar árbol persistente o construir uno manual
                    if (modoManualPersistente && arbolPersistente != null) {
                        arbol = arbolPersistente;
                        System.out.println("Usando árbol persistente. Árbol actual:"); arbol.imprimirArbol();
                        try {
                            System.out.print("Ingrese a (límite inferior): "); int a = Integer.parseInt(sc.nextLine().trim());
                            System.out.print("Ingrese b (límite superior): "); int b = Integer.parseInt(sc.nextLine().trim());
                            List<Integer> rlist = arbol.rango(a,b);
                            System.out.println("Claves en ["+a+","+b+"]: " + rlist);
                        } catch (Exception e) { System.out.println("Entrada inválida."); }
                    } else if (askDemoOrManual(sc)) {
                        System.out.println("Demo rango: insertamos 1..10 y consultamos rango [3,7]");
                        arbol = new ArbolRN<>();
                        for (int i=1;i<=10;i++) arbol.insertarYArreglar(i, "v"+i);
                        List<Integer> r = arbol.rango(3,7);
                        System.out.println("Claves en [3,7]: " + r);
                    } else {
                        System.out.println("Inserte una lista de enteros separados por comas para construir el árbol (usar fix? s/n):");
                        System.out.print("Usar fixInsert al insertar? (s/n): ");
                        boolean usarFix = sc.nextLine().trim().toLowerCase().startsWith("s");
                        arbol = buildTreeFromList(sc, usarFix);
                        modoManualPersistente = true; arbolPersistente = arbol;
                        arbol.imprimirArbol();
                        try {
                            System.out.print("Ingrese a (límite inferior): "); int a = Integer.parseInt(sc.nextLine().trim());
                            System.out.print("Ingrese b (límite superior): "); int b = Integer.parseInt(sc.nextLine().trim());
                            List<Integer> rlist = arbol.rango(a,b);
                            System.out.println("Claves en ["+a+","+b+"]: " + rlist);
                        } catch (Exception e) { System.out.println("Entrada inválida."); }
                    }
                    break;
                case 10:
                    // Verificadores de invariantes: preferir árbol persistente o lista ingresada
                    if (modoManualPersistente && arbolPersistente != null) {
                        arbol = arbolPersistente;
                        System.out.println("Usando árbol persistente. Árbol actual:"); arbol.imprimirArbol();
                        System.out.println("raizNegra: " + arbol.raizNegra());
                        System.out.println("sinRojoRojo: " + arbol.sinRojoRojo());
                        System.out.println("alturaNegra (o -1 si falla): " + arbol.alturaNegra());
                    } else if (askDemoOrManual(sc)) {
                        System.out.println("Demo invariantes: insertamos 10,5,15,1,6,12,17 y comprobamos invariantes");
                        arbol = new ArbolRN<>(); int[] ins = {10,5,15,1,6,12,17};
                        for (int v: ins) arbol.insertarYArreglar(v, "v"+v);
                        arbol.imprimirArbol();
                        System.out.println("raizNegra: " + arbol.raizNegra());
                        System.out.println("sinRojoRojo: " + arbol.sinRojoRojo());
                        System.out.println("alturaNegra (o -1 si falla): " + arbol.alturaNegra());
                    } else {
                        System.out.println("Inserte una lista de enteros separados por comas para construir el árbol (usar fix? s/n):");
                        System.out.print("Usar fixInsert al insertar? (s/n): ");
                        boolean usarFix = sc.nextLine().trim().toLowerCase().startsWith("s");
                        arbol = buildTreeFromList(sc, usarFix);
                        modoManualPersistente = true; arbolPersistente = arbol;
                        arbol.imprimirArbol();
                        System.out.println("raizNegra: " + arbol.raizNegra());
                        System.out.println("sinRojoRojo: " + arbol.sinRojoRojo());
                        System.out.println("alturaNegra (o -1 si falla): " + arbol.alturaNegra());
                    }
                    break;
                case 11:
                    System.out.println("Árbol actual:"); arbol.imprimirArbol();
                    break;
                case 12:
                    modoManualPersistente = false;
                    arbolPersistente = null;
                    System.out.println("Modo manual persistente desactivado.");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
        sc.close();
    }

    private static boolean askDemoOrManual(Scanner sc) {
        System.out.print("Usar demo preset? (s/n): ");
        String r = sc.nextLine().trim().toLowerCase();
        return r.startsWith("s");
    }

    private static ArbolRN<Integer,String> buildTreeFromList(Scanner sc, boolean usarFix) {
        ArbolRN<Integer,String> arbol = new ArbolRN<>();
        String line = sc.nextLine().trim();
        if (line.isEmpty()) return arbol;
        String[] parts = line.split(",");
        for (String p: parts) {
            try {
                int v = Integer.parseInt(p.trim());
                if (usarFix) arbol.insertarYArreglar(v, "v"+v);
                else arbol.insertBST(v, "v"+v);
            } catch (NumberFormatException e) {
                // saltar tokens inválidos
            }
        }
        return arbol;
    }
}
