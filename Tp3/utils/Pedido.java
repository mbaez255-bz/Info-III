package Tp3.utils;

/**
 * Representa un pedido realizado en la pizzería.
 */
public class Pedido {
    // Nombre del cliente que realiza el pedido
    private String nombreCliente;
    // Tiempo de preparación del pedido en minutos
    private int tiempoPreparacion;
    // Precio total del pedido
    private double precio;

    /**
     * Constructor para crear un pedido con todos sus datos.
     * @param nombreCliente Nombre del cliente
     * @param tiempoPreparacion Tiempo de preparación en minutos
     * @param precio Precio total del pedido
     */
    public Pedido(String nombreCliente, int tiempoPreparacion, double precio) {
        this.nombreCliente = nombreCliente;
        this.tiempoPreparacion = tiempoPreparacion;
        this.precio = precio;
    }

    /**
     * Establece el nombre del cliente.
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * Establece el tiempo de preparación.
     */
    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    /**
     * Establece el precio del pedido.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Devuelve el nombre del cliente.
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * Devuelve el tiempo de preparación en minutos.
     */
    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    /**
     * Devuelve el precio total del pedido.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Devuelve una representación legible del pedido para mostrar por consola.
     */
    @Override
    public String toString() {
        return "Cliente: " + nombreCliente +
                " | Tiempo: " + tiempoPreparacion + " min" +
                " | Precio: $" + precio;
    }
}
