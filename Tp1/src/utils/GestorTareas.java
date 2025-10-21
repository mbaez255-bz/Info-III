
package utils;

import java.util.*;
import java.io.*;

/**
 * Gestiona una lista de tareas: agregar, listar, marcar, eliminar, guardar y cargar.
 */
public class GestorTareas {
    private final List<Tarea> tareas = new ArrayList<>();

    /**
     * Agrega una nueva tarea si la descripción no está vacía.
     */
    public void agregarTarea(String descripcion) {
        // .trim() elimina espacios al inicio y final de la cadena para evitar tareas con solo espacios
        if (descripcion == null || descripcion.trim().isEmpty()) {
            System.out.println("\nLa descripción no puede estar vacía");
            return;
        }
        tareas.add(new Tarea(descripcion.trim()));
        System.out.println("\nTarea agregada correctamente.");
    }

    /**
     * Muestra la lista de tareas con su estado.
     */
    public void listarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("\nNo hay tareas registradas.");
            return;
        }
        System.out.println("\n--- LISTA DE TAREAS ---");
        for (int i = 0; i < tareas.size(); i++) {
            System.out.println((i + 1) + ". " + tareas.get(i));
        }
    }

    /**
     * Marca como completada la tarea en el índice dado.
     */
    public void marcarTarea(int indice) {
        if (indice < 0 || indice >= tareas.size()) {
            System.out.println("\nÍndice de tarea inválido.");
            return;
        }
        Tarea tarea = tareas.get(indice);
        if (tarea.estaCompletada()) {
            System.out.println("\nLa tarea ya está completada.");
        } else {
            tarea.marcarCompletada();
            System.out.println("\nTarea nº " + (indice + 1) + " marcada como completada.");
        }
    }

    /**
     * Elimina todas las tareas que están completadas.
     */
    public void eliminarCompletadas() {
        int antes = tareas.size();
        tareas.removeIf(Tarea::estaCompletada);
        int eliminadas = antes - tareas.size();
        System.out.println("\nTareas completadas eliminadas: " + eliminadas);
    }

    /**
     * Guarda todas las tareas en el archivo (sobrescribe el archivo).
     */
    public void guardarEnArchivo(String nombre) {
        File archivo = new File(nombre);
        try (PrintWriter salida = new PrintWriter(new FileWriter(archivo, false))) {
            for (Tarea t : tareas) {
                salida.println(t.paraFormatoArchivo());
            }
            System.out.println("\nTareas guardadas en: " + nombre);
        } catch (IOException ex) {
            System.out.println("\nError al guardar las tareas: " + ex.getMessage());
        }
    }

    /**
     * Carga las tareas desde el archivo, reemplazando la lista actual.
     */
    public void cargarDesdeArchivo(String nombre) {
        File archivo = new File(nombre);
        if (!archivo.exists()) {
            System.out.println("\nNo se encontró archivo de tareas previo.");
            return;
        }
        tareas.clear();
        try (BufferedReader entrada = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = entrada.readLine()) != null) {
                tareas.add(Tarea.deFormatoArchivo(linea));
            }
            System.out.println("\nTareas cargadas desde: " + nombre);
        } catch (IOException ex) {
            System.out.println("\nError al cargar las tareas: " + ex.getMessage());
        }
    }
}

