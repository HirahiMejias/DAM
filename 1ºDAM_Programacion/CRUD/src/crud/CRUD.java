/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package crud;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Siempre que este vayamos a mostrar la lista completa hacer con el iterador
 *
 * @author 1dama
 */
public class CRUD {

    Scanner scanner = new Scanner(System.in);
    private static ArrayList<Contactos> contacto;
    private static ArrayList<Contactos> borrados;
    private static final String filepath = "src\\txt\\contactos.txt";
    private static final String borrado = "src\\txt\\borrados.txt";
    
    

    public CRUD() {
        contacto = new ArrayList<>();
        borrados = new ArrayList<>();
        cargarContactosDesdeArchivo();
        cargarBorradosDesdeArchivo();
    }

    public void inicio(byte opcion) {
        switch (opcion) {
            case 5:
                /*ANTES DE SALIR SE GUARDA UN FICHERO DE TEXTO CON TODO LO QUE TENGA EL ARRAY LIST */
                guardarContactosEnArchivo();
                System.out.println("Saliendo...");
                break;
            case 1:
                Crear();
                break;
            case 2:
                Borrar();
                break;
            case 3:
                Actualizar();
                break;
            case 4:
                Leer();

        }
    }

    public void Crear() {

        System.out.println("Introduce nombre de contacto");
        String nombre = scanner.nextLine();
        System.out.println("Introduce telefono de contacto");
        String telefono = scanner.nextLine();
        System.out.println("Introduce el Email:");
        String email = scanner.nextLine();
        Contactos nuevoContacto = new Contactos(nombre, telefono, email);
        contacto.add(nuevoContacto);
         System.out.println("Contacto creado exitosamente");

    }

    public void Leer() {
        /*utilizar el GET(i) */
        System.out.println("Lista de contactos:");
        for (int i = 0; i < contacto.size(); i++) {
            Contactos c = contacto.get(i);
            System.out.println("Nombre: " + c.getNombre() + ", Teléfono: " + c.getTelefono() + ",Email:" + c.getEmail() + ",ID:" + c.getId());

        }
            System.out.println("--------------------------------------------------------;");
            System.out.println("Mostrando ArrayList de Borrados;");
            System.out.println(borrados);
    }

    public void Actualizar() {
        /*utilizar el SET(i) */
        System.out.println("Introduce el ID del contacto a actualizar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        boolean encontrado = false;
        for (Contactos c : contacto) {
            if (c.getId() == id) {
                encontrado = true;
                System.out.println("Introduce el nuevo nombre:");
                String nuevoNombre = scanner.nextLine();
                System.out.println("Introduce el nuevo teléfono:");
                String nuevoTelefono = scanner.nextLine();
                System.out.println("Introduce el nuevo Email:");
                String nuevoEmail = scanner.nextLine();
                c.setNombre(nuevoNombre);
                c.setTelefono(nuevoTelefono);
                c.setEmail(nuevoEmail);
                System.out.println("Contacto actualizado correctamente.");
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Contacto no encontrado.");
        }
    }

    public void Borrar() {
        try {
            FileWriter fw = new FileWriter(borrado, true);
            BufferedWriter bw = new BufferedWriter(fw);

            System.out.println("Introduce el id del contacto a borrar:");
            int idBorrar = scanner.nextInt();
            scanner.nextLine();
            boolean encontrado = false;
            for (int i = 0; i < contacto.size(); i++) {
                if (contacto.get(i).getId() == idBorrar) {
                    Contactos contactoBorrado = contacto.remove(i); // Eliminar el contacto y guardarlo
                    borrados.add(contactoBorrado);
                    bw.write(contactoBorrado.toString()); // Escribir el contacto borrado en el archivo
                    bw.newLine(); // Agregar una nueva línea después de cada contacto
                    System.out.println("Contacto borrado correctamente.");
                    encontrado = true;
                    break; // Salir del bucle una vez que se haya encontrado y eliminado el contacto
                }

            }

            if (!encontrado) {
                System.out.println("No se encontró ningún contacto con ese ID.");
            }

            bw.close();
            fw.close();

            System.out.println("--------------------------------------------------------;");
            System.out.println("Mostrando ArrayList de Borrados;");
            System.out.println(borrados);
            System.out.println("--------------------------------------------------------;");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarContactosEnArchivo() {
        try {
            FileWriter fw = new FileWriter(filepath);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Contactos c : contacto) {
                bw.write(c.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();

            System.out.println("Contactos guardados en 'contactos.txt'.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarContactosDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String nombre = parts[0].trim();
                String telefono = parts[1].trim();
                String email = parts[2].trim();
                Contactos nuevoContacto = new Contactos(nombre, telefono, email);
                contacto.add(nuevoContacto);
            }
            System.out.println("Contactos cargados desde 'contactos.txt'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void cargarBorradosDesdeArchivo() {
    try (BufferedReader br = new BufferedReader(new FileReader(borrado))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            String nombre = parts[0].trim();
            String telefono = parts[1].trim();
            String email = parts[2].trim();
            Contactos contactoBorrado = new Contactos(nombre, telefono, email);
            borrados.add(contactoBorrado);
        }
        System.out.println("Contactos borrados cargados desde 'borrados.txt'.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
