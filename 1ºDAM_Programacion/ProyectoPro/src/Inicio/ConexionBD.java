package inicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static Connection conn = null;

      public static Connection conectar() {
    Connection conn = null;
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario", "root", "");
        System.out.println("Conexión establecida con la base de datos.");
    } catch (SQLException ex) {
        System.out.println("Error al conectar con la base de datos: " + ex.getMessage());
        // No necesitas asignar null a la conexión, ya que no se pudo establecer
    }
    
    // Verificar si la conexión se realizó con éxito
    if (conn != null) {
        // Si hay conexión, trabajar conectado
        System.out.println("Trabajando conectado...");
    } else {
        // Si no hay conexión, trabajar desconectado
        System.out.println("Trabajando desconectado...");
        // Realizar acciones para trabajar desconectado, como cargar datos desde archivos, etc.
    }
    
    return conn; // Devolver la conexión (puede ser null si no se pudo establecer)
}


    public static void desconectar() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión: " + ex.getMessage());
        }
    }
}