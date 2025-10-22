package Tp3;

public class Queue<T> {
    private T[] array;
    private int front;  // Indice del primer elemento
    private int rear;   // Indice donde se insertara el siguiente elemento
    private int size;   // Cantidad actual de elementos
    private int capacity; // Capacidad maxima del arreglo

    // Suprime warning de conversion no segura porque Java
    // no permite crear arreglos genericos directamente.
    // Solucion: crear Object[] y convertirlo a T[]
    @SuppressWarnings("unchecked")

    public Queue(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    // Constructor por defecto con capacidad 10
    public Queue() {
        this.capacity = 10;
    }

    
    // Agrega un elemento al final de la cola
    public boolean enqueue(T data) {
        if (isFull()) {
            return false;
        }
        array[rear] = data;
        rear = (rear + 1) % capacity; // Avanza circularmente
        size++;
        return true;
    }

    //Elimina y retorna el elemento al frente de la cola
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T data = array[front];
        array[front] = null; // Liberar referencia
        front = (front + 1) % capacity; // Avanza circularmente
        size--;
        return data;
    }

    //Retorna el elemento al frente de la cola sin eliminarlo
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return array[front];
    }
    
    //Verifica si la cola esta vacia
    public boolean isEmpty() {
        return size == 0;
    }

    //Verifica si la cola esta llena
    public boolean isFull() {
        return size == capacity;
    }

    //Retorna la cantidad de elementos en la cola
    public int getSize() {
        return size;
    }

    //Retorna la capacidad maxima de la cola
    public int getCapacity() {
        return capacity;
    }

    
    //Limpia todos los elementos de la cola

    // Suprime warning porque recreamos el arreglo generico (mismo motivo que en el constructor)
    @SuppressWarnings("unchecked")
    public void clear() {
        array = (T[]) new Object[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    //Busca un elemento en la cola
    public boolean search(T data) {
        if (isEmpty()) {
            return false;
        }
        int index = front;
        for (int i = 0; i < size; i++) {
            if (array[index].equals(data)) {
                return true;
            }
            index = (index + 1) % capacity;
        }
        return false;
    }

    //Invierte el orden de los elementos en la cola
    public void reverse() {
        if (isEmpty() || size == 1) {
            return; // No hay nada que invertir
        }
        
        // Crear arreglo temporal para almacenar elementos en orden inverso
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[size];
        
        // Copiar elementos del final al principio
        int index = front;
        for (int i = size - 1; i >= 0; i--) {
            temp[i] = array[index];
            index = (index + 1) % capacity;
        }
        
        // Copiar de vuelta al arreglo original
        index = front;
        for (int i = 0; i < size; i++) {
            array[index] = temp[i];
            index = (index + 1) % capacity;
        }
    }

    //Imprime la cola
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int index = front;
        for (int i = 0; i < size; i++) {
            sb.append(array[index]);
            if (i < size - 1) {
                sb.append(", ");
            }
            index = (index + 1) % capacity;
        }
        sb.append("]");
        return sb.toString();
    }
}
