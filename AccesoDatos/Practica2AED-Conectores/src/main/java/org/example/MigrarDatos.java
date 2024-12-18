package org.example;


import java.sql.*;

public class MigrarDatos {

    public void migrar() {
        // Establecer la conexión a MySQL
        try (Connection conexionMySQL = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/finalfantasy", "root", "1234");
             Connection conexionSQLite = ConexionSQLite.getConexion()) {

            // Migrar la tabla "juego"
            migrarTabla(conexionMySQL, conexionSQLite, "juego");
            // Migrar la tabla "monstruo"
            migrarTabla(conexionMySQL, conexionSQLite, "monstruo");
            // Migrar la tabla "personaje"
            migrarTabla(conexionMySQL, conexionSQLite, "personaje");
            // Migrar la tabla "stats"
            migrarTabla(conexionMySQL, conexionSQLite, "stats");



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void migrarTabla(Connection conexionMySQL, Connection conexionSQLite, String nombreTabla) {
        String selectSQL = "SELECT * FROM " + nombreTabla;
        try (Statement stmtMySQL = conexionMySQL.createStatement();
             ResultSet rs = stmtMySQL.executeQuery(selectSQL)) {

            // Obtener metadatos de la tabla
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Preparar la inserción en SQLite
            String insertSQL = crearInsertSQL(nombreTabla, metaData);
            try (PreparedStatement pstmtSQLite = conexionSQLite.prepareStatement(insertSQL)) {
                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        pstmtSQLite.setObject(i, rs.getObject(i));
                    }
                    pstmtSQLite.executeUpdate();
                }
                System.out.println("Datos migrados para la tabla: " + nombreTabla);
            }

        } catch (SQLException e) {
            System.out.println("Error al migrar la tabla " + nombreTabla + ": " + e.getMessage());
        }
    }

    private String crearInsertSQL(String nombreTabla, ResultSetMetaData metaData) throws SQLException {
        StringBuilder sql = new StringBuilder("INSERT INTO " + nombreTabla + " (");
        StringBuilder placeholders = new StringBuilder();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            sql.append(metaData.getColumnName(i));
            placeholders.append("?");
            if (i < columnCount) {
                sql.append(", ");
                placeholders.append(", ");
            }
        }
        sql.append(") VALUES (").append(placeholders).append(")");

        return sql.toString();
    }
}
