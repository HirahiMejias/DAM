package org.example;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/musica", "root", "123456");

        ejercicio1(conn);
        albumesPorAno(conn);
        discograficasSinArtistas(conn);
//        duracionTotalGrupo(conn);
//        updateGrupo(conn);
//        DeleteCancion(conn);
        GuardarFichero(conn);
    }

    //    Imprimir por pantalla una lista de todos los grupos con sus discográficas, ordenado de
//    forma ascendente por grupos. Crea la función void gruposYDisc (connection conexión)
    public static void ejercicio1(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT a.nombre as artista,d.nombre as discografica " +
                        "from artista AS a inner join discografica as d ON a.discografica_id=d.id " +
                        "order by a.nombre");
        while (rs.next()) {
            String artista = rs.getString("artista");
            String discografica = rs.getString("discografica");
            System.out.println(artista);
            System.out.println(discografica);
            System.out.println();

        }
    }

    //    Imprimir por pantalla el número de albums que se han lanzado por año. Crea la
//    función void albumesPorAño(connextion conexión)
    static void albumesPorAno(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select count(nombre) as numAlbums,anyo as anio from album group by anyo order by anyo");
        while (rs.next()) {
            int anio = rs.getInt("anio");
            int numAlbums = rs.getInt("numAlbums");
            System.out.println(numAlbums);
            System.out.println(anio);
            System.out.println();
        }
    }

    //    Imprime por pantalla las discográficas sin artistas. Crea la función void
//    discograficasSinArtistas (connection conexión)
    static void discograficasSinArtistas(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT d.nombre as disco " +
                "from artista AS a right join discografica as d ON a.discografica_id=d.id " +
                "where a.nombre is null " +
                "order by a.nombre");
        while (rs.next()) {
            String discografica = rs.getString("disco");
            System.out.println(discografica);
        }
    }
//    Crea la función void duracionTotalGrupo(connection conexion) en la que se le pida a
//    un usuario el id de un grupo en concreto, y se le devuelva la suma de la duración de
//    sus canciones.
static void duracionTotalGrupo(Connection conn) throws SQLException {
            Scanner sc = new Scanner(System.in);
        double duracion;
            System.out.println("introduce el id del artista para saber la duracion Total de sus canciones");
            String identificacion=sc.nextLine();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select a.nombre as artista,sum(c.duracion)as duracion " +
                "from artista as a " +
                "inner join album as al on a.id=al.artista_id " +
                "inner join cancion as c on al.id=c.album_id " +
                "where a.id= "+identificacion);
        while (rs.next()) {
            duracion= rs.getDouble("duracion");
            String artista= rs.getString("artista");
            System.out.println(artista);
            System.out.println(duracion);
        }
        }
//    Crea las funciones void updateGrupo(connection conexion),void
//    DeleteCancion(connection conexión) que permitan modificar los datos de las tablas
//    indicadas en los nombres de la función realizando la acción definida en su nombre, es
//    decir, poder hacer update de la tabla Grupo, y hacer Delete en la tabla canción.

    static void updateGrupo(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Update artista " +
                "set nombre=\"Yung Beef\" " +
                "where nombre=\"Cecilio G\" ");
        while (rs.next()) {
            System.out.println("Se actualizo correctamente");
        }

    }
    static void DeleteCancion(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("delete from cancion " +
                "where nombre=\"One\" ");
        while (rs.next()) {

        }
    }

//    Crea una función que lea todos los grupos pertenecientes a la discográfica ‘Hopeless
//    Records’, y almacene a estos grupos en un fichero binario llamado Hoepeless.dat.
//    Como es un fichero binario, utiliza DataOutputStream Llama a la función void
//    crearFicheroHopeless(Connection conexión, File fichero)
    static void GuardarFichero(Connection conn) throws SQLException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/Hopeless.dat"))) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select a.nombre as artista " +
                    "from artista as a " +
                    "inner join discografica as d on a.discografica_id=d.id " +
                    "where d.nombre=\"Hopeless Records\"");
            while (rs.next()) {
                String artista = rs.getString("artista");
                dos.writeUTF(artista);
            }

            rs.close();
            stmt.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}