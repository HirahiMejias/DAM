/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import GestionAulas.Aula;
import GestionAulas.AulaDAO;
import GestionMarcaje.Marcajes;
import GestionMarcaje.MarcajesDAO;
import GestionProductos.Productos;
import GestionProductos.ProductosDAO;
import Interfaz.InterfazGenerica;
import inicio.ConexionBD;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author 1dama
 */
public class MenuDatos {
    
    private InterfazGenerica<Aula, Integer> aulaDAO;
    private ArrayList<Aula> aulasDesconectadas; // Modificación

    public MenuDatos(InterfazGenerica<Aula, Integer> aulaDAO, ArrayList<Aula> aulasDesconectadas) { // Modificación
        this.aulaDAO = aulaDAO;
        this.aulasDesconectadas = aulasDesconectadas; // Modificación
    }
    
    private static final String AULAS_FILE_PATH = "src/Datos/Aulas.txt";
    private static final String PRODUCTOS_FILE_PATH = "src/Datos/Productos.txt";
    private static final String MARCAJES_FILE_PATH = "src/Datos/Marcajes.txt";

    // Método genérico para cargar datos desde archivo
    public static <T> void cargarDatosDesdeArchivo(String archivo, ArrayList<T> lista) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            ArrayList<T> loadedList = (ArrayList<T>) ois.readObject();
            lista.clear();
            lista.addAll(loadedList);
            System.out.println("Datos cargados desde el archivo " + archivo);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar datos desde el archivo: " + e.getMessage());
        }
    }

    // Método genérico para guardar datos en archivo
    public static <T> void guardarDatosEnArchivo(String archivo, ArrayList<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
            System.out.println("Datos guardados en el archivo " + archivo);
        } catch (IOException e) {
            System.out.println("Error al guardar datos en el archivo: " + e.getMessage());
        }
    }
    
    public void cargarDatosDesdeBD(ArrayList<Aula> aulas, ArrayList<Productos> productos, ArrayList<Marcajes> marcajes) {
    // Conectar a la base de datos
    Connection conn = ConexionBD.conectar();

    if (conn != null) {
        // Llamar a los métodos correspondientes de los DAO para cargar los datos
        AulaDAO aulaDAO = new AulaDAO(conn);
        ProductosDAO productosDAO = new ProductosDAO(conn);
        MarcajesDAO marcajesDAO = new MarcajesDAO(conn);

        // Cargar los datos desde la base de datos
        aulas.clear();
        aulas.addAll(aulaDAO.selectAll());

        productos.clear();
        productos.addAll(productosDAO.selectAll());

        marcajes.clear();
        marcajes.addAll(marcajesDAO.selectAll());

        // Desconectar de la base de datos
        ConexionBD.desconectar();
    } else {
        System.out.println("No se pudo conectar a la base de datos. Trabajando desconectado...");
        // Aquí podrías implementar una lógica adicional para cargar los datos de los archivos, si lo deseas
    }
}

public void guardarDatosEnBD(ArrayList<Aula> aulas, ArrayList<Productos> productos, ArrayList<Marcajes> marcajes) {
    // Conectar a la base de datos
    Connection conn = ConexionBD.conectar();

    if (conn != null) {
        // Llamar a los métodos correspondientes de los DAO para guardar los datos
        AulaDAO aulaDAO = new AulaDAO(conn);
        ProductosDAO productosDAO = new ProductosDAO(conn);
        MarcajesDAO marcajesDAO = new MarcajesDAO(conn);

        // Guardar los datos en la base de datos
        for (Aula aula : aulas) {
            aulaDAO.insert(aula);
        }

        for (Productos producto : productos) {
            productosDAO.insert(producto);
        }

        for (Marcajes marcaje : marcajes) {
            marcajesDAO.insert(marcaje);
        }

        // Desconectar de la base de datos
        ConexionBD.desconectar();
    } else {
        System.out.println("No se pudo conectar a la base de datos. Trabajando desconectado...");
        // Aquí podrías implementar una lógica adicional para guardar los datos en archivos, si lo deseas
    }
}

public void mostrarMenuDatos() {
    Scanner sc = new Scanner(System.in);
    ArrayList<Aula> aulas = new ArrayList<>();
    ArrayList<Productos> productos = new ArrayList<>();
    ArrayList<Marcajes> marcajes = new ArrayList<>();

    while (true) {
        System.out.println("Sub Menu 5 - Datos");
        System.out.println("1 - Cargar datos de archivos");
        System.out.println("2 - Guardar datos en archivos");
        System.out.println("3 - Cargar datos de Base de Datos");
        System.out.println("4 - Guardar datos en Base de Datos");
        System.out.println("0 - Volver");
        System.out.print("Introduzca opción: ");
        int opcion = sc.nextInt();
        sc.nextLine(); // Consumir el salto de línea

        switch (opcion) {
            case 1:
                cargarDatosDesdeArchivo(AULAS_FILE_PATH, aulas);
                cargarDatosDesdeArchivo(PRODUCTOS_FILE_PATH, productos);
                cargarDatosDesdeArchivo(MARCAJES_FILE_PATH, marcajes);
                break;
            case 2:
                guardarDatosEnArchivo(AULAS_FILE_PATH, aulas);
                guardarDatosEnArchivo(PRODUCTOS_FILE_PATH, productos);
                guardarDatosEnArchivo(MARCAJES_FILE_PATH, marcajes);
                break;
            case 3:
                // Implementar la carga de datos desde la base de datos
                cargarDatosDesdeBD(aulas, productos, marcajes);
                break;
            case 4:
                // Implementar la guardado de datos en la base de datos
                guardarDatosEnBD(aulas, productos, marcajes);
                break;
            case 0:
                System.out.println("Volviendo al menú principal...");
                return;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
                break;
        }
    }
}

}
