package Ejercicio2;

import java.util.Random;
import java.util.concurrent.*;

public class EjercicioB {

    // Constantes
    private static final int MAX_PROBADOR_TIEMPO = 2000;
    private static final int MAX_EMBALADOR_TIEMPO = 2000;
    private static final int NUM_BOMBILLAS = 50;
    private static final int CAPACIDAD_PROBADOR = 3;
    private static final int CAPACIDAD_EMBALADOR = 2;
    private static final Random random = new Random();

    public static void main(String[] args) {

        BlockingQueue<String> cola = new ArrayBlockingQueue<>(CAPACIDAD_PROBADOR);


        ExecutorService ejecutor = Executors.newFixedThreadPool(2);


        Runnable probador = () -> {
            int procesadas = 0;
            try {
                for (int i = 1; i <= NUM_BOMBILLAS; i++) {
                    if (procesadas == CAPACIDAD_PROBADOR) {
                        System.out.println("🛑 El probador se detiene a descansar...");
                        Thread.sleep(3000);
                        procesadas = 0;
                    }
                    String bombilla = "Bombilla-" + i;
                    System.out.println("🔍 Probando " + bombilla);
                    Thread.sleep(random.nextInt(MAX_PROBADOR_TIEMPO));
                    System.out.println("✅ " + bombilla + " está lista para empaquetar.");
                    cola.put(bombilla);
                    procesadas++;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };


        Runnable embalador = () -> {
            int procesadas = 0;
            try {
                while (true) {
                    if (procesadas == CAPACIDAD_EMBALADOR) {
                        System.out.println("🛑 El embalador se detiene a descansar...");
                        Thread.sleep(3000);
                        procesadas = 0;
                    }
                    String bombilla = cola.take();
                    System.out.println("📦 Empaquetando " + bombilla);
                    Thread.sleep(random.nextInt(MAX_EMBALADOR_TIEMPO));
                    System.out.println("📦 " + bombilla + " empaquetada.");
                    procesadas++;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };


        ejecutor.submit(probador);
        ejecutor.submit(embalador);


        ejecutor.shutdown();
        try {
            boolean terminado = ejecutor.awaitTermination(
                    NUM_BOMBILLAS * (MAX_PROBADOR_TIEMPO + MAX_EMBALADOR_TIEMPO),
                    TimeUnit.MILLISECONDS
            );
            if (terminado) {
                System.out.println("🚦 Línea de montaje detenida. Todo está empaquetado.");
            } else {
                System.out.println("🚦 Tiempo límite excedido. Línea de montaje interrumpida.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("🚨 Error: El proceso fue interrumpido.");
        }
    }
}
