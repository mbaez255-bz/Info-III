package Tp5.src.main;

import Tp5.src.utils.AVLTree;

/**
 * Ejecuta una secuencia hardcodeada del demo y muestra la salida por consola.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== DEMO Tp5: Árbol AVL ===");

        AVLTree<Integer> tree = new AVLTree<>();

        // 1) Preparar un árbol controlado que provocará rebalanceo tras una eliminación
        System.out.println("\n1) Construyendo árbol de demo (preparado para eliminación->rebalanceo):");
        System.out.println("Valores a ingresar: 30, 10, 40, 5, 20, 25, 50");
        tree.createDemoTree();
        Integer candidateToRemove = 50; // valor fijo para la demo (createDemoTree no devuelve candidato)

        // 2) Mostrar estado actual
        System.out.println("\n2) Árbol actual:");
        tree.printTree();
        System.out.println("Recorrido in-order: " + tree.inOrderTraversal());
        System.out.println("¿es AVL (actual)? " + tree.isAVL());

        // 3) Búsquedas (demo)
        System.out.println("\n3) Búsquedas: contiene 25 (debe existir) y 99 (no debe existir):");
        System.out.println("buscar(25): " + tree.contains(25));
        System.out.println("buscar(99): " + tree.contains(99));

        // 4) Eliminación -> eliminar el candidato preparado
        System.out.println("\n4) Eliminación que provocará rebalanceo: eliminar " + candidateToRemove);
        boolean removed1 = tree.remove(candidateToRemove);
        System.out.println("eliminar(" + candidateToRemove + ") -> " + removed1);
        System.out.println("Recorrido in-order: " + tree.inOrderTraversal());
        System.out.println("¿es AVL (después de eliminación)? " + tree.isAVL());

        // Eliminar un segundo valor para mostrar otra operación
        int otherToRemove = 25;
        System.out.println("\nTambién eliminamos el valor: " + otherToRemove);
        boolean removed2 = tree.remove(otherToRemove);
        System.out.println("eliminar(" + otherToRemove + ") -> " + removed2);
        System.out.println("Recorrido in-order: " + tree.inOrderTraversal());
        System.out.println("¿es AVL (después de segunda eliminación)? " + tree.isAVL());

        // 5) Resumen final y altura
        System.out.println("\n5) Resumen final del árbol:");
        tree.printTree();
        System.out.println("Altura del árbol: " + tree.getHeight());
        System.out.println("¿es AVL? " + tree.isAVL());
        System.out.println("\n=== FIN DEMO Tp5 ===");
    }
}
