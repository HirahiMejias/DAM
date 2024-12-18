package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Consultas {
    private Connection conexionMySQL;

    public Consultas(Connection conexionMySQL) {
        this.conexionMySQL = ConexionSQLite.getConexion();
    }

    public void consultaMonstruos() throws SQLException {
        String sqlMaxAtaque = "SELECT * FROM monstruo WHERE nombre <> 'Chocobo' ORDER BY ataque DESC LIMIT 1;";

        try (Statement stmt = conexionMySQL.createStatement();
             ResultSet rsMaxAtaque = stmt.executeQuery(sqlMaxAtaque)) {

            System.out.println("--------------------------------------------------");
            System.out.println("Monstruos que no sean Chocobos y con mayor ataque:");
            System.out.println("--------------------------------------------------");

            if (rsMaxAtaque.next()) {
                System.out.println("ID: " + rsMaxAtaque.getInt("idMonstruo") + ", Nombre: " + rsMaxAtaque.getString("nombre") +
                        ", Ataque: " + rsMaxAtaque.getInt("ataque") + ", Defensa: " + rsMaxAtaque.getInt("defensa") +
                        ", MP: " + rsMaxAtaque.getInt("mp") + ", HP: " + rsMaxAtaque.getInt("hp"));
            }
        }
    }

    public void consultaPersonajesPorPlataforma() throws SQLException {
        String sql = "SELECT plataforma, COUNT(*) AS total_personajes FROM juego GROUP BY plataforma;";

        try (Statement stmt = conexionMySQL.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("-----------------------------------");
            System.out.println("Total de personajes por plataforma:");
            System.out.println("-----------------------------------");
            while (rs.next()) {
                System.out.println("Plataforma: " + rs.getString("plataforma") + ", Total: " + rs.getInt("total_personajes"));
            }
        }
    }

    public void consultaJuegosConMasDe6Personajes() throws SQLException {
        String sql = "SELECT j.titulo, COUNT(p.id) AS total_personajes " +
                "FROM juego j " +
                "JOIN personaje p ON j.titulo = p.origen " +
                "GROUP BY j.titulo " +
                "HAVING COUNT(p.id) > 6 " +
                "ORDER BY total_personajes DESC;";

        try (Statement stmt = conexionMySQL.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("-------------------------------");
            System.out.println("Juegos con más de 6 personajes:");
            System.out.println("-------------------------------");
            while (rs.next()) {
                System.out.println("Título: " + rs.getString("titulo") +
                        ", Total Personajes: " + rs.getInt("total_personajes"));
            }
        }
    }

    public void consultaMonstruosMPyHP() throws SQLException {
        String sql = "SELECT * FROM monstruo WHERE mp < (SELECT AVG(mp) FROM monstruo) " +
                "AND hp > (SELECT AVG(hp) FROM monstruo);";

        try (Statement stmt = conexionMySQL.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Monstruos con menos MP que la media y más HP que la media:");
            boolean hayResultados = false; // Variable para verificar si hay resultados
            while (rs.next()) {
                hayResultados = true;
                System.out.println("ID: " + rs.getString("idMonstruo") +
                        ", Nombre: " + rs.getString("nombre") +
                        ", Afinidad: " + rs.getString("afinidad") +
                        ", Debilidad: " + rs.getString("debilidad") +
                        ", HP: " + rs.getInt("hp") +
                        ", MP: " + rs.getInt("mp") +
                        ", Ataque: " + rs.getInt("ataque") +
                        ", Defensa: " + rs.getInt("defensa") +
                        ", Foto: " + rs.getString("foto") +
                        ", Descripción: " + rs.getString("descripcion") +
                        ", Juego: " + rs.getString("juego"));
            }
            if (!hayResultados) {
                System.out.println("No se encontraron monstruos que cumplan con los criterios.");
            }
        }
    }
}
