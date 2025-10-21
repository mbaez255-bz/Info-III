
package Ordenamiento.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona la lista de pedidos de la pizzería y las operaciones sobre ellos.
 */
public class Pizzeria {
    private final List<Pedido> pedidos;
    private final Ordenador ordenador;

    public Pizzeria() {
        this.pedidos = new ArrayList<>();
        this.ordenador = new Ordenador();
    }

    /**
     * Agrega un nuevo pedido a la lista.
     */
    public void agregarPedido(Pedido pedido) {
        if (pedido == null) {
            System.out.println("Pedido inválido.");
            return;
        }
        pedidos.add(pedido);
        System.out.println("Pedido agregado correctamente.");
    }

    /**
     * Elimina el pedido en el índice dado.
     */
    public void eliminarPedido(int indice) {
        if (indice >= 0 && indice < pedidos.size()) {
            pedidos.remove(indice);
            System.out.println("Pedido eliminado.");
        } else {
            System.out.println("Índice de pedido inválido.");
        }
    }

    /**
     * Muestra la lista de pedidos.
     */
    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
        } else {
            System.out.println("\n--- LISTA DE PEDIDOS ---");
            for (int i = 0; i < pedidos.size(); i++) {
                System.out.println((i + 1) + ". " + pedidos.get(i));
            }
        }
    }

    /**
     * Ordena los pedidos por tiempo de preparación (inserción).
     */
    public void ordenarPorTiempo() {
        ordenador.insercion(pedidos);
    }

    /**
     * Ordena los pedidos por precio total (shellsort).
     */
    public void ordenarPorPrecio() {
        ordenador.shellsort(pedidos);
    }

    /**
     * Ordena los pedidos por nombre de cliente (quicksort).
     */
    public void ordenarPorNombre() {
        ordenador.quicksort(pedidos, 0, pedidos.size() - 1);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
