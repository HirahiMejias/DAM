/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dao;

import java.sql.*;

/**
 *
 * @author 1dama
 */
public class DAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        
        Connection miCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/hirahibdprueba", "root", "");
        Statement miSt = miCon.createStatement();
        //ResultSet miRs = miSt.executeQuery("SELECT * FROM artistas ");
        ResultSet miRs = miSt.executeQuery("SELECT * FROM hirahi ");
        // procesa los resultados de la consulta
        ResultSetMetaData metaDatos = miRs.getMetaData();
        int numeroDeColumnas = metaDatos.getColumnCount();
        System.out.println("Tabla Hirahi de la base de datos hirahibdprueba:\n");

        for (int i = 1; i <= numeroDeColumnas; i++) {
            System.out.printf("%-8s\t", metaDatos.getColumnLabel(i) + "[" + metaDatos.getColumnTypeName(i) + "]");
        }
        System.out.println();

        while (miRs.next()) {
            for (int i = 1; i <= numeroDeColumnas; i++) {

                System.out.print(miRs.getString(metaDatos.getColumnName(i)) + "\t" + "\t");
                // System.out.print(miRs.getString(metaDatos.getColumnLabel(i)) + "\t" + "\t");
            }
            System.out.println();
            /**
             * while (miRs.next()) {
             *
             * System.out.println(miRs.getString(1) + "\t" + "\t" +
             * miRs.getString(2) + "\t" + "\t" + miRs.getString(3) + "\t" + "\t"
             * + miRs.getString(4) + "\t" + "\t" ); }
             */
        }
        }
    catch (SQLException ex) {

            System.out.println("Error" + ex.toString());

    }
}
}