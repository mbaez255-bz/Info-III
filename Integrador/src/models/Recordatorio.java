package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Modelo de Recordatorio
 * Representa un recordatorio o llamada programada
 */

public class Recordatorio implements Comparable<Recordatorio> {
    private String id;
    private LocalDateTime fecha;
    private String dniPaciente;
    private String mensaje;

    public Recordatorio(String id, LocalDateTime fecha, String dniPaciente, String mensaje) {
        this.id = id;
        this.fecha = fecha;
        this.dniPaciente = dniPaciente;
        this.mensaje = mensaje;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getDniPaciente() {
        return dniPaciente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @Override
    public int compareTo(Recordatorio otro) {
        // Orden ascendente por fecha (para min-heap)
        return this.fecha.compareTo(otro.fecha);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("%s | %s | Paciente: %s | %s",
                id, fecha.format(formatter), dniPaciente, mensaje);
    }
}
