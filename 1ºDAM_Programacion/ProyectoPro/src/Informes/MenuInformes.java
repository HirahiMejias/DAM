/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Informes;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class MenuInformes {
     public void mostrarMenuInformes() {
        Scanner scanner = new Scanner(System.in);
        
        int opcion;

        do {
            System.out.println("=== Menú Informes ===");
            System.out.println("1 - Generar Informe de Aulas.");
            System.out.println("2 - Generar Informe de Productos.");
            System.out.println("3 - Generar Informe de Marcajes.");
            System.out.println("0 - Volver al Menú Principal.");
            System.out.print("Introduzca opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de la opción

            switch (opcion) {
                case 1:
                    // Lógica para generar informe de aulas
                    System.out.println("Generando informe de aulas...");
                    break;
                case 2:
                    // Lógica para generar informe de productos
                    System.out.println("Generando informe de productos...");
                    break;
                case 3:
                    // Lógica para generar informe de marcajes
                    System.out.println("Generando informe de marcajes...");
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
                    break;
            }
        } while (opcion != 0);
    }
}
