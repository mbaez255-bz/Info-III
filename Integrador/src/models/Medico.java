package models;

public class Medico {
    private String matricula;
    private String nombre;
    private String especialidad;

    public Medico(String matricula, String nombre, String especialidad) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return matricula + " - " + nombre + " (" + especialidad + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Medico medico = (Medico) obj;
        return matricula.equals(medico.matricula);
    }

    @Override
    public int hashCode() {
        return matricula.hashCode();
    }
}
