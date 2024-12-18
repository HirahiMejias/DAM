package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws SQLException {


        // Crear una instancia de CrearTablasSQLite
        CrearTablasSQLite crearTablas = new CrearTablasSQLite();

        // Llamar al método para crear las tablas
        crearTablas.crearTablas();

        // Crear una instancia de MigrarDatos
        MigrarDatos migrarDatos = new MigrarDatos();
        // Llamar al método para migrar los datos
        migrarDatos.migrar();

        // Crear una instancia de Consultas
//        Consultas consultas = new Consultas();

//        try {
//            System.out.println("Monstruos que no son Chocobos:");
//            consultas.monstruosNoChocobos();
//
//            System.out.println("\nCantidad de personajes por plataforma:");
//            consultas.contarPersonajesPorPlataforma();
//
//            System.out.println("\nJuegos con más de 6 personajes:");
//            consultas.juegosConMasDe6Personajes();
//
//            System.out.println("\nMonstruos con menos MP que la media y más HP que la media:");
//            consultas.monstruosConMpYHpMedia();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            // Cerrar la conexión al final
//            ConexionSQLite.cerrarConexion();
//        }

    }
}

