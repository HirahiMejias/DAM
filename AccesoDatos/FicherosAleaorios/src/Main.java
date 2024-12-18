import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        RandomAccessFile dos = new RandomAccessFile("src/Random1.dat", "rw");

        while (n != -1) {
            System.out.println("Inserte números enteros:");
            System.out.println("-1. Salir");
            n = sc.nextInt();
            dos.writeInt(n);
        }

        leerFichero();

        System.out.println("Ingresa la posición del fichero que quieras obtener:");
        int posicion = sc.nextInt();
        leerNumero(posicion);

        ejercicioModificarFichero();
        escribirCancionesEnFichero();
    }

    public static void leerFichero() throws IOException {
        RandomAccessFile fichero = new RandomAccessFile("src/Random1.dat", "r");

        int numero = (int) (fichero.length() / 4);

        for (int i = 0; i < numero; i++) {
            numero = fichero.readInt();
            System.out.println(numero);
        }
        fichero.close();
    }

    public static void leerNumero(int posicion) throws IOException {
        RandomAccessFile fichero = new RandomAccessFile("src/Random1.dat", "r");
        int posBuscado = 4 * posicion;
        fichero.seek(posBuscado);
        int numero = fichero.readInt();
        System.out.println(numero);
        fichero.close();
    }

    public static void ejercicioModificarFichero() throws IOException {
        try (RandomAccessFile fichero = new RandomAccessFile("src/Random1.dat", "rw")) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Escribe la posición que quieres modificar:");
            int posicion = sc.nextInt();

            long fileLength = fichero.length();
            int posBuscado = 4 * posicion;

            if (posBuscado >= fileLength) {
                System.out.println("La posición solicitada está fuera del rango del archivo.");
                return;
            }

            fichero.seek(posBuscado);
            int valorActual = fichero.readInt();

            System.out.println("El valor actual en la posición " + posicion + " es: " + valorActual);
            System.out.println("Escribe el nuevo valor:");
            int nuevoValor = sc.nextInt();

            fichero.seek(posBuscado);
            fichero.writeInt(nuevoValor);

            System.out.println("El valor actual en la posición " + posicion + " es: " + nuevoValor);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void escribirCancionesEnFichero() throws IOException {
        try (RandomAccessFile fichero = new RandomAccessFile("canciones.bin", "rw")) {
            Cancion cancion1 = new Cancion("Cancion1", "Disco1", "Autor1", true);
            Cancion cancion2 = new Cancion("Cancion2", "Disco2", "Autor2", false);
            Cancion cancion3 = new Cancion("Cancion3", "Disco3", "Autor3", true);
            Cancion cancion4 = new Cancion("Cancion4", "Disco4", "Autor4", false);
            Cancion cancion5 = new Cancion("Cancion5", "Disco5", "Autor5", true);

            cancion1.escribirEnFichero(fichero);
            cancion2.escribirEnFichero(fichero);
            cancion3.escribirEnFichero(fichero);
            cancion4.escribirEnFichero(fichero);
            cancion5.escribirEnFichero(fichero);

            System.out.println("Las canciones se han escrito en el fichero.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
