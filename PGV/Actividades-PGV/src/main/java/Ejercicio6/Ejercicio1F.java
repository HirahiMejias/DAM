package Ejercicio6;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Ejercicio1F {

    private static final int MAX_PROBADOR_TIEMPO = 2 * 1000;
    private static final int MAX_EMBALADOR_TIEMPO = 2 * 1000;
    private static final int NUM_BOMBILLAS = 10;
    private static final String FIN = "FIN";

    public static void main(String[] args) {
        BlockingQueue<String> cintaTransportadora = new LinkedBlockingQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Random random = new Random();

        Runnable comprobador = () -> {
            try {
                for (int i = 1; i <= NUM_BOMBILLAS; i++) {
                    Thread.sleep(random.nextInt(MAX_PROBADOR_TIEMPO));
                    String bombilla = "Bombilla-" + i;


                    if (random.nextBoolean()) {
                        System.err.println("[!] Defectuosa: " + bombilla);
                        continue;
                    }

                    cintaTransportadora.put(bombilla);
                    System.out.println("[i] Chequeada: " + bombilla + ".");
                }


                cintaTransportadora.put(FIN);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("COMPROBADOR: Proceso interrumpido.");
            }
        };

        Runnable embalador = () -> {
            try {
                while (true) {
                    String bombilla = cintaTransportadora.take();


                    if (bombilla.equals(FIN)) {
                        System.out.println("[!] Linea de montaje finalizada.");
                        break;
                    }

                    Thread.sleep(random.nextInt(MAX_EMBALADOR_TIEMPO));
                    System.out.println("Empaquetada: " + bombilla + ".");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("EMBALADOR: Proceso interrumpido.");
            }
        };

        executorService.submit(comprobador);
        executorService.submit(embalador);

        executorService.shutdown();
    }
}
