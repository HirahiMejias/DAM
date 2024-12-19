package GestionProductos;

import Interfaz.InterfazGenerica;

import java.util.List;
import java.util.Scanner;

public class MenuProductos {
    private static final Scanner scanner = new Scanner(System.in);
    private InterfazGenerica<Productos, Integer> productosDAO;

    public MenuProductos(InterfazGenerica<Productos, Integer> productosDAO) {
        this.productosDAO = productosDAO;
    }

    public void mostrarMenuProductos() {
        while (true) {
            System.out.println("=== Sub Menú 2 - Productos ===");
            System.out.println("1 - Crear Producto");
            System.out.println("2 - Listar Productos");
            System.out.println("3 - Eliminar Producto");
            System.out.println("4 - Modificar Producto");
            System.out.println("0 - Volver");
            System.out.print("Introduzca opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1:
                    insertarProducto();
                    break;
                case 2:
                    mostrarTodosLosProductos();
                    break;
                case 3:
                    eliminarProducto();
                    break;
                case 4:
                    actualizarProducto();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
            }
        }
    }

   private void insertarProducto() {
        System.out.println("Ingrese la descripción del producto:");
        String descripcion = scanner.nextLine();
        System.out.println("Ingrese el código EAN13 del producto:");
        int ean13 = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea
        System.out.println("Ingrese la clave RFID del producto:");
        String keyRFID = scanner.nextLine();

        Productos producto = new Productos(0, descripcion, ean13, keyRFID);
        boolean exito = productosDAO.insert(producto);
        if (exito) {
            System.out.println("Producto creado exitosamente!");
        } else {
            System.out.println("Error al crear el producto.");
        }
    }

    private void mostrarTodosLosProductos() {
        List<Productos> productosList = productosDAO.selectAll();
        if (productosList.isEmpty()) {
            System.out.println("No hay productos disponibles.");
        } else {
            System.out.println("=== Lista de Productos ===");
            for (Productos producto : productosList) {
                System.out.println(producto);
            }
        }
    }

    private void eliminarProducto() {
        System.out.println("Ingrese el ID del producto que desea eliminar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        boolean exito = productosDAO.delete(id);
        if (exito) {
            System.out.println("Producto eliminado exitosamente!");
        } else {
            System.out.println("Error al eliminar el producto.");
        }
    }

    private void actualizarProducto() {
        System.out.println("Ingrese el ID del producto que desea modificar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        Productos producto = productosDAO.selectById(id);
        if (producto == null) {
            System.out.println("El producto no existe.");
            return;
        }

        System.out.println("Ingrese la nueva descripción del producto:");
        String nuevaDescripcion = scanner.nextLine();
        System.out.println("Ingrese el nuevo código EAN13 del producto:");
        int nuevoEan13 = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea
        System.out.println("Ingrese la nueva clave RFID del producto:");
        String nuevaKeyRFID = scanner.nextLine();

        producto.setDescripcion(nuevaDescripcion);
        producto.setEan13(nuevoEan13);
        producto.setKeyRFID(nuevaKeyRFID);

        boolean exito = productosDAO.update(producto);
        if (exito) {
            System.out.println("Producto modificado exitosamente!");
        } else {
            System.out.println("Error al modificar el producto.");
        }
    }
}
