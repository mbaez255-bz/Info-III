package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Modelo de Solicitud de Cirugía
 * Representa una solicitud de cirugía con médico, duración y deadline
 */

public class SolicitudCirugia {
    private String id;
    private String matriculaMedico;
    private String nombreMedico;
    private int duracionMin;
    private LocalDateTime deadline;

    public SolicitudCirugia(String id, String matriculaMedico, String nombreMedico, 
                            int duracionMin, LocalDateTime deadline) {
        this.id = id;
        this.matriculaMedico = matriculaMedico;
        this.nombreMedico = nombreMedico;
        this.duracionMin = duracionMin;
        this.deadline = deadline;
    }

    public String getId() {
        return id;
    }

    public String getMatriculaMedico() {
        return matriculaMedico;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public int getDuracionMin() {
        return duracionMin;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("Cirugía %s | Dr. %s | %d min | Deadline: %s",
                id, nombreMedico, duracionMin, deadline.format(formatter));
    }
}
