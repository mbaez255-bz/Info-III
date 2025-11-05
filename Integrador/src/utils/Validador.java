package utils;

import java.time.LocalDateTime;

/**
 * Utilidades para validación de datos
 */

public class Validador {

    /**
     * Valida que una fecha no sea pasada
     */
    public static boolean esFechaValida(LocalDateTime fecha) {
        if (fecha == null) return false;
        return !fecha.isBefore(LocalDateTime.now());
    }

    /**
     * Valida que una duración sea positiva
     */
    public static boolean esDuracionValida(int duracionMin) {
        return duracionMin > 0;
    }

    /**
     * Valida formato de DNI (8 dígitos)
     */
    public static boolean esDniValido(String dni) {
        if (dni == null || dni.isEmpty()) return false;
        return dni.matches("\\d{7,8}");
    }

    /**
     * Valida formato de matrícula médica
     */
    public static boolean esMatriculaValida(String matricula) {
        if (matricula == null || matricula.isEmpty()) return false;
        return matricula.matches("M\\d{3,}");
    }
}
