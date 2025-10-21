
package utils;

/**
 * Representa una tarea con descripción y estado (pendiente o completada).
 */
public class Tarea {
    private String descripcion;
    private boolean completada;

    /**
     * Constructor vacío (no recomendado salvo para frameworks).
     */
    public Tarea() {
    }

    /**
     * Crea una tarea pendiente con la descripción dada.
     */
    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.completada = false;
    }

    /**
     * Devuelve una representación legible de la tarea.
     */
    @Override
    public String toString() {
        return "Descripción: " + descripcion +
                "\nEstado: " + (completada ? "Completada" : "Pendiente");
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean estaCompletada() {
        return completada;
    }

    /**
     * Marca la tarea como completada.
     */
    public void marcarCompletada() {
        this.completada = true;
    }

    /**
     * Devuelve la tarea en formato para guardar en archivo.
     */
    public String paraFormatoArchivo() {
        return descripcion + ";" + (completada ? "Completada" : "Pendiente");
    }

    /**
     * Crea una tarea a partir de una línea de archivo.
     */
    public static Tarea deFormatoArchivo(String linea) {
        String[] partes = linea.split(";");
        Tarea t = new Tarea(partes[0]);
        if (partes.length > 1 && partes[1].equalsIgnoreCase("Completada")) {
            t.setCompletada(true);
        }
        return t;
    }
}


