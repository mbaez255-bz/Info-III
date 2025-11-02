package utils.integrador;

public class Task {
    public String description;
    public int priority; // lower = more urgent

    public Task(String description, int priority) {
        this.description = description;
        this.priority = priority;
    }

    @Override
    public String toString() { return description + "(p=" + priority + ")"; }
}
