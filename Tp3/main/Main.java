package Tp3.main;

import java.util.Scanner;
import Tp3.utils.*;

/**
 * Menú principal dentro del paquete Tp3.main que permite ejecutar
 * cada ejercicio/utility implementado bajo Tp3.utils.
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion;
		do {
			System.out.println("\n--- TP3: Menú de utilidades (Pila/Cola) ---");
			System.out.println("1. Ej1 - Pila (prueba)");
			System.out.println("2. Ej2 - Cola (prueba)");
			System.out.println("3. Ej3 - Invertir cadena");
			System.out.println("4. Ej4 - Simulación de turnos");
			System.out.println("5. Ej5 - Palíndromo");
			System.out.println("6. Ej6 - Deshacer/Rehacer");
			System.out.println("7. Ej7 - Simulación de impresora");
			System.out.println("8. Ej8 - Cola circular (sobrescribir)");
			System.out.println("0. Salir");
			System.out.print("Elija una opción: ");
			try {
				opcion = Integer.parseInt(sc.nextLine().trim());
			} catch (Exception e) {
				opcion = -1;
			}
			switch (opcion) {
				case 1: Ej1_Pila.ejecutar(); break;
				case 2: Ej2_Cola.ejecutar(); break;
				case 3: Ej3_InvertirCadena.ejecutar(sc); break;
				case 4: Ej4_SimulacionTurnos.ejecutar(); break;
				case 5: Ej5_Palindromo.ejecutar(sc); break;
				case 6: Ej6_DeshacerRehacer.ejecutar(); break;
				case 7: Ej7_Impresora.ejecutar(); break;
				case 8: Ej8_ColaCircular.ejecutar(); break;
				case 0: System.out.println("Saliendo..."); break;
				default: System.out.println("Opción inválida.");
			}
		} while (opcion != 0);
		sc.close();
	}
}
