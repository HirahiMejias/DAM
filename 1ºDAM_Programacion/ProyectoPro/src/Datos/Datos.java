/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import GestionAulas.Aula;
import GestionMarcaje.Marcajes;
import GestionProductos.Productos;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Admin
 */
public class Datos {

    // Métodos para cargar y guardar datos desde/hacia archivos
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

    public static void guardarDatosEnArchivo(String archivo, ArrayList<?> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
            System.out.println("Datos guardados en el archivo " + archivo);
        } catch (IOException e) {
            System.out.println("Error al guardar datos en el archivo: " + e.getMessage());
        }
    }

    // Métodos para cargar y guardar datos desde/hacia base de datos
    public static void cargarDatosDesdeBD(String url, String usuario, String contrasena, ArrayList<Aula> aulas, ArrayList<Productos> productos, ArrayList<Marcajes> marcajes) {
        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena)) {
            Statement stmt = conn.createStatement();
            
            // Cargar aulas
            ResultSet rsAulas = stmt.executeQuery("SELECT * FROM aula");
            while (rsAulas.next()) {
                aulas.add(new Aula(rsAulas.getInt("IdAula"), rsAulas.getString("Numeracion"), rsAulas.getString("Descripcion"), rsAulas.getString("IP")));
            }
            
            // Cargar productos
            ResultSet rsProductos = stmt.executeQuery("SELECT * FROM productos");
            while (rsProductos.next()) {
                productos.add(new Productos(rsProductos.getInt("IdProducto"), rsProductos.getString("Descripcion"), rsProductos.getInt("EAN13"), rsProductos.getString("keyRFID")));
            }

            // Cargar marcajes
            ResultSet rsMarcajes = stmt.executeQuery("SELECT * FROM marcaje");
            while (rsMarcajes.next()) {
                marcajes.add(new Marcajes(rsMarcajes.getInt("IdMarcaje"), rsMarcajes.getInt("IdProducto"), rsMarcajes.getInt("IdAula"), rsMarcajes.getInt("ipo"), rsMarcajes.getTimestamp("TimeStamp")));
            }
            
            System.out.println("Datos cargados desde la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al cargar datos desde la base de datos: " + e.getMessage());
        }
    }

    public static void guardarDatosEnBD(String url, String usuario, String contrasena, ArrayList<Aula> aulas, ArrayList<Productos> productos, ArrayList<Marcajes> marcajes) {
        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena)) {
            Statement stmt = conn.createStatement();
            
            // Guardar aulas
            for (Aula aula : aulas) {
                stmt.executeUpdate("INSERT INTO aula (Numeracion, Descripcion, IP) VALUES ('" + aula.getNumeracion() + "', '" + aula.getDescripcion() + "', '" + aula.getIp()+ "')");
            }

            // Guardar productos
            for (Productos producto : productos) {
                stmt.executeUpdate("INSERT INTO productos (Descripcion, EAN13, keyRFID) VALUES ('" + producto.getDescripcion() + "', " + producto.getEan13() + ", '" + producto.getKeyRFID() + "')");
            }

            // Guardar marcajes
            for (Marcajes marcaje : marcajes) {
                stmt.executeUpdate("INSERT INTO marcaje (IdProducto, IdAula, Tipo, TimeStamp) VALUES (" + marcaje.getIdProducto() + ", " + marcaje.getIdAula() + ", '" + marcaje.getIpo()+ "', '" + marcaje.getTimeStamp() + "')");
            }

            System.out.println("Datos guardados en la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al guardar datos en la base de datos: " + e.getMessage());
        }
    }
}
