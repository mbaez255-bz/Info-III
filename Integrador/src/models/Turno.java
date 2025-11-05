package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Modelo de Turno
 * Representa un turno médico con paciente, médico, fecha/hora, duración y motivo
 */

public class Turno implements Comparable<Turno> {
    private String id;
    private String dniPaciente;
    private String matriculaMedico;
    private LocalDateTime fechaHora;
    private int duracionMin;
    private String motivo;

    public Turno(String id, String dniPaciente, String matriculaMedico, 
                 LocalDateTime fechaHora, int duracionMin, String motivo) {
        this.id = id;
        this.dniPaciente = dniPaciente;
        this.matriculaMedico = matriculaMedico;
        this.fechaHora = fechaHora;
        this.duracionMin = duracionMin;
        this.motivo = motivo;
    }

    public String getId() {
        return id;
    }

    public String getDniPaciente() {
        return dniPaciente;
    }

    public String getMatriculaMedico() {
        return matriculaMedico;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public int getDuracionMin() {
        return duracionMin;
    }

    public String getMotivo() {
        return motivo;
    }

    /**
     * Calcula la fecha y hora de fin del turno
     * plusMinutes: Suma minutos a una fecha/hora dada
     */
    public LocalDateTime getFechaHoraFin() {
        return fechaHora.plusMinutes(duracionMin);
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Verifica si este turno se solapa con otro
     * isBefore: Devuelve true si esta fecha es anterior a la otra fecha
     */
    public boolean seSolapaCon(Turno otro) {
        return this.fechaHora.isBefore(otro.getFechaHoraFin()) &&
               otro.fechaHora.isBefore(this.getFechaHoraFin());
    }

    /**
     * Compara turnos por fecha y hora
     * Retorna negativo si this es antes que otro,
     * cero si son iguales, positivo si this es después que otro
     */
    @Override
    public int compareTo(Turno otro) {
        return this.fechaHora.compareTo(otro.fechaHora);
    }

    /**
     * Representación en String del turno
     * formatter: Formato de fecha y hora "dd/MM/yyyy HH:mm"
     * format: Formatea la cadena con los atributos del turno
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("%s | Paciente: %s | Médico: %s | %s hs | %d min | %s",
                id, dniPaciente, matriculaMedico, 
                fechaHora.format(formatter), duracionMin, motivo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // misma referencia
        if (obj == null || getClass() != obj.getClass()) return false; // diferente clase
        Turno turno = (Turno) obj; 
        return id.equals(turno.id); // igualdad por ID
    }

    @Override
    public int hashCode() {
        return id.hashCode(); // hashCode calculado a partir del ID
    }
}
