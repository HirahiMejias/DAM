import java.io.*;

//Ejercicio 1 Escribe un programa que guarde en un fichero con nombre numerosArmstrong.dat los
//números de entre 1 y 1000 que sean números de Armstrong. Un número de Armstrong es aquel que es igual a la suma
//de sus propios dígitos elevados a la potencia de la cantidad de dígitos.
//Por ejemplo, 370 es un número de Armstrong ya que 370 = 3^3 + 7 ^3 + 0^3.
public class Ejercicio1 {
    public static void ejercicio1() {

        int sumaTotal = 0;
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/numerosArmstrong.dat")))  {
            for (int i = 1; i < 1000; i++) {
                String s = Integer.toString(i);
                int Nch = s.length();
                sumaTotal = 0;
                // este bucle recorre los numeros
                for (int j = 0; j < Nch; j++) {
                    int numeroElevado = (int) Math.pow(Character.getNumericValue(s.charAt(j)), Nch);
                    sumaTotal += numeroElevado;
                }
                if (sumaTotal == i) {
                    dos.writeInt(sumaTotal);
                    System.out.println("El número es " + sumaTotal);
                }

            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }

    }
}
