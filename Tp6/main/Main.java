package Tp6.main;

import java.util.List;
import Tp6.utils.ArbolRN;

/**
 * Demo non-interactive: ejecuta en secuencia ejemplos hardcodeados que
 * demuestran cada una de las funcionalidades del menú original.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== DEMO Tp6: Árbol Rojo-Negro (secuencia hardcodeada) ===\n");

    // 1) Crear NIL/root y mostrar estado
    System.out.println("Demo a usar: (10,5,15,12)");
    System.out.println("1) Crear arbol:");
    ArbolRN<Integer,String> arbol1 = new ArbolRN<>();
    System.out.println("Raíz presente? " + (arbol1.getRoot() != null));
    ArbolRN<Integer,String> demoTreeFor2 = new ArbolRN<>();
    demoTreeFor2.insertBST(10, "A");
    demoTreeFor2.insertBST(5, "B");
    demoTreeFor2.insertBST(15, "C");
    demoTreeFor2.insertBST(12, "D");
    demoTreeFor2.imprimirArbol();
    System.out.println();

    // 2) Rotación izquierda (demo)
    System.out.println("Demo a usar: (10,5,15,12)");
    System.out.println("2) Demo rotateLeft:");
        ArbolRN<Integer,String> arbol2 = new ArbolRN<>();
        arbol2.insertBST(10, "A");
        arbol2.insertBST(5, "B");
        arbol2.insertBST(15, "C");
        arbol2.insertBST(12, "D");
        System.out.println("Antes:"); arbol2.imprimirArbol();
        ArbolRN<Integer,String>.RBNode n10 = arbol2.buscarNodo(10);
        arbol2.rotateLeft(n10);
        System.out.println("Después de rotateLeft(10):"); arbol2.imprimirArbol();
        System.out.println();

    // 3) Rotación derecha (demo)
    System.out.println("Demo a usar: (10,5,15,3)");
    System.out.println("3) Demo rotateRight:");
        ArbolRN<Integer,String> arbol3 = new ArbolRN<>();
        arbol3.insertBST(10, "A");
        arbol3.insertBST(5, "B");
        arbol3.insertBST(15, "C");
        arbol3.insertBST(3, "D");
        System.out.println("Antes:"); arbol3.imprimirArbol();
        ArbolRN<Integer,String>.RBNode n10r = arbol3.buscarNodo(10);
        arbol3.rotateRight(n10r);
        System.out.println("Después de rotateRight(10):"); arbol3.imprimirArbol();
        System.out.println();

    // 4) Insertar como BST (sin balance)
    System.out.println("Demo a usar: (42,21,84)");
    System.out.println("4) Insertar como BST (sin fix):");
        ArbolRN<Integer,String> arbol4 = new ArbolRN<>();
        arbol4.insertBST(42, "v42");
        arbol4.insertBST(21, "v21");
        arbol4.insertBST(84, "v84");
        arbol4.imprimirArbol();
        System.out.println();

    // 5) Clasificar caso de un nodo (dos ejemplos)
    System.out.println("Demo a usar:  (10,20,30 -> RR) y (30,20,10 -> LL)");
    System.out.println("5) Clasificar caso:");

    // Ejemplo A: 10,20,30 (debería clasificarse como RR)
    System.out.println("Ejemplo A: insertar 10,20,30 (esperado: RR)");
    ArbolRN<Integer,String> arbol5a = new ArbolRN<>();
    arbol5a.insertBST(10, "v10");
    arbol5a.insertBST(20, "v20");
    ArbolRN<Integer,String>.RBNode zA = arbol5a.insertBST(30, "v30");
    System.out.println("Antes del balanceo (insertBST):");
    arbol5a.imprimirArbol();
    arbol5a.fixInsert(zA);// aplicar el algoritmo de fix para mostrar el después
    System.out.println("Después del balanceo (fixInsert):");
    arbol5a.imprimirArbol();
    System.out.println();

    // Ejemplo B: 30,20,10 (debería clasificarse como LL)
    System.out.println("Ejemplo B: insertar 30,20,10 (esperado: LL)");
    ArbolRN<Integer,String> arbol5b = new ArbolRN<>();
    arbol5b.insertBST(30, "v30");
    arbol5b.insertBST(20, "v20");
    ArbolRN<Integer,String>.RBNode zB = arbol5b.insertBST(10, "v10");
    System.out.println("Antes del balanceo (insertBST):");
    arbol5b.imprimirArbol();
    arbol5b.fixInsert(zB);// aplicar el algoritmo de fix para mostrar el después    // aplicar el algoritmo de fix para mostrar el después
    System.out.println("Después del balanceo (fixInsert):");
    arbol5b.imprimirArbol();
    System.out.println();

    // 6) Recolorear por tío rojo (mostrar antes y después)
    System.out.println("Demo a usar: (10,5,15,1,6)");
    System.out.println("6) Demo insertarYArreglar (recoloreos)");
        ArbolRN<Integer,String> arbol6 = new ArbolRN<>();
        // insertamos todos excepto el último con insertBST para ver el estado "antes"
        arbol6.insertBST(10, "v10");
        arbol6.insertBST(5, "v5");
        arbol6.insertBST(15, "v15");
        arbol6.insertBST(1, "v1");
        // insertar el último y capturarlo
        ArbolRN<Integer,String>.RBNode z6 = arbol6.insertBST(6, "v6");
        System.out.println("Antes del balanceo (insertBST, sin fix):");
        arbol6.imprimirArbol();
        // aplicar fixInsert para mostrar el después
        arbol6.fixInsert(z6);
        System.out.println("Después del balanceo (fixInsert):");
        arbol6.imprimirArbol();
        System.out.println();

    // 7) Rotación simple/doble (mostrar antes y después: LL -> luego LR)
    System.out.println("Demo a usar: (30,20,10,25)");
    System.out.println("7) Rotación simple/doble (LL -> luego LR) :");
        ArbolRN<Integer,String> arbol7 = new ArbolRN<>();
        // forzar la inserción que provoca LL: insertar 30 y 20 primero
        arbol7.insertBST(30, "v30");
        arbol7.insertBST(20, "v20");
        // insertar 10 y capturar para mostrar before/after
        ArbolRN<Integer,String>.RBNode z7a = arbol7.insertBST(10, "v10");
        System.out.println("Antes del balanceo (insertBST 30,20,10):");
        arbol7.imprimirArbol();
        arbol7.fixInsert(z7a);
        System.out.println("Después del balanceo (fixInsert - debería ser LL):");
        arbol7.imprimirArbol();
        System.out.println();
        // ahora insertar 25 para forzar un caso LR en otra rama y mostrar before/after
        System.out.println("Insertar 25 para forzar LR en otra rama:");
        ArbolRN<Integer,String>.RBNode z7b = arbol7.insertBST(25, "v25");
        System.out.println("Antes del balanceo (insertBST 25):");
        arbol7.imprimirArbol();
        arbol7.fixInsert(z7b);
        System.out.println("Después del balanceo (fixInsert)");
        arbol7.imprimirArbol();
        System.out.println();

    // 8) Successor / Predecessor
    System.out.println("Demo a usar: (5,10,15)");
    System.out.println("8) Successor / Predecessor (ejemplo con 5,10,15):");
        ArbolRN<Integer,String> arbol8 = new ArbolRN<>();
        arbol8.insertarYArreglar(10, "v10"); arbol8.insertarYArreglar(5, "v5"); arbol8.insertarYArreglar(15, "v15");
        ArbolRN<Integer,String>.RBNode n5 = arbol8.buscarNodo(5);
        ArbolRN<Integer,String>.RBNode s = arbol8.successor(n5);
        System.out.println("Successor de 5: " + (s!=null? s.getKey() : "null"));
        ArbolRN<Integer,String>.RBNode n15 = arbol8.buscarNodo(15);
        ArbolRN<Integer,String>.RBNode p = arbol8.predecessor(n15);
        System.out.println("Predecessor de 15: " + (p!=null? p.getKey() : "null"));
        System.out.println();

    // 9) Consulta por rango [a,b]
    System.out.println("Demo a usar: árbol 1..10 ");
    System.out.println("9) Consulta por rango [3,7]");
        ArbolRN<Integer,String> arbol9 = new ArbolRN<>();
        for (int i=1;i<=10;i++) arbol9.insertarYArreglar(i, "v"+i);
        List<Integer> r = arbol9.rango(3,7);
        System.out.println("Claves en [3,7]: " + r);
        System.out.println();

    // 10) Verificadores de invariantes
    System.out.println("Demo a usar: (10,5,15,1,6,12,17)");
    System.out.println("10) Verificadores de invariantes en árbol de ejemplo:");
        ArbolRN<Integer,String> arbol10 = new ArbolRN<>();
        int[] ins = {10,5,15,1,6,12,17};
        for (int v: ins) arbol10.insertarYArreglar(v, "v"+v);
        arbol10.imprimirArbol();
        System.out.println("raizNegra: " + arbol10.raizNegra());
        System.out.println("sinRojoRojo: " + arbol10.sinRojoRojo());
        System.out.println("alturaNegra (o -1 si falla): " + arbol10.alturaNegra());
        System.out.println();

    // 11) Mostrar árbol (ya mostrado en varios pasos), repetir final
    System.out.println("Demo a usar: repetir la impresión del árbol de verificadores");
    System.out.println("11) Mostrar árbol final de ejemplo:");
        arbol10.imprimirArbol();
        System.out.println();

        System.out.println("\n=== FIN DEMO Tp6 ===");
    }
}
