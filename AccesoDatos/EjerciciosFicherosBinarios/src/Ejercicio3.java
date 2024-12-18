import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio3 {

    public static boolean escribirClientes(ArrayList<Cliente> listaDeTeclado, File fichero) {
        try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(fichero))) {
            for (Cliente cliente : listaDeTeclado) {
                escritor.writeObject(cliente); // Escribir cada cliente en el archivo
            }
            return true; // Devolver true si la escritura fue exitosa
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            return false;
        }
    }

    public static ArrayList<Cliente> devolverMorosos(File fichero) {
        ArrayList<Cliente> morosos = new ArrayList<>();
        try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream(fichero))) {
            while (true) {
                Cliente clienteLeido = (Cliente) lector.readObject(); // Leer cliente
                if ("deudor".equals(clienteLeido.getCuenta())) { // Verificar si es deudor
                    morosos.add(clienteLeido); // Añadir a la lista de morosos
                }
            }
        } catch (EOFException e) {
            // Fin del fichero
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return morosos;
    }


}
