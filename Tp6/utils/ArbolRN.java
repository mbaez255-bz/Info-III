package Tp6.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación didáctica de un Árbol Rojo-Negro (RBT).
 *
 * Contiene implementaciones mínimas para las tareas del práctico:
 * 1) Definición de nodo y NIL sentinel
 * 2) Rotación izquierda
 * 3) Rotación derecha
 * 4) Inserción como ABB (sin balance)
 * 5) Clasificador de caso para fixInsert
 * 6-7) fixInsert: recoloreos y rotaciones (LL, RR, LR, RL y tío rojo)
 * 8) successor / predecessor
 * 9) Consulta por rango [a,b]
 * 10) Verificadores de invariantes (raíz negra, sin rojo-rojo, altura negra)
 */
public class ArbolRN<K extends Comparable<K>, V> {

    /**
     * Nodo interno del RBT.
     * Contiene key/val, punteros left/right/parent y un booleano 'rojo' que
     * indica el color (true = rojo, false = negro).
     * Ejercicio relacionado: 1) Nodo y NIL sentinel.
     */
    public class RBNode {
        K key;
        V val;
        RBNode left, right, parent;
        boolean rojo; // true = rojo, false = negro

        RBNode(K key, V val, boolean rojo) {
            this.key = key;
            this.val = val;
            this.rojo = rojo;
            // los punteros se asignan después; por defecto null
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        // getters públicos para acceder desde fuera del paquete
        public K getKey() { return key; }
        public V getVal() { return val; }
    }

    // NIL sentinel único por árbol
    private final RBNode NIL;
    private RBNode root;

    public ArbolRN() {
        NIL = new RBNode(null, null, false); // negro
        NIL.left = NIL.right = NIL.parent = NIL;
        root = NIL;
    }

    // Impresión lateral simple para visualización (valor[color])
    public void imprimirArbol() {
        imprimirRec(root, "", true);
    }

    private void imprimirRec(RBNode nodo, String pref, boolean esUltimo) {
        if (nodo == NIL) return;
        System.out.print(pref);
        System.out.print(esUltimo ? "└─" : "├─");
        System.out.println(nodo.key + "[" + (nodo.rojo ? "R" : "B") + "]");
        imprimirRec(nodo.left, pref + (esUltimo ? "  " : "│ "), false);
        imprimirRec(nodo.right, pref + (esUltimo ? "  " : "│ "), true);
    }

    // 2) Rotación izquierda
    /**
     * rotateLeft(x): realiza la rotación izquierda sobre el nodo x.
     * Actualiza punteros parent/left/right y la raíz si es necesario.
    *
    * Lógica paso a paso:
    * 1. Asumimos que x.right = y existe (si no, no hay rotación).
    * 2. Trasladamos el subárbol izquierdo de y a ser el subárbol derecho de x:
    *    x.right = y.left. Si y.left != NIL, su parent debe apuntar a x.
    * 3. Enlazamos y en la posición que ocupaba x (ajustando parent de y):
    *    si x era root, ahora root = y; si x era hijo izquierdo/derecho de su padre,
    *    actualizamos el puntero correspondiente.
    * 4. Ponemos x como hijo izquierdo de y y actualizamos parent(x) = y.
    *
    * Este procedimiento preserva la propiedad de BST (orden) y solo cambia
    * la forma del subárbol local.
     */
    public void rotateLeft(RBNode x) {
        if (x == NIL || x.right == NIL) return;
        RBNode y = x.right;
        x.right = y.left;
        if (y.left != NIL) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == NIL) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    // 3) Rotación derecha
    /**
     * rotateRight(y): realiza la rotación derecha sobre el nodo y.
     * Simétrico a rotateLeft.
    *
    * Lógica paso a paso (simétrica a rotateLeft):
    * 1. Asumimos que y.left = x existe.
    * 2. Trasladamos el subárbol derecho de x a ser el subárbol izquierdo de y.
    * 3. Enlazamos x en la posición que ocupaba y (ajustando parent).
    * 4. Hacemos y el hijo derecho de x y actualizamos parents.
    *
    * Preserva la propiedad de BST en el subárbol afectado.
     */
    public void rotateRight(RBNode y) {
        if (y == NIL || y.left == NIL) return;
        RBNode x = y.left;
        y.left = x.right;
        if (x.right != NIL) x.right.parent = y;
        x.parent = y.parent;
        if (y.parent == NIL) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }
        x.right = y;
        y.parent = x;
    }

    // 4) Inserción como ABB (sin balance): devuelve el nodo creado (rojo)
    /**
     * insertBST: inserta la clave/valor como en un BST normal, creando un nodo
     * rojo con left/right/parent = NIL. No realiza recoloreos ni rotaciones.
     * Devuelve el nodo creado.
    *
    * Lógica paso a paso:
    * 1. Crear nodo z rojo con hijos/padre = NIL.
    * 2. Recorrer desde root buscando la posición de inserción (y será el padre).
    *    - si key < x.key bajar a la izquierda; sino a la derecha.
    * 3. Enlazar z como hijo izquierdo o derecho de y según la comparación.
    * 4. Mantener la estructura BST; la corrección de colores/rotaciones se hará
    *    por separado (fixInsert) si se desea.
     */
    public RBNode insertBST(K key, V val) {
        RBNode z = new RBNode(key, val, true); // nuevo rojo
        z.left = z.right = z.parent = NIL;

        RBNode y = NIL;
        RBNode x = root;
        while (x != NIL) {
            y = x;
            if (key.compareTo(x.key) < 0) x = x.left;
            else x = x.right;
        }
        z.parent = y;
        if (y == NIL) {
            root = z;
        } else if (key.compareTo(y.key) < 0) {
            y.left = z;
        } else {
            y.right = z;
        }
        return z;
    }

    // 5) Clasificador de caso para fixInsert
    public enum Caso { TIO_ROJO, LL, RR, LR, RL }
    /**
     * clasificar(z): devuelve el caso a aplicar durante fixInsert, comparando
     * z con su padre p y su abuelo g. Posibles valores: TIO_ROJO, LL, RR, LR, RL.
    *
    * Lógica:
    * - Se obtiene p = z.parent y g = p.parent. El 'tio' es el otro hijo de g.
    * - Si el tío es rojo -> TIO_ROJO (caso de recoloreado).
    * - Si el tío es negro o NIL, decidimos entre LL/LR/RR/RL según la
    *   orientación de p respecto a g y de z respecto a p.
     */
    public Caso clasificar(RBNode z) {
        RBNode p = z.parent;
        RBNode g = p.parent;
        RBNode tio = (p == g.left) ? g.right : g.left;
        if (tio != NIL && tio.rojo) return Caso.TIO_ROJO;
        if (p == g.left) {
            if (z == p.left) return Caso.LL;
            else return Caso.LR;
        } else {
            if (z == p.right) return Caso.RR;
            else return Caso.RL;
        }
    }

    // 6-7) fixInsert con recoloreo y rotaciones (cubre tío rojo y rama izquierda/derecha)
    /**
     * fixInsert(z): corrige las propiedades RBT después de insertar z (suponiendo
     * que z fue creado rojo). Maneja el caso tío rojo (recoloreo) y las rotaciones
     * simples/dobles (LL, RR, LR, RL) con el recoloreo apropiado.
    *
    * Lógica general del algoritmo (iterativa):
    * - Mientras el padre de z sea rojo (violación de rojo-rojo):
    *   1) Clasificar el caso (tío rojo o una de las cuatro formas).
    *   2) Si TIO_ROJO: colorear padre y tío de negro, abuelo de rojo y subir z = g
    *      (propagación del problema hacia arriba).
    *   3) Si LL: rotación derecha en g hace que p sea nueva subraíz; recolorear
    *      p negro y g rojo; terminar.
    *   4) Si LR: rotación izquierda en p (convierte LR en LL), luego rotateRight(g)
    *      y recolorear p negro y g rojo; terminar.
    *   5) Casos simétricos RR y RL hacen lo análogo con rotaciones izquierdas/derechas.
    * - Finalmente garantizar que la raíz sea negra.
    *
    * Comentario: este método asume que p y g existen cuando se llama desde flujos
    * controlados (e.g., tras insertBST seguido de fixInsert). En una versión robusta
    * habría checks adicionales por seguridad.
     */
    public void fixInsert(RBNode z) {
        while (z.parent != NIL && z.parent.rojo) {
            RBNode p = z.parent;
            RBNode g = p.parent;
            Caso c = clasificar(z);
            if (c == Caso.TIO_ROJO) {
                RBNode tio = (p == g.left) ? g.right : g.left;
                p.rojo = false;
                tio.rojo = false;
                g.rojo = true;
                z = g; // subir
            } else if (c == Caso.LL) {
                rotateRight(g);
                p.rojo = false;
                g.rojo = true;
                break;
            } else if (c == Caso.LR) {
                rotateLeft(p);
                // ahora es LL
                rotateRight(g);
                p.rojo = false;
                g.rojo = true;
                break;
            } else if (c == Caso.RR) {
                rotateLeft(g);
                p.rojo = false;
                g.rojo = true;
                break;
            } else if (c == Caso.RL) {
                rotateRight(p);
                rotateLeft(g);
                p.rojo = false;
                g.rojo = true;
                break;
            }
        }
        root.rojo = false; // asegurar raíz negra
    }

    // 8) successor y predecessor
    /**
     * minimo(x)/maximo(x): utilidades para encontrar el mínimo/máximo en un
     * subárbol (más a la izquierda/derecha).
    *
    * Lógica: para mínimo bajar repetidamente por left hasta alcanzar NIL.
     */
    public RBNode minimo(RBNode x) {
        while (x.left != NIL) x = x.left;
        return x;
    }

    public RBNode maximo(RBNode x) {
        while (x.right != NIL) x = x.right;
        return x;
    }

    /**
     * successor(x): devuelve el siguiente nodo en orden (menor > x.key), o NIL/null
     * si no existe. Usa el subárbol derecho o sube por parent según la regla BST.
    *
    * Lógica:
    * - Si x.right != NIL: el sucesor es el mínimo del subárbol derecho.
    * - Sino, subir por parent hasta encontrar un ancestro y tal que x sea su
    *   hijo izquierdo; ese ancestro será el sucesor.
     */
    public RBNode successor(RBNode x) {
        if (x.right != NIL) return minimo(x.right);
        RBNode y = x.parent;
        while (y != NIL && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    /**
     * predecessor(x): devuelve el anterior en orden (mayor < x.key).
    *
    * Lógica: simétrica a successor: buscar máximo en left o subir hasta encontrar
    * un ancestro para el cual x sea hijo derecho.
     */
    public RBNode predecessor(RBNode x) {
        if (x.left != NIL) return maximo(x.left);
        RBNode y = x.parent;
        while (y != NIL && x == y.left) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    // 9) Consulta por rango [a,b]
    /**
     * rango(a,b): devuelve una lista de claves en [a,b] en orden. Implementa
     * un in-order acotado para evitar visitar subárboles fuera del rango.
     */
    public List<K> rango(K a, K b) {
        List<K> res = new ArrayList<>();
        rangoRec(root, a, b, res);
        return res;
    }

    private void rangoRec(RBNode nodo, K a, K b, List<K> res) {
        if (nodo == NIL) return;
        if (nodo.key.compareTo(a) > 0) rangoRec(nodo.left, a, b, res);
        if (nodo.key.compareTo(a) >= 0 && nodo.key.compareTo(b) <= 0) res.add(nodo.key);
        if (nodo.key.compareTo(b) < 0) rangoRec(nodo.right, a, b, res);
    }

    // 10) Verificadores de invariantes
    /**
     * raizNegra(): true si la raíz es negra (o árbol vacío). 
     */
    public boolean raizNegra() {
        return root == NIL || !root.rojo;
    }

    public boolean sinRojoRojo() {
        return sinRojoRojoRec(root);
    }

    private boolean sinRojoRojoRec(RBNode nodo) {
        if (nodo == NIL) return true;
        if (nodo.rojo) {
            if (nodo.left.rojo || nodo.right.rojo) return false;
        }
        return sinRojoRojoRec(nodo.left) && sinRojoRojoRec(nodo.right);
    }

    // devuelve black-height si válida, o -1 si no (diferentes alturas negras)
    /**
     * alturaNegra(): devuelve la black-height del árbol si es consistente en todos
     * los caminos; devuelve -1 si hay discrepancia (invariante rota).
     * Ejercicio: 10) alturaNegra.
     */
    public int alturaNegra() {
        return alturaNegraRec(root);
    }

    private int alturaNegraRec(RBNode nodo) {
        if (nodo == NIL) return 0;
        int li = alturaNegraRec(nodo.left);
        int ld = alturaNegraRec(nodo.right);
        if (li == -1 || ld == -1) return -1;
        if (li != ld) return -1;
        return li + (nodo.rojo ? 0 : 1);
    }

    // Métodos auxiliares para pruebas desde Main
    public RBNode getRoot() { return root; }

    public RBNode buscarNodo(K key) {
        RBNode x = root;
        while (x != NIL) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x;
            else if (cmp < 0) x = x.left;
            else x = x.right;
        }
        return null;
    }

    // Inserta y repara (helper simple que combina insertBST + fixInsert)
    public void insertarYArreglar(K key, V val) {
        RBNode z = insertBST(key, val);
        fixInsert(z);
    }
}
