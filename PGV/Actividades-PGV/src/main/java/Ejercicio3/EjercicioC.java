package Ejercicio3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class EjercicioC {

    public static void main(String[] args) {

        int DIM = 8;


        long startTimeA = System.nanoTime();
        double[][] matrizA = generarMatriz(DIM);

        long startTimeB = System.nanoTime();
        double[][] matrizB = generarMatriz(DIM);


        long startTimeSuma = System.nanoTime();
        double[][] matrizResultado = sumaParalela(matrizA, matrizB, DIM);
        long endTimeSuma = System.nanoTime();
        System.out.println("Tiempo de suma de matrices: " + (endTimeSuma - startTimeSuma) / 1_000_000.0 + " ms");


        System.out.println("\nMatriz A:\t\t\t\t\t\t\t\t\t\t\t\t\t\tMatriz B:\t\t\t\t\t\t\t\t\t\t\t\t\t\tResultado:");
        mostrarMatrices(matrizA, matrizB, matrizResultado);

        // Contar y mostrar el número de procesadores utilizados
        int numProcesadores = Runtime.getRuntime().availableProcessors();
        System.out.println("Número de procesadores utilizados: " + numProcesadores);
    }


    public static double[][] generarMatriz(int dim) {
        double[][] matriz = new double[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                matriz[i][j] = generarNumeroDosDecimales(dim);
            }
        }
        return matriz;
    }


    public static double generarNumeroDosDecimales(int dim) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double numero = random.nextInt(dim) + random.nextDouble();  // Número aleatorio entre 0 y DIM
        return Math.round(numero * 100.0) / 100.0;  // Redondear a 2 decimales
    }


    public static double[][] sumaParalela(double[][] a, double[][] b, int dim) {
        double[][] resultado = new double[dim][dim];
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


        for (int i = 0; i < dim; i++) {
            int fila = i;
            executor.execute(() -> {
                for (int j = 0; j < dim; j++) {
                    resultado[fila][j] = a[fila][j] + b[fila][j];
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {

        }

        return resultado;
    }

    public static void mostrarMatrices(double[][] matrizA, double[][] matrizB, double[][] matrizResultado) {
        int dim = matrizA.length;


        for (int i = 0; i < dim; i++) {

            for (int j = 0; j < dim; j++) {
                System.out.printf("%6.2f ", matrizA[i][j]);
            }
            System.out.print("\t\t");
            for (int j = 0; j < dim; j++) {
                System.out.printf("%6.2f ", matrizB[i][j]);
            }
            System.out.print("\t\t");
            for (int j = 0; j < dim; j++) {
                System.out.printf("%6.2f ", matrizResultado[i][j]);
            }
            System.out.println();
        }
    }
}
