package Tp3;

public class Stack<T> {
    private T[] array;
    private int top;  // Indice del tope de la pila (-1 si esta vacia)
    private int capacity; // Capacidad maxima del arreglo

    // Suprime warning de conversion no segura porque Java no permite crear arreglos genericos directamente.
    // Solucion: crear Object[] y convertirlo a T[]
    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
        this.top = -1; // Pila vacia
    }

    // Constructor por defecto con capacidad 10
    public Stack() {
        this.capacity = 10;
    }

    //Agrega un elemento al tope de la pila
    public boolean push(T data) {
        if (isFull()) {
            return false;
        }
        top++;
        array[top] = data;
        return true;
    }

    //Elimina y retorna el elemento del tope de la pila
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T data = array[top];
        array[top] = null; // Liberar referencia
        top--;
        return data;
    }

    //Retorna el elemento del tope de la pila sin eliminarlo
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return array[top];
    }

    //Verifica si la pila esta vacia
    public boolean isEmpty() {
        return top == -1;
    }

    //Verifica si la pila esta llena
    public boolean isFull() {
        return top == capacity - 1;
    }

    //Retorna la cantidad de elementos en la pila
    public int getSize() {
        return top + 1;
    }

    //Limpia todos los elementos de la pila
    @SuppressWarnings("unchecked")
    public void clear() {
        array = (T[]) new Object[capacity];
        top = -1;
    }

    //Retorna la capacidad maxima de la pila
    public int getCapacity() {
        return capacity;
    }

    //Busca un elemento en la pila
    public boolean search(T data) {
        if (isEmpty()) {
            return false;
        }
        for (int i = 0; i <= top; i++) {
            if (array[i].equals(data)) {
                return true;
            }
        }
        return false;
    }
    
    //Invierte el orden de los elementos en la pila
    public void reverse() {
        if (isEmpty() || getSize() == 1) {
            return; // No hay nada que invertir
        }
        
        // Invertir elementos in-place
        int left = 0;
        int right = top;
        while (left < right) {
            // Intercambiar elementos
            T temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }

    //Imprime la pila (del fondo al tope)
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i <= top; i++) {
            sb.append(array[i]);
            if (i < top) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
