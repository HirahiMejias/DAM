import java.io.*;

public class Ejercicio2 {
    public static void ejercicio2() {
        DataInputStream lector = null;
        try {
            lector = new DataInputStream(new FileInputStream("src/numerosArmstrong.dat"));

            while (true) {
                System.out.println(lector.readInt());  // Lee enteros del fichero
            }

        } catch (EOFException e) {
            System.out.println("Fin del fichero");
            try {
                if (lector != null) {
                    lector.close();  // Cierra el lector
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
