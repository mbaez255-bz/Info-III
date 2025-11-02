package Tp7.src.utils.integrador;

/**
 * Clase auxiliar que representa un paciente con un nombre y una prioridad.
 * priority: 1=alta, 2=media, 3=baja (valores menores = mayor urgencia).
 */
public class Patient {
    public String name; // nombre del paciente
    public int priority; // prioridad: menor valor = más urgente

    public Patient(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public String toString() { return name + "(p=" + priority + ")"; }
}
