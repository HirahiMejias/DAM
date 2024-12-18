package Ejercicio5;

import java.util.concurrent.Semaphore;

public class EjercicioE {
    private static final int NUM_ASIENTOS = 3;
    private static final Semaphore asientos = new Semaphore(NUM_ASIENTOS);

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            int clienteId = i;
            new Thread(() -> {
                try {
                    System.out.println("Cliente #" + clienteId + " está intentando obtener un asiento.");


                    if (asientos.tryAcquire()) {
                        System.out.println("Cliente #" + clienteId + " obtiene asiento.");


                        int tiempoAtencion = (int) (Math.random() * 10) + 1;
                        Thread.sleep(tiempoAtencion * 1000);

                        System.out.println("Cliente #" + clienteId + " ha liberado el asiento.");
                        asientos.release();
                    } else {

                        System.out.println("Cliente #" + clienteId + " ha dejado la barbería.");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }
}
