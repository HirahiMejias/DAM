package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainConsultas {
    public static void main(String[] args) {
        Connection conexionMySQL = null;

        try {
            conexionMySQL = DriverManager.getConnection(
                    "jdbc:sqlite:finalfantasy.db");

            Consultas consultas = new Consultas(conexionMySQL);

            consultas.consultaMonstruos();
            System.out.println();

            consultas.consultaPersonajesPorPlataforma();
            System.out.println();

            consultas.consultaJuegosConMasDe6Personajes();
            System.out.println();

            consultas.consultaMonstruosMPyHP();
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexionMySQL != null) conexionMySQL.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
