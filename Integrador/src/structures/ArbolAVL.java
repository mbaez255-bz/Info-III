package structures;

import models.Turno;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Árbol AVL para gestión de turnos ordenados por fecha/hora
 * Complejidad: O(log n) para insert, remove, buscar
 * Uso: Agenda por médico (Ejercicio 2)
 */

public class ArbolAVL {
    
    private class Nodo {
        Turno turno;
        Nodo izq, der;
        int altura;

        Nodo(Turno turno) {
            this.turno = turno;
            this.altura = 1;
        }
    }

    private Nodo raiz;
    private int size;

    public ArbolAVL() {
        this.raiz = null;
        this.size = 0;
    }

    private int altura(Nodo n) {
        return (n == null) ? 0 : n.altura;
    }

    private int factorBalance(Nodo n) {
        return (n == null) ? 0 : altura(n.izq) - altura(n.der);
    }

    private void actualizarAltura(Nodo n) {
        n.altura = 1 + Math.max(altura(n.izq), altura(n.der));
    }

    // Rotación simple derecha
    private Nodo rotarDerecha(Nodo y) {
        Nodo x = y.izq;
        Nodo T2 = x.der;

        x.der = y;
        y.izq = T2;

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    // Rotación simple izquierda
    private Nodo rotarIzquierda(Nodo x) {
        Nodo y = x.der;
        Nodo T2 = y.izq;

        y.izq = x;
        x.der = T2;

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    /**
     * Inserta un turno en el árbol AVL
     * Complejidad: O(log n)
     */
    public boolean insertar(Turno turno) {
        if (turno == null) return false;
        int sizePrevio = size;
        raiz = insertarRec(raiz, turno);
        return size > sizePrevio;
    }

    private Nodo insertarRec(Nodo nodo, Turno turno) {
        // 1. Inserción BST normal
        if (nodo == null) {
            size++;
            return new Nodo(turno);
        }

        int cmp = turno.compareTo(nodo.turno);
        if (cmp < 0) {
            nodo.izq = insertarRec(nodo.izq, turno);
        } else if (cmp > 0) {
            nodo.der = insertarRec(nodo.der, turno);
        } else {
            // Duplicado: no insertar
            return nodo;
        }

        // 2. Actualizar altura
        actualizarAltura(nodo);

        // 3. Obtener factor de balance
        int balance = factorBalance(nodo);

        // 4. Balancear si es necesario

        // Caso Left-Left
        if (balance > 1 && turno.compareTo(nodo.izq.turno) < 0) {
            return rotarDerecha(nodo);
        }

        // Caso Right-Right
        if (balance < -1 && turno.compareTo(nodo.der.turno) > 0) {
            return rotarIzquierda(nodo);
        }

        // Caso Left-Right
        if (balance > 1 && turno.compareTo(nodo.izq.turno) > 0) {
            nodo.izq = rotarIzquierda(nodo.izq);
            return rotarDerecha(nodo);
        }

        // Caso Right-Left
        if (balance < -1 && turno.compareTo(nodo.der.turno) < 0) {
            nodo.der = rotarDerecha(nodo.der);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    /**
     * Elimina un turno por ID
     * Complejidad: O(log n)
     */
    public boolean eliminar(String idTurno) {
        int sizePrevio = size;
        raiz = eliminarRec(raiz, idTurno);
        return size < sizePrevio;
    }

    private Nodo eliminarRec(Nodo nodo, String idTurno) {
        if (nodo == null) return null;

        // 1. BST delete normal
        if (idTurno.compareTo(nodo.turno.getId()) < 0) {
            nodo.izq = eliminarRec(nodo.izq, idTurno);
        } else if (idTurno.compareTo(nodo.turno.getId()) > 0) {
            nodo.der = eliminarRec(nodo.der, idTurno);
        } else {
            // Nodo encontrado
            if (nodo.izq == null || nodo.der == null) {
                nodo = (nodo.izq != null) ? nodo.izq : nodo.der;
                size--;
            } else {
                // Nodo con dos hijos: buscar sucesor inorden
                Nodo temp = nodoMinimo(nodo.der);
                nodo.turno = temp.turno;
                nodo.der = eliminarRec(nodo.der, temp.turno.getId());
            }
        }

        if (nodo == null) return null;

        // 2. Actualizar altura
        actualizarAltura(nodo);

        // 3. Balancear
        int balance = factorBalance(nodo);

        // Left-Left
        if (balance > 1 && factorBalance(nodo.izq) >= 0) {
            return rotarDerecha(nodo);
        }

        // Left-Right
        if (balance > 1 && factorBalance(nodo.izq) < 0) {
            nodo.izq = rotarIzquierda(nodo.izq);
            return rotarDerecha(nodo);
        }

        // Right-Right
        if (balance < -1 && factorBalance(nodo.der) <= 0) {
            return rotarIzquierda(nodo);
        }

        // Right-Left
        if (balance < -1 && factorBalance(nodo.der) > 0) {
            nodo.der = rotarDerecha(nodo.der);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    private Nodo nodoMinimo(Nodo nodo) {
        while (nodo.izq != null) {
            nodo = nodo.izq;
        }
        return nodo;
    }

    /**
     * Busca el siguiente turno disponible >= tiempo dado
     * Complejidad: O(log n)
     */
    public Turno siguienteDisponible(LocalDateTime tiempo) {
        return siguienteDisponibleRec(raiz, tiempo, null);
    }

    private Turno siguienteDisponibleRec(Nodo nodo, LocalDateTime tiempo, Turno mejor) {
        if (nodo == null) return mejor;

        if (nodo.turno.getFechaHora().isAfter(tiempo) || 
            nodo.turno.getFechaHora().isEqual(tiempo)) {
            // Este nodo es candidato, buscar en izquierda por si hay mejor
            mejor = nodo.turno;
            return siguienteDisponibleRec(nodo.izq, tiempo, mejor);
        } else {
            // Buscar en derecha
            return siguienteDisponibleRec(nodo.der, tiempo, mejor);
        }
    }

    /**
     * Devuelve todos los turnos en orden (inorden)
     * Complejidad: O(n)
     */
    public List<Turno> inorden() {
        List<Turno> resultado = new ArrayList<>();
        inordenRec(raiz, resultado);
        return resultado;
    }

    private void inordenRec(Nodo nodo, List<Turno> resultado) {
        if (nodo != null) {
            inordenRec(nodo.izq, resultado);
            resultado.add(nodo.turno);
            inordenRec(nodo.der, resultado);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
