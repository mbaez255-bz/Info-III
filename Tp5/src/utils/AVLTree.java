package Tp5.src.utils;

import java.util.ArrayList;
import java.util.List;

public class AVLTree<T extends Comparable<T>> {
    // Raíz del árbol
    private Node<T> root;

    // --- MÉTODOS PÚBLICOS ---
    // 1) Construcción / preparación del árbol de demo
    @SuppressWarnings("unchecked")
    public void createDemoTree() {
    // Construcción manual para el demo:
        //        30
        //       /  \
        //     10    40
        //    /  \     
        //   5   20    50
        //        \
        //        25
        root = new Node<>((T) (Integer) 30);
        root.left = new Node<>((T) (Integer) 10);
        root.right = new Node<>((T) (Integer) 40);
        root.left.left = new Node<>((T) (Integer) 5);
        root.left.right = new Node<>((T) (Integer) 20);
        root.left.right.right = new Node<>((T) (Integer) 25);
        root.right.right = new Node<>((T) (Integer) 50);

    
        // Actualizar alturas desde abajo (desde las hojas hacia la raíz)
        updateHeight(root.left.left);
        updateHeight(root.left.right.right);
        updateHeight(root.left.right);
        updateHeight(root.left);

        updateHeight(root.right.right);
        updateHeight(root.right);
        updateHeight(root);
    }

    /** 2) Imprime el árbol en vista lateral (texto). */
    public void printTree() { printTreeRec(root, "", true); }

    /* Recorrido in-order: devuelve valores en una lista. */
    public List<T> inOrderTraversal() {
        List<T> result = new ArrayList<>();
        inOrderRec(root, result);
        return result;
    }

    /** Devuelve si el árbol cumple las condiciones AVL. */
    public boolean isAVL() { return isAVLRec(root, null, null).isAVL; }

    /** 3) Verifica si un valor existe en el árbol. */
    public boolean contains(T value) { return searchRec(root, value) != null; }

    /** 4) Elimina un valor del árbol (si existe). Devuelve true si se eliminó. */
    public boolean remove(T value) {
        if (root != null && root.value.equals(value)) {
            System.out.println("Opción inválida: no se puede eliminar la raíz");
            return false;
        }
        List<T> before = inOrderTraversal();
        root = removeRec(root, value);
        List<T> after = inOrderTraversal();
        if (before.equals(after)) {
            System.out.println("No se encontró: " + value);
            return false;
        } else {
            // show tree after removal
            System.out.println("\nÁrbol después de eliminar " + value + ":");
            if (root == null) System.out.println("(vacío)"); else printTree();
            return true;
        }
        /*Resumen general
        public boolean remove(T value) es el método que el usuario llama. En tu código hace:
        Rechaza eliminar la raíz explícitamente (si el valor a eliminar es exactamente root.value imprime un mensaje y devuelve false).
        Calcula before = inOrderTraversal() (lista de valores antes).
        Llama root = removeRec(root, value) para intentar borrar en el árbol (re-asigna root porque la raíz del árbol puede cambiar).
        Calcula after = inOrderTraversal() y compara before.equals(after) para decidir si se eliminó algo (devuelve false si no cambió).
        Si se eliminó, imprime el árbol resultante y retorna true. */
    }

    /** Pruebas mínimas para isAVL() */
    public static void testIsAVL() {
        System.out.println("\n--- Pruebas isAVL() ---");
        AVLTree<Integer> v = new AVLTree<>(); v.root = new Node<>(2); v.root.left = new Node<>(1); v.root.right = new Node<>(4);
        v.updateHeight(v.root.left); v.updateHeight(v.root.right); v.updateHeight(v.root);
        System.out.println(v.isAVL() ? "true es avl" : "false no es avl");
        AVLTree<Integer> nb = new AVLTree<>(); nb.root = new Node<>(1); nb.root.right = new Node<>(2); nb.root.right.right = new Node<>(3);
        nb.updateHeight(nb.root.right.right); nb.updateHeight(nb.root.right); nb.updateHeight(nb.root);
        System.out.println(nb.isAVL() ? "true es avl" : "false no es avl");
        System.out.println("--- Fin ---\n");
    }

    /** 6) Devuelve la altura del árbol. */
    public int getHeight() { return nodeHeight(root); }


    // --- MÉTODOS PRIVADOS ---

    // Búsqueda recursiva(paso 3)
    private Node<T> searchRec(Node<T> node, T value) {
        if (node == null) return null;
        int cmp = value.compareTo(node.value);
        if (cmp == 0) return node;
        if (cmp < 0) return searchRec(node.left, value);
        return searchRec(node.right, value);
    }

    // Auxiliar para recorrido in-order
    private void inOrderRec(Node<T> node, List<T> result) {
        if (node != null) {
            inOrderRec(node.left, result);
            result.add(node.value);
            inOrderRec(node.right, result);
        }
    }

    // Altura de un nodo (0 si es nulo)
    private int nodeHeight(Node<T> node) { return node == null ? 0 : node.height; }

    // Actualiza node.height según sus hijos
    private void updateHeight(Node<T> node) {
        node.height = 1 + Math.max(nodeHeight(node.left), nodeHeight(node.right));
    }

    // Factor de balance: altura(izq) - altura(dcha)
    private int getBalance(Node<T> node) {
        return node == null ? 0 : nodeHeight(node.left) - nodeHeight(node.right);
    }

    // Rebalancea el nodo si es necesario aplicando rotaciones
    private Node<T> rebalance(Node<T> node) {
        int balance = getBalance(node);
        int balLeft = getBalance(node.left);
        int balRight = getBalance(node.right);

    // Detecta el caso y aplica la rotación
        if (balance < -1 && balRight <= 0) {
            // Caso RR -> rotación izquierda
            System.out.println("Rebalanceo: Rotación izquierda (RR) en nodo " + node.value);
            return rotateLeft(node);
        }
        if (balance > 1 && balLeft >= 0) {
            // Caso LL -> rotación derecha
            System.out.println("Rebalanceo: Rotación derecha (LL) en nodo " + node.value);
            return rotateRight(node);
        }
        if (balance > 1 && balLeft < 0) {
            // Caso LR -> rotación izquierda sobre hijo izquierdo y luego derecha
            System.out.println("Rebalanceo: Rotación izquierda-derecha (LR) en nodo " + node.value);
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && balRight > 0) {
            // Caso RL -> rotación derecha sobre hijo derecho y luego izquierda
            System.out.println("Rebalanceo: Rotación derecha-izquierda (RL) en nodo " + node.value);
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Rotación izquierda: devuelve la nueva raíz del subárbol
    private Node<T> rotateLeft(Node<T> y) {
        Node<T> x = y.right;
        Node<T> tempLeft = x.left;
        x.left = y;
        y.right = tempLeft;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    // Rotación derecha: devuelve la nueva raíz del subárbol
    private Node<T> rotateRight(Node<T> y) {
        Node<T> x = y.left;
        Node<T> tempRight = x.right;
        x.right = y;
        y.left = tempRight;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    // Eliminación recursiva
    private Node<T> removeRec(Node<T> node, T value) {
        if (node == null) return null;
        //busca el nodo a borrar
        int cmp = value.compareTo(node.value);
        if (cmp < 0) node.left = removeRec(node.left, value);//se vuelve a buscar por izq
        else if (cmp > 0) node.right = removeRec(node.right, value);//se vuelve a buscar por dcha
        else {
            // Nodo encontrado
                if (node.left == null || node.right == null) {
                Node<T> temp = (node.left != null) ? node.left : node.right;
                if (temp == null) return null; // ambos hijos eran null, se borra el nodo
                    else node = temp; // un hijo lo pas a a temp y se borra el nodo
            } else {
                // dos hijos: buscar sucesor in-order, lo copia y borra el sucesor
                Node<T> successor = minValueNode(node.right);
                node.value = successor.value;
                node.right = removeRec(node.right, successor.value);
            }
        }
        updateHeight(node);
        return rebalance(node);
        /*Propósito: borra value del subárbol con raíz node y devuelve la nueva raíz
        del subárbol (puede ser null si queda vacío). */
    }

    // Devuelve el nodo con el valor mínimo en el subárbol
    private Node<T> minValueNode(Node<T> node) {
        Node<T> current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    // Impresión recursiva con prefijos para representar la jerarquía
    private void printTreeRec(Node<T> node, String prefix, boolean isTail) {
        if (node != null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + node.value + " (bf=" + getBalance(node) + ")");
            printTreeRec(node.left, prefix + (isTail ? "    " : "│   "), false);
            printTreeRec(node.right, prefix + (isTail ? "    " : "│   "), true);
        }
        //Es decir: concatena el prefix(""), después └──  si isTail==true o ├──  si no, luego el valor del nodo y entre paréntesis el factor de balance (bf).
    }

        // Clase interna para nodos del árbol
        private static class Node<T> {
        T value;
        Node<T> left, right;
        int height;

        Node(T value) { this.value = value; this.height = 1; }
    }

    // Método recursivo que devuelve si el subárbol es AVL y su altura
    private Result isAVLRec(Node<T> node, T min, T max) {
        if (node == null) return new Result(true, 0);

        // BST property: check ranges
        if ((min != null && node.value.compareTo(min) <= 0) ||
            (max != null && node.value.compareTo(max) >= 0)) {
            return new Result(false, 0);
            //Si el valor actual no está estrictamente entre (min, max) → falla la propiedad BST → devolvemos isAVL=false.
        }

        // check children
        Result leftRes = isAVLRec(node.left, min, node.value);
        Result rightRes = isAVLRec(node.right, node.value, max);

        boolean balanced = leftRes.isAVL && rightRes.isAVL &&
            Math.abs(leftRes.height - rightRes.height) <= 1;

        int height = 1 + Math.max(leftRes.height, rightRes.height);

        return new Result(balanced, height);
        //Nodo 5: left/right nulos → leftRes=(true,0), rightRes=(true,0) → balanced true, height = 1.
        //Nodo 10: left 5 (1), right 20 (2) → height = 3, balanced true.
    }

    // Clase auxiliar Resultado
    private static class Result {
        boolean isAVL;
        int height;
        Result(boolean e, int h) { isAVL = e; height = h; }
    }

}
