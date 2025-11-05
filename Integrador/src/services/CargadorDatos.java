package services;

import models.Medico;
import models.Paciente;
import models.Turno;
import utils.Validador;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Servicio para cargar datos iniciales desde CSV
 * Ejercicio 1: Carga inicial y validaciones de dominio
 * Complejidad: O(n log n) por inserción en estructuras
 */

public class CargadorDatos {
    
    private Map<String, Paciente> pacientes;
    private Map<String, Medico> medicos;
    private List<Turno> turnos;
    private Set<String> idsUnicos;

    public CargadorDatos() {
        this.pacientes = new HashMap<>();
        this.medicos = new HashMap<>();
        this.turnos = new ArrayList<>();
        this.idsUnicos = new HashSet<>();
    }

    /**
     * Carga completa: pacientes, médicos y turnos
     */
    public void cargarTodo(String rutaPacientes, String rutaMedicos, String rutaTurnos) {

        cargarPacientes(rutaPacientes);
        cargarMedicos(rutaMedicos);
        cargarTurnos(rutaTurnos);
        
    }

    /**
     * Carga pacientes desde CSV
     */
    public void cargarPacientes(String ruta) {
        //System.out.println("> Leyendo pacientes.csv ...");
        
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea = br.readLine(); // Saltar header
            //int contador = 0;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 2) {
                    String dni = partes[0].trim();
                    String nombre = partes[1].trim();
                    
                    pacientes.put(dni, new Paciente(dni, nombre));
                    //contador++;
                }
            }

            //System.out.println("  [OK] (" + contador + " registros)");
        } catch (IOException e) {
            System.err.println(" [ERROR] No se pudo leer pacientes.csv: " + e.getMessage());
        }
    }

    /**
     * Carga médicos desde CSV
     */
    public void cargarMedicos(String ruta) {
        //System.out.println("> Leyendo medicos.csv ...");
        
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea = br.readLine(); // Saltar header
            //int contador = 0;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 3) {
                    String matricula = partes[0].trim();
                    String nombre = partes[1].trim();
                    String especialidad = partes[2].trim();
                    
                    medicos.put(matricula, new Medico(matricula, nombre, especialidad));
                    //contador++;
                }
            }

            //System.out.println("  [OK] (" + contador + " registros)");
        } catch (IOException e) {
            System.err.println(" [ERROR] No se pudo leer medicos.csv: " + e.getMessage());
        }
    }

    /**
     * Carga turnos desde CSV con validaciones
     */
    public void cargarTurnos(String ruta) {
        //System.out.println("> Leyendo turnos.csv ...");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        int contador = 0;
        int rechazados = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea = br.readLine(); // Saltar header

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 6) {
                    String id = partes[0].trim();
                    String dniPaciente = partes[1].trim();
                    String matriculaMedico = partes[2].trim();
                    String fechaHoraStr = partes[3].trim();
                    int duracionMin = Integer.parseInt(partes[4].trim());
                    String motivo = partes[5].trim();

                    // Validar
                    boolean valido = true;
                    StringBuilder razon = new StringBuilder();

                    // 1. Paciente/médico existen
                    if (!pacientes.containsKey(dniPaciente)) {
                        valido = false;
                        razon.append("Paciente no existe. ");
                    }
                    if (!medicos.containsKey(matriculaMedico)) {
                        valido = false;
                        razon.append("Médico no existe. ");
                    }

                    // 2. Fecha no pasada
                    LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);
                    if (!Validador.esFechaValida(fechaHora)) {
                        valido = false;
                        razon.append("Fecha pasada. ");
                    }

                    // 3. Duración > 0
                    if (!Validador.esDuracionValida(duracionMin)) {
                        valido = false;
                        razon.append("Duración inválida. ");
                    }

                    // 4. ID único
                    if (idsUnicos.contains(id)) {
                        valido = false;
                        razon.append("ID duplicado (ID = " + id + "). ");
                    }

                    if (valido) {
                        Turno turno = new Turno(id, dniPaciente, matriculaMedico, 
                                               fechaHora, duracionMin, motivo);
                        turnos.add(turno);
                        idsUnicos.add(id);
                        contador++;
                    } else {
                        System.out.println("  - " + contador + " turno rechazado (ID = " + id + "): " + razon);
                        rechazados++;
                    }
                }
            }

            //System.out.println("  [OK] (" + contador + " registros)");
            if (rechazados > 0) {
                System.out.println(" [ADVERTENCIA] " + rechazados + " turno(s) rechazado(s)");
            }
        } catch (IOException e) {
            System.err.println(" [ERROR] No se pudo leer turnos.csv: " + e.getMessage());
        }
    }

    // Getters
    public Map<String, Paciente> getPacientes() {
        return pacientes;
    }

    public Map<String, Medico> getMedicos() {
        return medicos;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }
}
