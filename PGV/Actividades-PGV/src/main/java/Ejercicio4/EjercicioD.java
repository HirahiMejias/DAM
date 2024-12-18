package Ejercicio4;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EjercicioD {

    public static void main(String[] args) {
        int tamanyo = 900;
        double[][] a = generarDecimales(tamanyo, tamanyo);
        double[][] b = generarDecimales(tamanyo, tamanyo);



        double[][] resultadoSecuencial = multiplicarMatricesSecuencial(a, b);



        System.out.println("Resultado Secuencial:");
        imprimirMatriz(resultadoSecuencial);

        long inicioSecuencial = System.currentTimeMillis();

        long inicioParalela = System.currentTimeMillis();
        double[][] resultadoParalelo = multiplicarMatricesParalelo(a, b);
        long finParalela = System.currentTimeMillis();
        System.out.println("El multiproceso paralelo emplea: " + (finParalela - inicioParalela) + " milisegundos");


        System.out.println("Resultado Paralelo:");
        imprimirMatriz(resultadoParalelo);


        long finSecuencial = System.currentTimeMillis();
        System.out.println("El proceso secuencial emplea: " + (finSecuencial - inicioSecuencial) + " milisegundos");
    }


    public static double[][] generarDecimales(int filas, int columnas) {
        Random random = new Random();
        double[][] matriz = new double[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = Math.round((random.nextDouble() * 1000) * 100.0) / 100.0; // Redondeo a dos decimales
            }
        }
        return matriz;
    }


    public static double[][] multiplicarMatricesSecuencial(double[][] a, double[][] b) {
        int filasA = a.length;
        int columnasA = a[0].length;
        int columnasB = b[0].length;
        double[][] resultado = new double[filasA][columnasB];

        for (int i = 0; i < filasA; i++) {
            for (int j = 0; j < columnasB; j++) {
                for (int k = 0; k < columnasA; k++) {
                    resultado[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return resultado;
    }


    public static double[][] multiplicarMatricesParalelo(double[][] a, double[][] b) {
        int filasA = a.length;
        int columnasB = b[0].length;
        double[][] resultado = new double[filasA][columnasB];

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < filasA; i++) {
            final int fila = i;
            executor.submit(() -> {
                for (int j = 0; j < columnasB; j++) {
                    for (int k = 0; k < a[0].length; k++) {
                        resultado[fila][j] += a[fila][k] * b[k][j];
                    }
                }
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultado;
    }


    public static void imprimirMatriz(double[][] matriz) {
        for (double[] fila : matriz) {
            for (double valor : fila) {
                System.out.printf("%.2f\t", valor); // Formatear a dos decimales
            }
            System.out.println();
        }
    }
}
