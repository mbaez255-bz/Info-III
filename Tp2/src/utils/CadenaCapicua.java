package utils;

import java.util.Scanner;
/**
 * Verifica si una cadena es capicúa (palíndromo).
 */
public class CadenaCapicua {

    /**
     * Ejecuta la comprobación usando el Scanner proporcionado.
     */
    public static void ejecutar(Scanner sc) {
        System.out.println("*** CADENA CAPICÚA ***");
        System.out.print("Ingrese una cadena: ");
        String s = sc.nextLine();
        boolean es = esCapicua(s);
        System.out.println(es ? "La cadena es capicúa." : "La cadena NO es capicúa.");
    }

    public static boolean esCapicua(String s) {
        if (s == null) return false;
        String limpio = s.replaceAll("\\s+", "").toLowerCase();
        int i = 0, j = limpio.length() - 1;
        while (i < j) {
            if (limpio.charAt(i) != limpio.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }
}
