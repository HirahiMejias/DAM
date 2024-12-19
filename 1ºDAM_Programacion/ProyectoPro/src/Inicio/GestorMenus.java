package inicio;

import static Datos.Datos.cargarDatosDesdeArchivo;
import static Datos.Datos.cargarDatosDesdeBD;
import static Datos.Datos.guardarDatosEnArchivo;
import static Datos.Datos.guardarDatosEnBD;
import Datos.MenuDatos;
import GestionAulas.Aula;
import GestionAulas.AulaDAO;
import GestionAulas.MenuAulas;
import GestionMarcaje.Marcajes;
import GestionMarcaje.MarcajesDAO;
import GestionProductos.MenuProductos;
import GestionMarcaje.MenuMarcajes;
import GestionProductos.Productos;
import GestionProductos.ProductosDAO;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorMenus {
    
    private MenuDatos menuDatos;

    public GestorMenus() {
        this.menuDatos = new MenuDatos();
    }
    
    public void mostrarMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        
        int opcion;

        do {
            System.out.println("=== Menú Principal ===");
            System.out.println("1 - Gestión Aulas.");
            System.out.println("2 - Gestión Productos.");
            System.out.println("3 - Gestión Marcajes.");
            System.out.println("4 - Informes.");
            System.out.println("5 - Datos.");
            System.out.println("0 - Salir.");
            System.out.print("Introduzca opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de la opción

            switch (opcion) {
                case 1:
                    // Llamar al menú de gestión de Aulas
                    AulaDAO aulaDAO = new AulaDAO(ConexionBD.conectar());
                    MenuAulas menuAulas = new MenuAulas(aulaDAO);
                    menuAulas.mostrarMenuAulas();
                    break;
                case 2:
                    // Llamar al menú de gestión de Productos
                    ProductosDAO productosDAO = new ProductosDAO(ConexionBD.conectar());
                    MenuProductos menuProductos = new MenuProductos(productosDAO);
                    menuProductos.mostrarMenuProductos();
                    break;
                case 3:
                    // Llamar al menú de gestión de Marcajes
                    MarcajesDAO marcajesDAO = new MarcajesDAO(ConexionBD.conectar());
                    MenuMarcajes menuMarcajes = new MenuMarcajes(marcajesDAO);
                    menuMarcajes.mostrarMenuMarcajes();
                    break;
                case 4:
                    // Llamar al menú de Informes
                    mostrarMenuInformes();
                    break;
                case 5:
                    // Llamar al menú de Datos
                    menuDatos.mostrarMenuDatos();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
                    break;
            }
        } while (opcion != 0);

        scanner.close();
    }

 
    private void mostrarMenuInformes() {
        Scanner scanner = new Scanner(System.in);
        
        int opcion;

        do {
            System.out.println("=== Menú Datos ===");
            System.out.println("1 - Importar Datos.");
            System.out.println("2 - Exportar Datos.");
            System.out.println("0 - Volver al Menú Principal.");
            System.out.print("Introduzca opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de la opción

            switch (opcion) {
                case 1:
                    // Lógica para importar datos
                    System.out.println("Importando datos...");
                    break;
                case 2:
                    // Lógica para exportar datos
                    System.out.println("Exportando datos...");
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