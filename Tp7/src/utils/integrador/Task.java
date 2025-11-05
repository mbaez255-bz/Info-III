package Tp7.src.utils.integrador;

public class Task {
    public String description;
    public int priority; // menor valor = más urgente

    public Task(String description, int priority) {
        this.description = description;
        this.priority = priority;
    }

    @Override
    public String toString() { return description + " (prioridad=" + priority + ")"; }
}
