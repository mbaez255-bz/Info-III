package Tp5.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * ArbolAVL.java
 * Implementación genérica y documentada de un árbol AVL (árbol binario de búsqueda auto-balanceado).
 * @param <T> Tipo de dato almacenado (debe ser Comparable)
 */
public class ArbolAVL<T extends Comparable<T>> {
    // Raíz del árbol
    private Node<T> raiz;

    /**
     * Inserta un elemento en el árbol.
     * @param dato elemento a insertar (no nulo)
     */

    public void insertar(T dato) {
        if (dato == null) throw new IllegalArgumentException("El dato no puede ser nulo");
        raiz = insertarRec(raiz, dato);
    }


    /** Busca si un elemento existe en el árbol. */
    public boolean buscar(T dato) {
        return buscarRec(raiz, dato) != null;
    }

    /** Elimina un elemento del árbol (si existe). */
    public void eliminar(T dato) {
        raiz = eliminarRec(raiz, dato);
    }

    /**
     * Recorre el árbol en orden (in-order) y devuelve los valores en una lista.
     Devuelve una lista con los elementos del árbol en orden (in-order).*/
    public List<T> recorridoEnOrden() {
        List<T> resultado = new ArrayList<>();
        recorridoEnOrdenRec(raiz, resultado);
        return resultado;
    }
 

    /** Devuelve la altura del árbol. */
    public int obtenerAltura() { return obtenerAlturaNodo(raiz); }

    /** Imprime el árbol de forma textual para inspección. */
    public void imprimirArbol() { imprimirArbolRec(raiz, "", true); }


    // --- Métodos privados ---

    // Inserción recursiva: devuelve el subárbol balanceado
    private Node<T> insertarRec(Node<T> nodo, T dato) {
        if (nodo == null) return new Node<>(dato);
        int comparacion = dato.compareTo(nodo.valor);
        if (comparacion < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, dato);
        } else if (comparacion > 0) {
            nodo.derecho = insertarRec(nodo.derecho, dato);
        } else {
            return nodo; // No se permiten duplicados
        }
        actualizarAltura(nodo);
        return rebalancear(nodo);
    }

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

        // Rotación izquierda simple
        if (balance < -1 && balDer <= 0) return rotarIzquierda(nodo);

        // Rotación derecha simple
        if (balance > 1 && balIzq >= 0) return rotarDerecha(nodo);

        // Rotación doble izquierda-derecha
        if (balance > 1 && balIzq < 0) {
            nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
            return rotarDerecha(nodo);
        }

        // Rotación doble derecha-izquierda
        if (balance < -1 && balDer > 0) {
            nodo.derecho = rotarDerecha(nodo.derecho);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    // Rotación izquierda: devuelve nueva raíz del subárbol
    private Node<T> rotarIzquierda(Node<T> y) {
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
            System.out.println(prefix + (isTail ? "└── " : "├── ") + nodo.valor + " (h=" + nodo.altura + ")");
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
}
