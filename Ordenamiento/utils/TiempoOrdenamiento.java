package Ordenamiento.utils;

public class TiempoOrdenamiento {
     public static long medirTiempo(Runnable metodo) {
        long inicio = System.nanoTime();
        metodo.run();
        long fin = System.nanoTime();
        return (fin - inicio) / 1000000; // milisegundos
    }
    
}
