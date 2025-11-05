package Tp7.src.main;

import java.util.Scanner;

import Tp7.src.utils.MaxHeap;
import Tp7.src.utils.MinHeap;
import Tp7.src.utils.integrador.MinHeapGeneric;
import Tp7.src.utils.integrador.Patient;
import Tp7.src.utils.integrador.Task;
import Tp7.src.utils.integrador.TaskAgenda;

/**
 * Programa demo para TP7: implementaciones y ejercicios con montículos (heaps).
 * Contiene varios métodos estáticos que muestran ejemplos didácticos
 * (construcción heapify, inserciones, extracciones, heapsort, cola de prioridad).
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("\n--- Menú TP7: Montículo / Heaps ---");
			System.out.println("1. MinHeap demo (insertar y extraer orden)");
			System.out.println("2. add (mostrar swaps) y printTree");
			System.out.println("3. poll (mostrar antes/después)");
			System.out.println("4. Heapify desde arreglo (mostrar pasos)");
			System.out.println("5. Heapsort demo");
			System.out.println("6. MaxHeap demo");
			System.out.println("7. Cola de prioridad de pacientes (MinHeapGeneric)");
			System.out.println("8. Seguimiento estado interno (printArray demo)");
			System.out.println("9. Agenda de tareas (MinHeapGeneric)");
			System.out.println("0. Salir");
			System.out.print("Elegí una opción: ");
			int opt = Integer.parseInt(sc.nextLine());
			if (opt==0) break;
			try {
				switch (opt) {
					case 1: demoMinHeapBasic(); break;
					case 2: demoAddAndPrintTree(); break;
					case 3: demoPollShows(); break;
					case 4: demoHeapify(); break;
					case 5: demoHeapsort(); break;
					case 6: demoMaxHeap(); break;
					case 7: demoPatients(); break;
					case 8: demoPrintArraySequence(); break;
					case 9: demoTaskAgenda(); break;
					default: System.out.println("Opción inválida");
				}
			} catch (Exception e) {
				System.out.println("Error en demo: " + e.getMessage());
				e.printStackTrace();
			}
		}
		sc.close();
		System.out.println("Adiós");
	}

	/**
	 * Demo 1: inserta una secuencia de enteros en un MinHeap y luego extrae
	 * todos los elementos mostrando el orden ascendente (mínimo primero).
	 */
	static void demoMinHeapBasic() {
		System.out.println("Demo MinHeap básico:");
		MinHeap h = new MinHeap();
		int[] vals = {20,5,15,3,11};
		for (int v: vals) h.add(v);
		System.out.println("/nExtraer en orden:");
		while (!h.isEmpty()) System.out.println(h.poll());
	}

	/**
	 * Demo 2: inserciones visuales; la implementación de add imprime
	 * los intercambios (percolateUp) y printTree muestra la estructura.
	 */
	static void demoAddAndPrintTree() {
		System.out.println("Insertar mostrando swaps y estructura:");
		MinHeap h = new MinHeap();
		int[] vals = {20,5,15,3,11};
		for (int v: vals) h.add(v);
	}

	/**
	 * Demo 3: muestra el estado del arreglo antes de extraer y luego
	 * extrae elementos sucesivamente mostrando cada extracción.
	 */
	static void demoPollShows() {
		MinHeap h = new MinHeap();
		int[] vals = {20,5,15,3,11};
		for (int v: vals) h.add(v);
		// Mostrar únicamente la secuencia de extracción y los prints internos de poll()
		System.out.println("Extrayendo elementos:");
		while (!h.isEmpty()) {
			int v = h.poll();
			System.out.println("Extraído: " + v);
		}
	}

	/**
	 * Demo 4: construye un heap usando heapify desde un arreglo dado
	 * e imprime los pasos intermedios y el heap final.
	 */
	static void demoHeapify() {
		int[] datos = {20,5,15,3,11};
		System.out.println("Construcción heapify desde: ");
		MinHeap h = new MinHeap(datos);
		System.out.println("Heap final:"); h.printArray();
	}

	/**
	 * Demo 5: muestra heapsort usando el MinHeap (extrae sucesivamente
	 * los mínimos para ordenar el arreglo de menor a mayor).
	 */
	static void demoHeapsort() {
		int[] arr = {9,4,7,1,6,2};
		System.out.println("Antes heapsort: " + java.util.Arrays.toString(arr));
		MinHeap.heapsort(arr);
		System.out.println("Después heapsort: " + java.util.Arrays.toString(arr));
	}

	/**
	 * Demo 6: demuestra MaxHeap (extrae valores de mayor a menor).
	 */
	static void demoMaxHeap() {
		int[] data = {10,3,15,8,6,12};
		MaxHeap mh = new MaxHeap();
		for (int v: data) mh.add(v);
		System.out.println("Extracción (mayor a menor):");
		while (!mh.isEmpty()) System.out.println(mh.poll());
	}

	/**
	 * Demo 7: ejemplo de MinHeapGeneric con la clase Patient, mostrando
	 * el orden de atención según prioridad.
	 */
	static void demoPatients() {
		MinHeapGeneric<Patient> q = new MinHeapGeneric<>((a,b)->Integer.compare(a.priority,b.priority));
		Patient[] patients = { new Patient("Ana",2), new Patient("Luis",1), new Patient("Marta",3), new Patient("Diego",1), new Patient("Eva",2) };
	System.out.println("Ingreso de pacientes:");
	for (Patient pa: patients) { System.out.println(pa); q.add(pa); }
	System.out.println("Orden de atención:");
		while (!q.isEmpty()) System.out.println(q.poll());
	}

	/**
	 * Demo 8: secuencia de inserciones y extracciones intercaladas mostrando
	 * el array interno en cada paso.
	 */
	static void demoPrintArraySequence() {
		MinHeap h = new MinHeap();
		int[] ops = {10,4,5,2,7};
		for (int v: ops) { h.add(v); }
		h.printArray();
		h.poll(); h.printArray();
		h.poll(); h.printArray();
	}

	/**
	 * Demo 9: ejemplo de uso de Agenda (MinHeapGeneric<Task>): agregar,
	 * consultar próxima tarea, completar y mostrar todas.
	 */
	static void demoTaskAgenda() {
		TaskAgenda ag = new TaskAgenda();
		ag.add(new Task("Pagar facturas", 2));
		ag.add(new Task("Enviar informe", 1));
		ag.add(new Task("Comprar insumos", 3));
	System.out.println("Próxima tarea: " + ag.next());
	System.out.println("Completar: " + ag.complete());
	System.out.println("Todas pendientes:"); ag.showAll();
	}
}

