package GestionAulas;

import Interfaz.InterfazGenerica;
import inicio.ConexionBD;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuAulas {
    private static final Scanner scanner = new Scanner(System.in);
    private InterfazGenerica<Aula, Integer> aulaDAO;
    private List<Aula> aulasDesconectadas;

    public MenuAulas(InterfazGenerica<Aula, Integer> aulaDAO) {
        this.aulaDAO = aulaDAO;
        this.aulasDesconectadas = new ArrayList<>();
    }

    public void mostrarMenuAulas() {
        while (true) {
            System.out.println("=== Menú ===");
            System.out.println("1. Insertar Aula");
            System.out.println("2. Actualizar Aula");
            System.out.println("3. Eliminar Aula");
            System.out.println("4. Mostrar Aula por ID");
            System.out.println("5. Mostrar todas las Aulas");
            System.out.println("6. Salir");

            System.out.print("Ingrese su opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1:
                    insertarAula();
                    break;
                case 2:
                    actualizarAula();
                    break;
                case 3:
                    eliminarAula();
                    break;
                case 4:
                    mostrarAulaPorId();
                    break;
                case 5:
                    mostrarTodasLasAulas();
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
            }
        }
    }

private void insertarAula() {
        System.out.print("Ingrese Numeración: ");
        String numeracion = scanner.nextLine();
        System.out.print("Ingrese Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese IP: ");
        String ip = scanner.nextLine();
        
        Aula aula = new Aula(0, numeracion, descripcion, ip);

        if (ConexionBD.conectar() != null) {
            // Si hay conexión, inserta en la base de datos
            aulaDAO.insert(aula);
            System.out.println("¡Aula insertada exitosamente en la base de datos!");
        } else {
            // Si no hay conexión, agrega al ArrayList
            aulasDesconectadas.add(aula);
            System.out.println("¡Aula insertada en la lista de trabajo desconectado!");
        }
    }

    private void actualizarAula() {
    System.out.print("Ingrese ID del Aula a actualizar: ");
    int id = scanner.nextInt();
    scanner.nextLine(); // Consumir nueva línea
    System.out.print("Ingrese nueva Numeración: ");
    String numeracion = scanner.nextLine();
    System.out.print("Ingrese nueva Descripción: ");
    String descripcion = scanner.nextLine();
    System.out.print("Ingrese nueva IP: ");
    String ip = scanner.nextLine();
    
    Aula aula = new Aula(id, numeracion, descripcion, ip);

    if (ConexionBD.conectar() != null) {
        // Si hay conexión, actualizar en la base de datos
        aulaDAO.update(aula);
        System.out.println("¡Aula actualizada exitosamente en la base de datos!");
    } else {
        // Si no hay conexión, buscar y actualizar en el ArrayList
        boolean actualizado = false;
        for (Aula a : aulasDesconectadas) {
            if (a.getIdAula() == id) {
                a.setNumeracion(numeracion);
                a.setDescripcion(descripcion);
                a.setIp(ip);
                actualizado = true;
                System.out.println("¡Aula actualizada exitosamente en la lista de trabajo desconectado!");
                break;
            }
        }
        if (!actualizado) {
            System.out.println("No se encontró el Aula en la lista de trabajo desconectado.");
        }
    }
}
private void eliminarAula() {
    System.out.print("Ingrese ID del Aula a eliminar: ");
    int id = scanner.nextInt();
    scanner.nextLine(); // Consumir nueva línea

    if (ConexionBD.conectar() != null) {
        // Si hay conexión, eliminar de la base de datos
        if (aulaDAO.delete(id)) {
            System.out.println("¡Aula eliminada exitosamente de la base de datos!");
        } else {
            System.out.println("No se pudo eliminar el Aula de la base de datos.");
        }
    } else {
        // Si no hay conexión, buscar y eliminar de la lista
        boolean eliminado = false;
        for (Aula a : aulasDesconectadas) {
            if (a.getIdAula() == id) {
                aulasDesconectadas.remove(a);
                eliminado = true;
                System.out.println("¡Aula eliminada exitosamente de la lista de trabajo desconectado!");
                break;
            }
        }
        if (!eliminado) {
            System.out.println("No se encontró el Aula en la lista de trabajo desconectado.");
        }
    }
}

    private void mostrarAulaPorId() {
    System.out.print("Ingrese ID del Aula a mostrar: ");
    int id = scanner.nextInt();
    scanner.nextLine(); // Consumir nueva línea

    if (ConexionBD.conectar() != null) {
        // Si hay conexión, mostrar desde la base de datos
        Aula aula = aulaDAO.selectById(id);
        if (aula != null) {
            System.out.println(aula);
        } else {
            System.out.println("Aula no encontrada en la base de datos.");
        }
    } else {
        // Si no hay conexión, buscar en el ArrayList
        boolean encontrado = false;
        for (Aula a : aulasDesconectadas) {
            if (a.getIdAula() == id) {
                System.out.println(a);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Aula no encontrada en la lista de trabajo desconectado.");
        }
    }
}

   private void mostrarTodasLasAulas() {
    if (ConexionBD.conectar() != null) {
        // Si hay conexión, mostrar todas desde la base de datos
        List<Aula> aulas = aulaDAO.selectAll();
        for (Aula aula : aulas) {
            System.out.println(aula);
        }
    } else {
        // Si no hay conexión, mostrar todas desde el ArrayList
        for (Aula aula : aulasDesconectadas) {
            System.out.println(aula);
        }
    }
}
}
