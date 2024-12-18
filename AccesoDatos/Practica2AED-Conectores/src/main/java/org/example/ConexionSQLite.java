package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLite {
    static Connection con;

    private ConexionSQLite() {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:finalfantasy.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConexion() {
        if (con == null) {
            new ConexionSQLite();
        }
        return con;
    }

    // Método para cerrar la conexión
    public static void cerrarConexion() {
        if (con != null) {
            try {
                con.close();
                con = null; // Asegurarse de que la conexión se pueda volver a abrir
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
