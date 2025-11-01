package Tp5.utils;

import java.util.ArrayList;
import java.util.List;

public class ArbolAVL<T extends Comparable<T>> {
    // Raíz del árbol
    private Node<T> raiz;

    @SuppressWarnings("unchecked")
    //1) (construcción / preparación del árbol de demo)
    public void crearArbolAVL() {
        // Construcción manual para el demo:
        //        30
        //       /  \
        //     10    40
        //    /  \     
        //   5   20    50
        //        \
        //        25
        raiz = new Node<>((T) (Integer) 30);
        raiz.izquierdo = new Node<>((T) (Integer) 10);
        raiz.derecho = new Node<>((T) (Integer) 40);
        raiz.izquierdo.izquierdo = new Node<>((T) (Integer) 5);
        raiz.izquierdo.derecho = new Node<>((T) (Integer) 20);
        raiz.izquierdo.derecho.derecho = new Node<>((T) (Integer) 25);
        raiz.derecho.derecho = new Node<>((T) (Integer) 50);

        // actualizar alturas desde abajo
        actualizarAltura(raiz.izquierdo.izquierdo);
        actualizarAltura(raiz.izquierdo.derecho.derecho);
        actualizarAltura(raiz.izquierdo.derecho);
        actualizarAltura(raiz.izquierdo);
        actualizarAltura(raiz.derecho.derecho);
        actualizarAltura(raiz.derecho);
        actualizarAltura(raiz);
    }

    /** 2) Imprime el árbol de forma textual para inspección (vista lateral). */
    public void imprimirArbol() { imprimirArbolRec(raiz, "", true); }

    /*Recorre el árbol en orden (in-order) y devuelve los valores en una lista.*/
    public List<T> recorridoEnOrden() {
        List<T> resultado = new ArrayList<>();
        recorridoEnOrdenRec(raiz, resultado);
        return resultado;
    }

    /** Devuelve si el árbol cumple las condiciones AVL. */
    public boolean esAVL() { return esAVLRec(raiz, null, null).esAVL; }

    /** 3) Busca si un elemento existe en el árbol. */
    public boolean buscar(T dato) { return buscarRec(raiz, dato) != null; }

    /** 4) Elimina un elemento del árbol (si existe). Devuelve true si se eliminó. */
    public boolean eliminar(T dato) {
        if (raiz != null && raiz.valor.equals(dato)) {
            System.out.println("Opción no válida: no se puede eliminar la raíz");
            return false;
        }
        List<T> antes = recorridoEnOrden();
        raiz = eliminarRec(raiz, dato);
        List<T> despues = recorridoEnOrden();
        if (antes.equals(despues)) {
            System.out.println("No se encontró: " + dato);
            return false;
        } else {
            // Mostrar árbol después de la eliminación
            System.out.println("\nÁrbol después de eliminar " + dato + ":");
            if (raiz == null) System.out.println("(vacío)"); else imprimirArbol();
            return true;
        }
    }

    /** Pruebas mínimas de esAVL() */
    public static void pruebasEsAVL() {
        System.out.println("\n--- Pruebas esAVL() ---");
        ArbolAVL<Integer> v = new ArbolAVL<>(); v.raiz = new Node<>(2); v.raiz.izquierdo = new Node<>(1); v.raiz.derecho = new Node<>(4);
        v.actualizarAltura(v.raiz.izquierdo); v.actualizarAltura(v.raiz.derecho); v.actualizarAltura(v.raiz);
        System.out.println(v.esAVL() ? "true es avl" : "false no es avl");
        ArbolAVL<Integer> nb = new ArbolAVL<>(); nb.raiz = new Node<>(1); nb.raiz.derecho = new Node<>(2); nb.raiz.derecho.derecho = new Node<>(3);
        nb.actualizarAltura(nb.raiz.derecho.derecho); nb.actualizarAltura(nb.raiz.derecho); nb.actualizarAltura(nb.raiz);
        System.out.println(nb.esAVL() ? "true es avl" : "false no es avl");
        System.out.println("--- Fin ---\n");
    }

    /** 6) Devuelve la altura del árbol. */
    public int obtenerAltura() { return obtenerAlturaNodo(raiz); }


    // --- METODOS PRIVADOS ---

    // Inserción recursiva (el método de inserción fue removido en esta limpieza).

    // Búsqueda recursiva
    private Node<T> buscarRec(Node<T> nodo, T dato) {
        if (nodo == null) return null;
        int comparacion = dato.compareTo(nodo.valor);
        if (comparacion == 0) return nodo;
        if (comparacion < 0) return buscarRec(nodo.izquierdo, dato);
        return buscarRec(nodo.derecho, dato);
    }

    // Recorre en-order y añade a la lista resultado
    private void recorridoEnOrdenRec(Node<T> nodo, List<T> resultado) {
        if (nodo != null) {
            recorridoEnOrdenRec(nodo.izquierdo, resultado);
            resultado.add(nodo.valor);
            recorridoEnOrdenRec(nodo.derecho, resultado);
        }
    }

    // Altura de un nodo (0 si es nulo)
    private int obtenerAlturaNodo(Node<T> nodo) { return nodo == null ? 0 : nodo.altura; }

    // Actualiza la altura de un nodo basándose en sus hijos
    private void actualizarAltura(Node<T> nodo) {
        nodo.altura = 1 + Math.max(obtenerAlturaNodo(nodo.izquierdo), obtenerAlturaNodo(nodo.derecho));
    }

    // Factor de balance: altura(izq) - altura(derecha)
    private int obtenerBalance(Node<T> nodo) {
        return nodo == null ? 0 : obtenerAlturaNodo(nodo.izquierdo) - obtenerAlturaNodo(nodo.derecho);
    }

    // Rebalancea el nodo si es necesario aplicando rotaciones AVL
    private Node<T> rebalancear(Node<T> nodo) {
        int balance = obtenerBalance(nodo);
        int balIzq = obtenerBalance(nodo.izquierdo);
        int balDer = obtenerBalance(nodo.derecho);

        // Detectar caso y aplicar la rotación correspondiente
        if (balance < -1 && balDer <= 0) {
            // RR case -> rotación izquierda
            System.out.println("Rebalanceo: Rotación izquierda (RR) en nodo " + nodo.valor);
            return rotarIzquierda(nodo);
        }
        if (balance > 1 && balIzq >= 0) {
            // LL case -> rotación derecha
            System.out.println("Rebalanceo: Rotación derecha (LL) en nodo " + nodo.valor);
            return rotarDerecha(nodo);
        }
        if (balance > 1 && balIzq < 0) {
            // LR case -> rotación izquierda sobre hijo izquierdo, luego derecha
            System.out.println("Rebalanceo: Rotación izquierda-derecha (LR) en nodo " + nodo.valor);
            nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
            return rotarDerecha(nodo);
        }
        if (balance < -1 && balDer > 0) {
            // RL case -> rotación derecha sobre hijo derecho, luego izquierda
            System.out.println("Rebalanceo: Rotación derecha-izquierda (RL) en nodo " + nodo.valor);
            nodo.derecho = rotarDerecha(nodo.derecho);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    // Rotación izquierda: devuelve nueva raíz del subárbol
    private Node<T> rotarIzquierda(Node<T> y) {
        // rotación izquierda
        Node<T> x = y.derecho;
        Node<T> tempIzq = x.izquierdo;
        x.izquierdo = y;
        y.derecho = tempIzq;
        actualizarAltura(y);
        actualizarAltura(x);
        return x;
    }

    // Rotación derecha: devuelve nueva raíz del subárbol
    private Node<T> rotarDerecha(Node<T> y) {
        Node<T> x = y.izquierdo;
        Node<T> tempDer = x.derecho;
        x.derecho = y;
        y.izquierdo = tempDer;
        actualizarAltura(y);
        actualizarAltura(x);
        return x;
    }

    // Eliminación recursiva
    private Node<T> eliminarRec(Node<T> nodo, T dato) {
        if (nodo == null) return null;
        int cmp = dato.compareTo(nodo.valor);
        if (cmp < 0) nodo.izquierdo = eliminarRec(nodo.izquierdo, dato);
        else if (cmp > 0) nodo.derecho = eliminarRec(nodo.derecho, dato);
        else {
            // Nodo encontrado
            if (nodo.izquierdo == null || nodo.derecho == null) {
                Node<T> temp = (nodo.izquierdo != null) ? nodo.izquierdo : nodo.derecho;
                if (temp == null) return null; // Sin hijos
                else nodo = temp; // Un hijo
            } else {
                // Dos hijos: buscar sucesor in-order
                Node<T> sucesor = nodoValorMinimo(nodo.derecho);
                nodo.valor = sucesor.valor;
                nodo.derecho = eliminarRec(nodo.derecho, sucesor.valor);
            }
        }
        actualizarAltura(nodo);
        return rebalancear(nodo);
    }

    // Devuelve el nodo con el valor mínimo en el subárbol
    private Node<T> nodoValorMinimo(Node<T> nodo) {
        Node<T> actual = nodo;
        while (actual.izquierdo != null) actual = actual.izquierdo;
        return actual;
    }

    // Impresión recursiva con prefijos para representar la jerarquía
    private void imprimirArbolRec(Node<T> nodo, String prefix, boolean isTail) {
        if (nodo != null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + nodo.valor + " (fe=" + obtenerBalance(nodo) + ")");
            imprimirArbolRec(nodo.izquierdo, prefix + (isTail ? "    " : "│   "), false);
            imprimirArbolRec(nodo.derecho, prefix + (isTail ? "    " : "│   "), true);
        }
    }

    // Clase interna para nodos del árbol
    private static class Node<T> {
        T valor;
        Node<T> izquierdo, derecho;
        int altura;

        Node(T valor) { this.valor = valor; this.altura = 1; }
    }

    // Método recursivo que devuelve si el subárbol es AVL y su altura
    private Resultado esAVLRec(Node<T> nodo, T min, T max) {
        if (nodo == null) return new Resultado(true, 0);

        // Propiedad de ABB: verificar rangos
        if ((min != null && nodo.valor.compareTo(min) <= 0) ||
            (max != null && nodo.valor.compareTo(max) >= 0)) {
            return new Resultado(false, 0);
        }

        // Verificar hijos
        Resultado izq = esAVLRec(nodo.izquierdo, min, nodo.valor);
        Resultado der = esAVLRec(nodo.derecho, nodo.valor, max);

        boolean balanceado = izq.esAVL && der.esAVL &&
            Math.abs(izq.altura - der.altura) <= 1;

        int altura = 1 + Math.max(izq.altura, der.altura);

        return new Resultado(balanceado, altura);
    }

    // Clase auxiliar interna
    private static class Resultado {
        boolean esAVL;
        int altura;
        Resultado(boolean e, int a) { esAVL = e; altura = a; }
    }

    
}
