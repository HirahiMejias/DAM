/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionMarcaje;

import Interfaz.InterfazGenerica;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MenuMarcajes {
    private static final Scanner scanner = new Scanner(System.in);
    private InterfazGenerica<Marcajes, Integer> marcajesDAO;

    public MenuMarcajes(InterfazGenerica<Marcajes, Integer> marcajesDAO) {
        this.marcajesDAO = marcajesDAO;
    }

    public void mostrarMenuMarcajes() {
        while (true) {
            System.out.println("=== Sub Menu 3 - Marcajes ===");
            System.out.println("1 - Crear Marcaje");
            System.out.println("2 - Listar Marcajes");
            System.out.println("3 - Eliminar Marcaje");
            System.out.println("4 - Modificar Marcaje");
            System.out.println("0 - Volver");
            System.out.print("Introduzca opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1:
                    insertarMarcaje();
                    break;
                case 2:
                    mostrarTodosLosMarcajes();
                    break;
                case 3:
                    eliminarMarcaje();
                    break;
                case 4:
                    actualizarMarcaje();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción invalida. Inténtelo de nuevo.");
            }
        }
    }

    private void insertarMarcaje() {
        System.out.print("Ingrese ID Producto: ");
        int idProducto = scanner.nextInt();
        System.out.print("Ingrese ID Aula: ");
        int idAula = scanner.nextInt();
        System.out.print("Ingrese IPO: ");
        int ipo = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        // Obtener la fecha y hora actual
        Date fechaActual = new Date();
        Timestamp timeStamp = new Timestamp(fechaActual.getTime());

        // Crear el objeto Marcajes con el timestamp
        Marcajes marcaje = new Marcajes(0, idProducto, idAula, ipo, timeStamp);

        // Insertar el marcaje en la base de datos
        marcajesDAO.insert(marcaje);

        System.out.println("¡Marcaje insertado exitosamente!");
    }

private void actualizarMarcaje() {
    System.out.print("Ingrese el ID del marcaje a actualizar: ");
    int idMarcaje = scanner.nextInt();
    System.out.print("Ingrese el nuevo ID Producto: ");
    int idProducto = scanner.nextInt();
    System.out.print("Ingrese el nuevo ID Aula: ");
    int idAula = scanner.nextInt();
    System.out.print("Ingrese el nuevo IPO: ");
    int ipo = scanner.nextInt();
    scanner.nextLine(); // Consumir nueva línea

    // Obtener la fecha y hora actual
    Date fechaActual = new Date();
    Timestamp timeStamp = new Timestamp(fechaActual.getTime());

    // Crear el objeto Marcajes con el timestamp
    Marcajes marcaje = new Marcajes(idMarcaje, idProducto, idAula, ipo, timeStamp);

    // Actualizar el marcaje en la base de datos
    if (marcajesDAO.update(marcaje)) {
        System.out.println("¡Marcaje actualizado exitosamente!");
    } else {
        System.out.println("Error al actualizar el marcaje.");
    }
}

private void eliminarMarcaje() {
    System.out.print("Ingrese el ID del marcaje a eliminar: ");
    int idMarcaje = scanner.nextInt();
    scanner.nextLine(); // Consumir nueva línea

    // Eliminar el marcaje de la base de datos
    if (marcajesDAO.delete(idMarcaje)) {
        System.out.println("¡Marcaje eliminado exitosamente!");
    } else {
        System.out.println("Error al eliminar el marcaje.");
    }
}

private void mostrarTodosLosMarcajes() {
    List<Marcajes> marcajes = marcajesDAO.selectAll();
    if (marcajes.isEmpty()) {
        System.out.println("No hay marcajes registrados.");
    } else {
        System.out.println("=== Listado de Marcajes ===");
        for (Marcajes marcaje : marcajes) {
            System.out.println(marcaje);
        }
    }
}
}