package structures;

import models.Paciente;
import java.util.*;

/**
 * Hash Table con Chaining y Rehash automático
 * Complejidad: O(1) promedio para put, get, remove
 * Load Factor: 0.75 (rehash cuando se supera)
 * Uso: Índice rápido de pacientes por DNI (Ejercicio 6)
 */

public class HashMapPacientes {
    
    private static class Nodo {
        String clave;
        Paciente valor;
        Nodo siguiente;

        Nodo(String clave, Paciente valor) {
            this.clave = clave;
            this.valor = valor;
            this.siguiente = null;
        }
    }

    private Nodo[] tabla;
    private int capacidad;
    private int size;
    private static final double LOAD_FACTOR = 0.75;

    public HashMapPacientes() {
        this(10); // Capacidad inicial
    }

    public HashMapPacientes(int capacidadInicial) {
        this.capacidad = capacidadInicial;
        this.tabla = new Nodo[capacidad];
        this.size = 0;
    }

    /**
     * Función hash usando método de multiplicación
     * Mejor distribución que módulo simple
     */
    private int hash(String clave) {
        if (clave == null) return 0;
        
        // Método: (hash * 31 + char) % capacidad
        int hash = 0;
        for (int i = 0; i < clave.length(); i++) {
            hash = (hash * 31 + clave.charAt(i)) % capacidad;
        }
        return Math.abs(hash);
    }

    /**
     * Inserta o actualiza un paciente
     * Complejidad: O(1) promedio
     */
    public void put(String dni, Paciente paciente) {
        if (dni == null || paciente == null) return;

        // Verificar load factor y rehash si es necesario
        if ((double) size / capacidad >= LOAD_FACTOR) {
            rehash();
        }

        int indice = hash(dni);
        Nodo actual = tabla[indice];

        // Buscar si ya existe
        while (actual != null) {
            if (actual.clave.equals(dni)) {
                actual.valor = paciente; // Actualizar
                return;
            }
            actual = actual.siguiente;
        }

        // Insertar al principio (chaining)
        Nodo nuevoNodo = new Nodo(dni, paciente);
        nuevoNodo.siguiente = tabla[indice];
        tabla[indice] = nuevoNodo;
        size++;
    }

    /**
     * Obtiene un paciente por DNI
     * Complejidad: O(1) promedio
     */
    public Paciente get(String dni) {
        if (dni == null) return null;

        int indice = hash(dni);
        Nodo actual = tabla[indice];

        while (actual != null) {
            if (actual.clave.equals(dni)) {
                return actual.valor;
            }
            actual = actual.siguiente;
        }

        return null; // No encontrado
    }

    /**
     * Elimina un paciente por DNI
     * Complejidad: O(1) promedio
     */
    public boolean remove(String dni) {
        if (dni == null) return false;

        int indice = hash(dni);
        Nodo actual = tabla[indice];
        Nodo previo = null;

        while (actual != null) {
            if (actual.clave.equals(dni)) {
                if (previo == null) {
                    tabla[indice] = actual.siguiente;
                } else {
                    previo.siguiente = actual.siguiente;
                }
                size--;
                return true;
            }
            previo = actual;
            actual = actual.siguiente;
        }

        return false;
    }

    /**
     * Verifica si contiene un DNI
     * Complejidad: O(1) promedio
     */
    public boolean containsKey(String dni) {
        return get(dni) != null;
    }

    /**
     * Rehash: duplica capacidad y redistribuye elementos
     * Se ejecuta cuando load factor > 0.75
     */
    private void rehash() {
        int nuevaCapacidad = capacidad * 2;
        Nodo[] nuevaTabla = new Nodo[nuevaCapacidad];

        // Redistribuir todos los elementos
        for (int i = 0; i < capacidad; i++) {
            Nodo actual = tabla[i];
            while (actual != null) {
                Nodo siguiente = actual.siguiente;
                
                // Recalcular hash con nueva capacidad
                int nuevoIndice = Math.abs((actual.clave.hashCode() * 31) % nuevaCapacidad);
                
                // Insertar en nueva tabla
                actual.siguiente = nuevaTabla[nuevoIndice];
                nuevaTabla[nuevoIndice] = actual;
                
                actual = siguiente;
            }
        }

        this.tabla = nuevaTabla;
        this.capacidad = nuevaCapacidad;
    }

    /**
     * Devuelve todas las claves (DNIs)
     * Complejidad: O(n)
     */
    public List<String> keys() {
        List<String> claves = new ArrayList<>();
        for (int i = 0; i < capacidad; i++) {
            Nodo actual = tabla[i];
            while (actual != null) {
                claves.add(actual.clave);
                actual = actual.siguiente;
            }
        }
        return claves;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Información de debugging para verificar distribución
     */
    public void printStats() {
        int bucketsUsados = 0;
        int maxCadena = 0;
        
        for (int i = 0; i < capacidad; i++) {
            int longitudCadena = 0;
            Nodo actual = tabla[i];
            
            if (actual != null) bucketsUsados++;
            
            while (actual != null) {
                longitudCadena++;
                actual = actual.siguiente;
            }
            
            maxCadena = Math.max(maxCadena, longitudCadena);
        }

        System.out.println("\n=== ESTADÍSTICAS HASH TABLE ===");
        System.out.println("Tamaño: " + size);
        System.out.println("Capacidad: " + capacidad);
        System.out.println("Load Factor actual: " + String.format("%.2f", (double) size / capacidad));
        System.out.println("Buckets usados: " + bucketsUsados + "/" + capacidad);
        System.out.println("Cadena más larga: " + maxCadena);
        System.out.println("===============================\n");
    }
}
