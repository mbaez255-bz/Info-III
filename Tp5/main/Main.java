package Tp5.main;

import Tp5.utils.ArbolAVL;

/**
 * Ejecuta de forma hardcodeada las acciones del menú y muestra la salida en consola. 
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== DEMO Tp5: Árbol AVL ===");

        ArbolAVL<Integer> arbol = new ArbolAVL<>();

        // 1) Preparar un árbol controlado que provoque rebalance tras una eliminación
        System.out.println("\n1) Construyendo árbol de demo (preparado para eliminación->rebalance):");
        System.out.println("Datos a ingresar / valores usados: 30, 10, 40, 5, 20, 25, 50");
        arbol.crearArbolAVL();
        Integer candidatoAEliminar = 50; // número fijado para la demostración (la función crearArbolAVL no devuelve el candidato)

        //  2) Mostrar estado actual
        System.out.println("\n2) Árbol actual:");
        arbol.imprimirArbol();
        System.out.println("Recorrido in-order: " + arbol.recorridoEnOrden());
        System.out.println("¿es AVL (árbol actual)? " + arbol.esAVL());

        // 3) Buscar por clave (simula opción 2)
        System.out.println("\n3) Búsquedas: buscar 25 (debe existir) y 99 (no existe):");
        System.out.println("buscar(25): " + arbol.buscar(25));
        System.out.println("buscar(99): " + arbol.buscar(99));

        // 4) Eliminar por clave (simula opción 3) -> eliminamos el candidato preparado
        System.out.println("\n4) Eliminación que provocará rebalance: eliminar " + candidatoAEliminar);
        boolean ok = arbol.eliminar(candidatoAEliminar);
        System.out.println("eliminar(" + candidatoAEliminar + ") -> " + ok);
        System.out.println("Recorrido in-order: " + arbol.recorridoEnOrden());
        System.out.println("¿es AVL (después de eliminación)? " + arbol.esAVL());

        // Eliminar un segundo número adicional para mostrar otra operación
        int otroAEliminar = 25;
        System.out.println("\nTambién eliminamos el valor: " + otroAEliminar);
        boolean ok2 = arbol.eliminar(otroAEliminar);
        System.out.println("eliminar(" + otroAEliminar + ") -> " + ok2);
        System.out.println("Recorrido in-order: " + arbol.recorridoEnOrden());
        System.out.println("¿es AVL (después de segunda eliminación)? " + arbol.esAVL());

        // 5) Mostrar resumen final y altura
        System.out.println("\n5) Resumen final del árbol:");
        arbol.imprimirArbol();
        System.out.println("Altura del árbol: " + arbol.obtenerAltura());
        System.out.println("¿es AVL? " + arbol.esAVL());
        System.out.println("\n=== FIN DEMO Tp5 ===");
    }
}
