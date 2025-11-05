package structures;

/**
 * Cola Circular con capacidad fija (Overflow Control)
 * Complejidad: O(1) para todas las operaciones
 * Uso: Sala de espera física (Ejercicio 4)
 * Cuando está llena, nuevas llegadas desbordan al más antiguo
 */

public class ColaCircular {
    private String[] cola;
    private int front;
    private int rear;
    private int size;
    private int capacidad;

    public ColaCircular(int capacidad) {
        this.capacidad = capacidad;
        this.cola = new String[capacidad];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    /**
     * Agrega un paciente (DNI) a la cola
     * Si está llena, elimina automáticamente al más antiguo
     * Complejidad: O(1)
     */
    public void llega(String dni) {
        if (dni == null) return;

        if (isFull()) {
            // Overflow: eliminar el más antiguo (front)
            front = (front + 1) % capacidad;
            size--;
        }

        rear = (rear + 1) % capacidad;
        cola[rear] = dni;
        size++;
    }

    /**
     * Atiende al siguiente paciente (FIFO)
     * Complejidad: O(1)
     */
    public String atiende() {
        if (isEmpty()) {
            return null;
        }

        String dni = cola[front];
        cola[front] = null; // Limpiar
        front = (front + 1) % capacidad;
        size--;
        return dni;
    }

    /**
     * Consulta el siguiente sin eliminarlo
     * Complejidad: O(1)
     */
    public String peek() {
        return isEmpty() ? null : cola[front];
    }

    /**
     * Tamaño actual de la cola
     * Complejidad: O(1)
     */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacidad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Devuelve el estado actual de la cola como string
     */
    public String getEstado() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        int indice = front;
        for (int i = 0; i < size; i++) {
            sb.append(cola[indice]);
            if (i < size - 1) sb.append(", ");
            indice = (indice + 1) % capacidad;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("ColaCircular[size=%d, capacidad=%d, estado=%s]", 
                            size, capacidad, getEstado());
    }
}
