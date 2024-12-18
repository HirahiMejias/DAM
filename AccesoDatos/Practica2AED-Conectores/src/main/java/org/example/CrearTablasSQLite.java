package org.example;

import java.sql.*;

public class CrearTablasSQLite {

        public void crearTablas() {
            try (Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/finalfantasy", "root", "1234")) {

                DatabaseMetaData metaDatos = conexion.getMetaData();

                System.out.println("Driver Name: " + metaDatos.getDriverName());
                System.out.println("Driver Version: " + metaDatos.getDriverVersion());
                System.out.println("Nombre de la Base de Datos: " + metaDatos.getDatabaseProductName());
                System.out.println("Versión de la Base de Datos: " + metaDatos.getDatabaseProductVersion());

                ResultSet tablas = metaDatos.getTables("finalfantasy", null, null, null);
                System.out.println("Tablas en la base de datos:");

                // Obtener la conexión de SQLite una vez aquí
                Connection conexionSQLite = ConexionSQLite.getConexion();

                while (tablas.next()) {
                    String nombreTabla = tablas.getString("TABLE_NAME");
                    String consulta = "CREATE TABLE IF NOT EXISTS " + nombreTabla + " (";

                    ResultSet columnas = metaDatos.getColumns(null, null, nombreTabla, null);
                    while (columnas.next()) {
                        String nombreColumna = columnas.getString("COLUMN_NAME");
                        String tipoColumna = columnas.getString("TYPE_NAME");
                        int tamanoColumna = columnas.getInt("COLUMN_SIZE");
                        String isNullable = columnas.getString("IS_NULLABLE");

                        consulta += nombreColumna + " " + tipoColumna + "(" + tamanoColumna + ")";
                        if ("NO".equals(isNullable)) {
                            consulta += " NOT NULL";
                        }
                        consulta += ", ";
                    }

                    // Claves primarias
                    ResultSet clavesPrimarias = metaDatos.getPrimaryKeys(null, null, nombreTabla);
                    while (clavesPrimarias.next()) {
                        consulta += "PRIMARY KEY (" + clavesPrimarias.getString("COLUMN_NAME") + ")";
                    }

                    // Claves foráneas
                    ResultSet clavesForaneas = metaDatos.getImportedKeys(null, null, nombreTabla);
                    while (clavesForaneas.next()) {
                        consulta += ", FOREIGN KEY (" + clavesForaneas.getString("FKCOLUMN_NAME") + ") REFERENCES " +
                                clavesForaneas.getString("PKTABLE_NAME") + '(' + clavesForaneas.getString("PKCOLUMN_NAME") + ")";
                    }

                    consulta = consulta.endsWith(", ") ? consulta.substring(0, consulta.length() - 2) : consulta; // Elimina la última coma
                    consulta += ");";

                    System.out.println(consulta);
                    ejecutarConsultaSQLite(consulta,conexionSQLite);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    private void ejecutarConsultaSQLite(String consulta, Connection conexionSQLite) {
        try (Statement statement = conexionSQLite.createStatement()) {
            statement.execute(consulta);
            System.out.println("Tabla creada en SQLite: " + consulta);
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla en SQLite: " + e.getMessage());
        }
    }
    }





