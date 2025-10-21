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
    // Estadísticas de rotaciones
    private int contadorRotaciones = 0;
    private final List<String> logRotaciones = new ArrayList<>();
    // Modo: true durante una operación pública de insertar para imprimir info en rebalanceo
    private boolean modoInsertando = false;
    // Mensajes generados durante una operación de inserción (se acumulan y se muestran al final)
    private final List<String> mensajesModoInsertar = new ArrayList<>();

    /**
     * Inserta un elemento en el árbol.
     * @param dato elemento a insertar (no nulo)
     */

    public void insertar(T dato) {
        if (dato == null) throw new IllegalArgumentException("El dato no puede ser nulo");
        // Preparar mensajes de inserción
        mensajesModoInsertar.clear();

        // Mostrar árbol antes de la inserción (solo dibujo compacto y recorrido antes)
        System.out.println("\nÁrbol antes de insertar " + dato + ":");
    if (raiz == null) System.out.println("(vacío)"); else imprimirArbol();
        System.out.println("Recorrido in-order antes: " + recorridoEnOrden());

        try {
            modoInsertando = true;
            raiz = insertarRec(raiz, dato);
        } finally {
            modoInsertando = false;
        }

        // Mostrar árbol después de la inserción (solo dibujo compacto)
        System.out.println("Árbol después de insertar " + dato + ":");
    if (raiz == null) System.out.println("(vacío)"); else imprimirArbol();

        // Mostrar recorrido in-order después
        System.out.println("Recorrido in-order después: " + recorridoEnOrden());
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

        // Detectar caso y registrar antes de aplicar rotación
        if (balance < -1 && balDer <= 0) {
            String msg = "CASO RR detectado en nodo=" + nodo.valor + " (FE=" + balance + ")";
            logRotaciones.add(msg);
            if (modoInsertando) {
                int altIzq = obtenerAlturaNodo(nodo.izquierdo);
                int altDer = obtenerAlturaNodo(nodo.derecho);
                System.out.println("FE antes de balancear en nodo " + nodo.valor + ": " + balance + ".");
                System.out.println("Acción: se aplicará RR");
                System.out.println("Porque altura(izq)=" + altIzq + ", altura(der)=" + altDer + " -> FE = " + altIzq + " - " + altDer + " = " + balance);
            }
            return rotarIzquierda(nodo);
        }
        if (balance > 1 && balIzq >= 0) {
            String msg = "CASO LL detectado en nodo=" + nodo.valor + " (FE=" + balance + ")";
            logRotaciones.add(msg);
            if (modoInsertando) {
                int altIzq = obtenerAlturaNodo(nodo.izquierdo);
                int altDer = obtenerAlturaNodo(nodo.derecho);
                System.out.println("FE antes de balancear en nodo " + nodo.valor + ": " + balance + ".");
                System.out.println("Acción: se aplicará LL");
                System.out.println("Porque altura(izq)=" + altIzq + ", altura(der)=" + altDer + " -> FE = " + altIzq + " - " + altDer + " = " + balance);
            }
            return rotarDerecha(nodo);
        }
        if (balance > 1 && balIzq < 0) {
            String msg = "CASO LR detectado en nodo=" + nodo.valor + " (FE=" + balance + ")";
            logRotaciones.add(msg);
            if (modoInsertando) {
                int altIzq = obtenerAlturaNodo(nodo.izquierdo);
                int altDer = obtenerAlturaNodo(nodo.derecho);
                System.out.println("FE antes de balancear en nodo " + nodo.valor + ": " + balance + ".");
                System.out.println("Acción: se aplicará LR");
                System.out.println("Porque altura(izq)=" + altIzq + ", altura(der)=" + altDer + " -> FE = " + altIzq + " - " + altDer + " = " + balance);
            }
            nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
            return rotarDerecha(nodo);
        }
        if (balance < -1 && balDer > 0) {
            String msg = "CASO RL detectado en nodo=" + nodo.valor + " (FE=" + balance + ")";
            logRotaciones.add(msg);
            if (modoInsertando) {
                int altIzq = obtenerAlturaNodo(nodo.izquierdo);
                int altDer = obtenerAlturaNodo(nodo.derecho);
                System.out.println("FE antes de balancear en nodo " + nodo.valor + ": " + balance + ".");
                System.out.println("Acción: se aplicará RL");
                System.out.println("Porque altura(izq)=" + altIzq + ", altura(der)=" + altDer + " -> FE = " + altIzq + " - " + altDer + " = " + balance);
            }
            nodo.derecho = rotarDerecha(nodo.derecho);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    // Rotación izquierda: devuelve nueva raíz del subárbol
    private Node<T> rotarIzquierda(Node<T> y) {
        // contar y registrar la rotación concreta
    contadorRotaciones++;
    logRotaciones.add("rotacionIzquierda en nodo=" + y.valor);
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
    contadorRotaciones++;
    logRotaciones.add("rotacionDerecha en nodo=" + y.valor);
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

    // ---------- Funciones y estructuras públicas para análisis ----------
    /** Devuelve la cantidad de rotaciones realizadas desde el último reinicio. */
    public int obtenerContadorRotaciones() { return contadorRotaciones; }

    /** Devuelve un registro (lista) con los eventos de rotación y casos detectados. */
    public List<String> obtenerLogRotaciones() { return new ArrayList<>(logRotaciones); }

    /** Reinicia el contador y registro de rotaciones. */
    public void reiniciarEstadisticasRotaciones() { contadorRotaciones = 0; logRotaciones.clear(); }

    /** Representa información de un nodo: valor, altura y factor de equilibrio. */
    public static class InfoNodo<E> {
        public final E valor;
        public final int altura;
        public final int fe;

        public InfoNodo(E valor, int altura, int fe) { this.valor = valor; this.altura = altura; this.fe = fe; }

        @Override
        public String toString() { return "(" + valor + ", h=" + altura + ", fe=" + fe + ")"; }
    }

    /** Devuelve una lista con InfoNodo en recorrido in-order (valor, altura, FE). */
    public List<InfoNodo<T>> listarInfoNodos() {
        List<InfoNodo<T>> res = new ArrayList<>();
        listarInfoNodosRec(raiz, res);
        return res;
    }

    private void listarInfoNodosRec(Node<T> nodo, List<InfoNodo<T>> res) {
        if (nodo == null) return;
        listarInfoNodosRec(nodo.izquierdo, res);
        int altura = nodo.altura;
        int fe = obtenerBalance(nodo);
        res.add(new InfoNodo<>(nodo.valor, altura, fe));
        listarInfoNodosRec(nodo.derecho, res);
    }

    /** Imprime el árbol con datos de cada nodo (altura y FE). */
    public void imprimirArbolConDatos() {
        // Por defecto imprimimos en formato compacto vertical (raíz arriba)
        imprimirArbolSimpleConFE();
    }


    /** Imprime el árbol por niveles (raíz arriba), mostrando (valor, h, fe) por nodo. */
    public void imprimirArbolVertical() {
        if (raiz == null) {
            System.out.println("(vacío)");
            return;
        }
        List<Node<T>> current = new ArrayList<>();
        current.add(raiz);
        while (!current.isEmpty()) {
            List<Node<T>> next = new ArrayList<>();
            StringBuilder line = new StringBuilder();
            for (Node<T> n : current) {
                if (n == null) {
                    line.append("    ");
                    next.add(null);
                    next.add(null);
                } else {
                    String info = n.valor + "(h=" + n.altura + ",fe=" + obtenerBalance(n) + ")";
                    line.append(info).append("   ");
                    next.add(n.izquierdo);
                    next.add(n.derecho);
                }
            }
            // trim trailing spaces
            System.out.println(line.toString().trim());
            // prepare next level: remove trailing nulls
            int last = next.size() - 1;
            while (last >= 0 && next.get(last) == null) last--;
            if (last < 0) break;
            current = new ArrayList<>(next.subList(0, last + 1));
        }
        // Imprimir caminos desde la raíz para cada nodo (pre-order)
        System.out.println();
        System.out.println("Caminos (desde la raíz, L = hijo izquierdo, R = hijo derecho):");
        List<String> caminos = new ArrayList<>();
        listarInfoConCaminoRec(raiz, "", caminos);
        for (String s : caminos) System.out.println(s);
    }

    /** Imprime el árbol en formato compacto con valores centrados y conectores '/' '\\' como en la imagen. */
    /** Imprime el árbol de forma simple: raíz y luego cada hijo en línea nueva con indentación y FE. */
    public void imprimirArbolSimpleConFE() {
        imprimirArbolSimpleRec(raiz, "");
    }

    private void imprimirArbolSimpleRec(Node<T> nodo, String pref) {
        if (nodo == null) return;
        System.out.println(pref + nodo.valor + " (fe=" + obtenerBalance(nodo) + ")");
        imprimirArbolSimpleRec(nodo.izquierdo, pref + "  ");
        imprimirArbolSimpleRec(nodo.derecho, pref + "  ");
    }

    /** Devuelve una lista de strings con el camino desde la raíz a cada nodo y su info (pre-order). */
    public List<String> listarInfoConCamino() {
        List<String> res = new ArrayList<>();
        listarInfoConCaminoRec(raiz, "", res);
        return res;
    }

    private void listarInfoConCaminoRec(Node<T> nodo, String path, List<String> res) {
        if (nodo == null) return;
        String camino = path.isEmpty() ? "root" : path.trim();
        res.add(camino + " -> " + nodo.valor + " (h=" + nodo.altura + ", fe=" + obtenerBalance(nodo) + ")");
        listarInfoConCaminoRec(nodo.izquierdo, path + " L", res);
        listarInfoConCaminoRec(nodo.derecho, path + " R", res);
    }

    /**
     * Imprime el árbol mostrando el camino desde la raíz a cada nodo usando ' ----- ' como separador
     * entre niveles. Cada línea mostrará: <camino> ----- <valor> (h=<altura>, fe=<fe>)
     * Ejemplo:
     * root ----- 10 (h=2, fe=1)
     * root ----- L ----- 5 (h=1, fe=0)
     */
    public void imprimirArbolConCaminosDash() {
        if (raiz == null) {
            System.out.println("(vacío)");
            return;
        }
        List<String> lineas = new ArrayList<>();
        imprimirArbolConCaminosDashRec(raiz, "root", lineas);
        for (String s : lineas) {
            System.out.println(s);
        }
    }

    /** Imprime el árbol en formato ASCII simple con la raíz arriba y '/' '\\' para ramas.
     * Ejemplo para un árbol pequeño:
     *   2
     *  / \
     * 1   4
     */
    public void imprimirArbolAscii() {
        if (raiz == null) {
            System.out.println("(vacío)");
            return;
        }
        // Construimos líneas por niveles con posiciones aproximadas.
        List<StringBuilder> canvas = new ArrayList<>();
        buildAscii(raiz, 0, canvas);
        for (StringBuilder sb : canvas) System.out.println(sb.toString());
    }

    // Helper que coloca cada nodo en una línea específica y añade conectores
    private void buildAscii(Node<T> nodo, int depth, List<StringBuilder> canvas) {
        if (nodo == null) return;
        // Asegurar que exista la línea para este depth*2 (valor y posible conectores)
        int lineIdx = depth * 2;
        while (canvas.size() <= lineIdx + 1) canvas.add(new StringBuilder());

        String val = String.valueOf(nodo.valor);
        // Center-ish placement: si la línea está vacía, agregamos valor, sino lo separamos con un espacio
        StringBuilder lineVal = canvas.get(lineIdx);
        if (lineVal.length() > 0) lineVal.append(" ");
        lineVal.append(val);

        // Conectores en la siguiente línea si tiene hijos
        StringBuilder lineConn = canvas.get(lineIdx + 1);
        boolean hasIzq = nodo.izquierdo != null;
        boolean hasDer = nodo.derecho != null;
        if (hasIzq || hasDer) {
            if (lineConn.length() > 0) lineConn.append(" ");
            if (hasIzq) lineConn.append("/"); else lineConn.append(" ");
            lineConn.append(" ");
            if (hasDer) lineConn.append("\\"); else lineConn.append(" ");
        }

        // Recorrer hijos
        buildAscii(nodo.izquierdo, depth + 1, canvas);
        buildAscii(nodo.derecho, depth + 1, canvas);
    }

    private void imprimirArbolConCaminosDashRec(Node<T> nodo, String camino, List<String> out) {
        if (nodo == null) return;
        String linea = camino + " ----- " + nodo.valor + " (h=" + nodo.altura + ", fe=" + obtenerBalance(nodo) + ")";
        out.add(linea);
        imprimirArbolConCaminosDashRec(nodo.izquierdo, camino + " ----- L", out);
        imprimirArbolConCaminosDashRec(nodo.derecho, camino + " ----- R", out);
    }
}
