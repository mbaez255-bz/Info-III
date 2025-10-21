package Tp4;

public class LinkedList<T> {
    private ListNode<T> head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void addBeginning(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addEnd(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        if (head == null) {
            head = newNode;
        } else {
            ListNode<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public void addPosition(T data, int position) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Posicion invalida");
        }
        if (position == 0) {
            addBeginning(data);
        } else {
            ListNode<T> newNode = new ListNode<>(data);
            ListNode<T> current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }

    public boolean deleteBeginning() {
        if (head == null) {
            return false; 
        }
        head = head.next;
        size--;
        return true; 
    }

    public boolean deleteEnd() {
        if (head == null) {
            return false; 
        }
        if (head.next == null) {
            head = null;
        } else {
            ListNode<T> current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
        size--;
        return true; 
    }

    public boolean deletePosition(int position) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Posicion invalida");
        }
        if (position < 0 || position >= size) {
            return false; 
        }
        if (position == 0) {
            return deleteBeginning();
        }
        ListNode<T> current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
        return true; 
    }

    public boolean deleteValue(T data) {
        if (head == null) {
            return false; 
        }
        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return true; 
        }
        ListNode<T> current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }
        if (current.next == null) {
            return false; 
        }
        current.next = current.next.next;
        size--;
        return true; 
    }

    public boolean deleteDuplicates() {
        if (head == null) {
            return false; 
        }
        ListNode<T> current = head;
        while (current != null) {
            ListNode<T> runner = current;
            while (runner.next != null) {
                if (runner.next.data.equals(current.data)) {
                    runner.next = runner.next.next;
                    size--;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
        return true; 
    }

    public boolean search(T data) {
        ListNode<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true; 
            }
            current = current.next;
        }
        return false; 
    }

    public void invest() {
        ListNode<T> prev = null;
        ListNode<T> current = head;
        ListNode<T> next = null;
        while (current != null) {
            next = current.next;  
            current.next = prev;  
            prev = current;       
            current = next;      
        }
        head = prev; 
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) { 
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb.toString();
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
