import models.Medico;
import models.Paciente;
import models.Recordatorio;
import models.SolicitudCirugia;
import models.Turno;
import services.AgendaMedico;
import services.AuditoriaHistorial;
import services.BuscadorHueco;
import services.CargadorDatos;
import services.ConsolidadorAgendas;
import services.GeneradorReportes;
import services.IndicePacientes;
import services.PlanificadorQuirofano;
import services.PlannerRecordatorios;
import services.SalaEspera;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * PRÁCTICO INTEGRADOR - Sistema de Gestión de Turnos Médicos
 * Main con menú interactivo para todos los ejercicios
 */

public class Main {
    
    private static CargadorDatos cargador;
    private static AgendaMedico agendaMedico;
    private static IndicePacientes indicePacientes;
    private static SalaEspera salaEspera;
    private static PlannerRecordatorios plannerRecordatorios;
    private static AuditoriaHistorial auditoria;
    private static GeneradorReportes generadorReportes;
    private static ConsolidadorAgendas consolidador;
    private static BuscadorHueco buscadorHueco;
    private static PlanificadorQuirofano planificadorQuirofano;
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        mostrarBanner();
        inicializarSistema();
        menuPrincipal();
    }

    private static void mostrarBanner() {
        System.out.println("\n");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("        SISTEMA DE GESTIÓN DE TURNOS MÉDICOS");
        System.out.println("         PRÁCTICO INTEGRADOR - INFORMÁTICA III");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("\n");
    }

    private static void inicializarSistema() {

        // Cargar datos
        cargador = new CargadorDatos();
        cargador.cargarTodo("Integrador/data/pacientes.csv", "Integrador/data/medicos.csv", "Integrador/data/turnos.csv");
        
        // Inicializar servicios
        agendaMedico = new AgendaMedico(cargador.getMedicos());
        agendaMedico.cargarTurnos(cargador.getTurnos());
        
        indicePacientes = new IndicePacientes();
        indicePacientes.cargarPacientes(cargador.getPacientes());
        
        salaEspera = new SalaEspera(5);
        
        plannerRecordatorios = new PlannerRecordatorios();
        
        auditoria = new AuditoriaHistorial(agendaMedico);
        
        generadorReportes = new GeneradorReportes();
        
        consolidador = new ConsolidadorAgendas();
        
        buscadorHueco = new BuscadorHueco(agendaMedico);
        
        // Quirófanos: Q1, Q2, Q3
        planificadorQuirofano = new PlanificadorQuirofano(
            Arrays.asList("Q1", "Q2", "Q3"), 
            3, 
            LocalDateTime.now()
        );

    }

    private static void menuPrincipal() {
        while (true) {
            System.out.println("\n╔═════════════════════════════════════════════════════════╗");
            System.out.println("║              MENÚ PRINCIPAL                             ║");
            System.out.println("╠═════════════════════════════════════════════════════════╣");
            System.out.println("║  1) Ver agenda de un médico                             ║");
            System.out.println("║  2) Buscar próximo turno disponible                     ║");
            System.out.println("║  3) Simular sala de espera                              ║");
            System.out.println("║  4) Programar recordatorios                             ║");
            System.out.println("║  5) Consultar índice de pacientes (Hash)                ║");
            System.out.println("║  6) Consolidador de agendas                             ║");
            System.out.println("║  7) Reportes de ordenamiento                            ║");
            System.out.println("║  8) Auditoría Undo/Redo                                 ║");
            System.out.println("║  9) Planificador de quirófano                           ║");
            System.out.println("║  0) Salir                                               ║");
            System.out.println("╚═════════════════════════════════════════════════════════╝");
            System.out.print("\nSeleccione una opción: ");
            
            int opcion = leerEntero();
            
            switch (opcion) {
                case 1:
                    ejercicio1VerAgenda();
                    break;
                case 2:
                    ejercicio3BuscarHueco();
                    break;
                case 3:
                    ejercicio4SalaEspera();
                    break;
                case 4:
                    ejercicio5Recordatorios();
                    break;
                case 5:
                    ejercicio6IndicePacientes();
                    break;
                case 6:
                    ejercicio7Consolidador();
                    break;
                case 7:
                    ejercicio8Reportes();
                    break;
                case 8:
                    ejercicio9Auditoria();
                    break;
                case 9:
                    ejercicio10Quirofano();
                    break;
                case 0:
                    System.out.println("\n¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nOpción inválida");
            }
        }
    }

    // ========== EJERCICIO 1 (+ 2): VER AGENDA ==========
    private static void ejercicio1VerAgenda() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("EJERCICIO 2: AGENDA POR MÉDICO CON INSERCIÓN/BORRADO");
        System.out.println("─".repeat(60));
        
        mostrarMedicos();
        System.out.print("\nIngrese matrícula del médico: ");
        String matricula = scanner.nextLine().trim();
        
        if (!cargador.getMedicos().containsKey(matricula)) {
            System.out.println("Médico no encontrado");
            return;
        }
        
        agendaMedico.mostrarAgenda(matricula);
        
        pausar();
    }

    // ========== EJERCICIO 3: BÚSQUEDA DE HUECO LIBRE ==========
    private static void ejercicio3BuscarHueco() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("EJERCICIO 3: BÚSQUEDA DE HUECO LIBRE DE X MINUTOS");
        System.out.println("─".repeat(60));
        
        mostrarMedicos();
        System.out.print("\nIngrese matrícula del médico: ");
        String matricula = scanner.nextLine().trim();
        
        if (!cargador.getMedicos().containsKey(matricula)) {
            System.out.println("Médico no encontrado");
            return;
        }
        
        System.out.print("Duración requerida (minutos): ");
        int duracion = leerEntero();
        
        LocalDateTime t0 = LocalDateTime.now();
        buscadorHueco.buscarYMostrar(matricula, t0, duracion);
        
        pausar();
    }

    // ========== EJERCICIO 4: SALA DE ESPERA ==========
    private static void ejercicio4SalaEspera() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("EJERCICIO 4: SALA DE ESPERA CON COLA CIRCULAR Y \"OVERFLOW CONTROL\"");
        System.out.println("─".repeat(60));
        
        salaEspera.mostrarEstado();
        
        System.out.println("\nSimulación:");
        System.out.println("1) Llegar pacientes");
        System.out.println("2) Atender paciente");
        System.out.println("3) Ver estado");
        System.out.println("0) Volver");
        
        while (true) {
            System.out.print("\nOpción: ");
            int opcion = leerEntero();
            
            if (opcion == 0) break;
            
            switch (opcion) {
                case 1:
                    System.out.print("DNI del paciente: ");
                    String dni = scanner.nextLine().trim();
                    salaEspera.llegaPaciente(dni);
                    break;
                case 2:
                    salaEspera.atiendePaciente();
                    break;
                case 3:
                    salaEspera.mostrarEstado();
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    // ========== EJERCICIO 5: RECORDATORIOS ==========
    private static void ejercicio5Recordatorios() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("EJERCICIO 5: RECORDATORIOS Y LLAMADOS (Planner con PriorityQueue)");
        System.out.println("─".repeat(60));
        
        plannerRecordatorios.mostrarEstado();
        
        System.out.println("\nOpciones:");
        System.out.println("1) Programar recordatorio");
        System.out.println("2) Procesar próximo");
        System.out.println("3) Reprogramar recordatorio");
        System.out.println("4) Ver estado");
        System.out.println("0) Volver");
        
        while (true) {
            System.out.print("\nOpción: ");
            int opcion = leerEntero();
            
            if (opcion == 0) break;
            
            switch (opcion) {
                case 1:
                    System.out.print("DNI del paciente: ");
                    String dni = scanner.nextLine().trim();
                    System.out.print("Mensaje: ");
                    String mensaje = scanner.nextLine().trim();
                    System.out.print("Días adelante: ");
                    int dias = leerEntero();
                    
                    LocalDateTime fecha = LocalDateTime.now().plusDays(dias);
                    Recordatorio rec = new Recordatorio("R-" + System.currentTimeMillis(), 
                                                        fecha, dni, mensaje);
                    plannerRecordatorios.programar(rec);
                    break;
                case 2:
                    plannerRecordatorios.procesarProximo();
                    break;
                case 3:
                    System.out.print("ID del recordatorio a reprogramar: ");
                    String idRec = scanner.nextLine().trim();
                    System.out.print("Nueva fecha (días adelante): ");
                    int nuevosDias = leerEntero();
                    
                    LocalDateTime nuevaFecha = LocalDateTime.now().plusDays(nuevosDias);
                    plannerRecordatorios.reprogramar(idRec, nuevaFecha);
                    break;
                case 4:
                    plannerRecordatorios.mostrarEstado();
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    // ========== EJERCICIO 6: ÍNDICE DE PACIENTES ==========
    private static void ejercicio6IndicePacientes() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("EJERCICIO 6: ÍNDICE RÁPIDO DE PACIENTES (Hash con Chaining)");
        System.out.println("─".repeat(60));
        
        indicePacientes.mostrarEstadisticas();
        
        System.out.print("\nIngrese DNI a buscar: ");
        String dni = scanner.nextLine().trim();
        
        indicePacientes.buscarYMostrar(dni);
        
        pausar();
    }

    // ========== EJERCICIO 7: CONSOLIDADOR DE AGENDAS ==========
    private static void ejercicio7Consolidador() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("EJERCICIO 7: CONSOLIDACIÓN DE AGENDAS (Merge y Deduplicación)");
        System.out.println("─".repeat(60));
        
        // Simular dos fuentes de agendas
        List<Turno> agendaLocal = agendaMedico.obtenerAgenda("M001");
        List<Turno> agendaNube = agendaMedico.obtenerAgenda("M002");
        
        consolidador.consolidarYMostrar(agendaLocal, agendaNube, "agendaLocal", "agendaNube");
        
        pausar();
    }

    // ========== EJERCICIO 8: REPORTES DE ORDENAMIENTO ==========
    private static void ejercicio8Reportes() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("EJERCICIO 8: REPORTES OPERATIVOS CON MÚLTIPLES ORDENAMIENTOS");
        System.out.println("─".repeat(60));
        
        System.out.println("\n1) Reporte por hora (Insertion Sort - estable)");
        System.out.println("2) Reporte por duración (Shellsort)");
        System.out.println("3) Reporte por apellido paciente (Quicksort - Lomuto)");
        System.out.println("4) Comparación de algoritmos");
        System.out.println("0) Volver");
        
        System.out.print("\nOpción: ");
        int opcion = leerEntero();
        
        List<Turno> turnos = cargador.getTurnos();
        Map<String, String> dniANombre = new HashMap<>();
        for (Paciente p : cargador.getPacientes().values()) {
            dniANombre.put(p.getDni(), p.getNombre());
        }
        
        switch (opcion) {
            case 1:
                generadorReportes.reportePorHora(turnos);
                break;
            case 2:
                generadorReportes.reportePorDuracion(turnos);
                break;
            case 3:
                generadorReportes.reportePorApellido(turnos, dniANombre);
                break;
            case 4:
                generadorReportes.compararAlgoritmos(turnos);
                break;
        }
        
        pausar();
    }

    // ========== EJERCICIO 9: AUDITORÍA UNDO/REDO ==========
    private static void ejercicio9Auditoria() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("EJERCICIO 9: AUDITORÍA Y UNDO/REDO DE CAMBIOS EN AGENDA");
        System.out.println("─".repeat(60));
        
        auditoria.mostrarEstado();
        
        System.out.println("\nOpciones:");
        System.out.println("1) Agendar turno (registrar)");
        System.out.println("2) Cancelar turno (registrar)");
        System.out.println("3) Undo (deshacer última acción)");
        System.out.println("4) Redo (rehacer)");
        System.out.println("5) Ver estado");
        System.out.println("0) Volver");
        
        while (true) {
            System.out.print("\nOpción: ");
            int opcion = leerEntero();
            
            if (opcion == 0) break;
            
            switch (opcion) {
                case 1:
                    Turno nuevoTurno = crearTurnoManual();
                    if (nuevoTurno != null) {
                        agendaMedico.agendar(nuevoTurno);
                        auditoria.registrarAgendar(nuevoTurno);
                    }
                    break;
                case 2:
                    System.out.print("ID del turno a cancelar: ");
                    String id = scanner.nextLine().trim();
                    System.out.print("Matrícula del médico: ");
                    String mat = scanner.nextLine().trim();
                    
                    // Buscar el turno para registrar
                    List<Turno> agenda = agendaMedico.obtenerAgenda(mat);
                    Turno turnoACancelar = null;
                    for (Turno t : agenda) {
                        if (t.getId().equals(id)) {
                            turnoACancelar = t;
                            break;
                        }
                    }
                    
                    if (turnoACancelar != null) {
                        agendaMedico.cancelar(id, mat);
                        auditoria.registrarCancelar(turnoACancelar);
                    } else {
                        System.out.println("Turno no encontrado");
                    }
                    break;
                case 3:
                    auditoria.undo();
                    break;
                case 4:
                    auditoria.redo();
                    break;
                case 5:
                    auditoria.mostrarEstado();
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    // ========== EJERCICIO 10: PLANIFICADOR DE QUIRÓFANO ==========
    private static void ejercicio10Quirofano() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("EJERCICIO 10: PLANIFICADOR DE QUIRÓFANO (MinHeap + Top-K)");
        System.out.println("─".repeat(60));
        
        Map<String, String> matriculaANombre = new HashMap<>();
        for (Medico m : cargador.getMedicos().values()) {
            matriculaANombre.put(m.getMatricula(), m.getNombre());
        }
        
        planificadorQuirofano.mostrarEstado(matriculaANombre);
        
        System.out.println("\nSimular solicitudes de cirugía:");
        System.out.println("1) Procesar solicitud");
        System.out.println("2) Ver estado");
        System.out.println("0) Volver");
        
        while (true) {
            System.out.print("\nOpción: ");
            int opcion = leerEntero();
            
            if (opcion == 0) break;
            
            switch (opcion) {
                case 1:
                    mostrarMedicos();
                    System.out.print("Matrícula del médico: ");
                    String mat = scanner.nextLine().trim();
                    
                    if (!cargador.getMedicos().containsKey(mat)) {
                        System.out.println("Médico no encontrado");
                        break;
                    }
                    
                    System.out.print("Duración (minutos): ");
                    int dur = leerEntero();
                    System.out.print("Días adelante para deadline: ");
                    int dias = leerEntero();
                    
                    Medico medico = cargador.getMedicos().get(mat);
                    LocalDateTime deadline = LocalDateTime.now().plusDays(dias);
                    
                    SolicitudCirugia solicitud = new SolicitudCirugia(
                        "CIR-" + System.currentTimeMillis(),
                        mat,
                        medico.getNombre(),
                        dur,
                        deadline
                    );
                    
                    planificadorQuirofano.procesarSolicitud(solicitud);
                    break;
                case 2:
                    planificadorQuirofano.mostrarEstado(matriculaANombre);
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    // ========== UTILIDADES ==========
    
    private static void mostrarMedicos() {
        System.out.println("\nMédicos disponibles:");
        for (Medico m : cargador.getMedicos().values()) {
            System.out.println("  " + m);
        }
    }

    private static Turno crearTurnoManual() {
        try {
            System.out.print("ID del turno: ");
            String id = scanner.nextLine().trim();
            System.out.print("DNI del paciente: ");
            String dni = scanner.nextLine().trim();
            System.out.print("Matrícula del médico: ");
            String mat = scanner.nextLine().trim();
            System.out.print("Duración (minutos): ");
            int dur = leerEntero();
            System.out.print("Días adelante: ");
            int dias = leerEntero();
            System.out.print("Motivo: ");
            String motivo = scanner.nextLine().trim();
            
            LocalDateTime fecha = LocalDateTime.now().plusDays(dias);
            
            return new Turno(id, dni, mat, fecha, dur, motivo);
        } catch (Exception e) {
            System.out.println("Error al crear turno: " + e.getMessage());
            return null;
        }
    }

    private static int leerEntero() {
        while (true) {
            try {
                int num = Integer.parseInt(scanner.nextLine().trim());
                return num;
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }

    private static void pausar() {
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}
