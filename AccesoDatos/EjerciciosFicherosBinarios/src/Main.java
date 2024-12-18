import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Ejercicio1.ejercicio1();
        Ejercicio2.ejercicio2();

        ArrayList<Cliente> listaClientes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Pedir datos de clientes por consola
        for (int i = 0; i < 3; i++) { // Cambia el número de clientes si lo deseas
            System.out.println("Introduce los datos del cliente " + (i + 1) + ":");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Dirección: ");
            String direccion = scanner.nextLine();
            System.out.print("Número de cliente: ");
            int numeroCliente = Integer.parseInt(scanner.nextLine());
            System.out.print("Saldo: ");
            double saldo = Double.parseDouble(scanner.nextLine());
            System.out.print("Cuenta (al día, atrasada, deudor): ");
            String cuenta = scanner.nextLine();
            listaClientes.add(new Cliente(nombre, direccion, numeroCliente, saldo, cuenta));
        }

        // Escribir clientes en el archivo
        File fichero = new File("clientes.dat");
        boolean resultado = Ejercicio3.escribirClientes(listaClientes, fichero);
        if (resultado) {
            System.out.println("Clientes escritos correctamente.");
        }

        // Leer y mostrar clientes morosos
        ArrayList<Cliente> morosos = Ejercicio3.devolverMorosos(fichero);
        if (!morosos.isEmpty()) {
            System.out.println("Clientes deudores:");
            for (Cliente moroso : morosos) {
                System.out.println("Nombre: " + moroso.getNombre());
            }
        } else {
            System.out.println("No hay clientes deudores.");
        }

        scanner.close();
    }
}
