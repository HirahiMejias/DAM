/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import java.util.Scanner;

/**
 *
 * @author 1dama
 */
public class Menu {
    
   private byte opcion;
   CRUD crud = new CRUD();
      
    public void MostrarMenu() {
        while(opcion!=5){
        Scanner teclado = new Scanner(System.in);
        System.out.println("-------------------------------------");
        System.out.println("1.Crear");
        System.out.println("2.Borrar");
        System.out.println("3.Actualizar");
        System.out.println("4.Leer Plantilla");
        System.out.println("5.Salir");
        System.out.println("-------------------------------------");
        System.out.println("Introduzca la opcion que desee: ");
        System.out.println("-------------------------------------");
        opcion = teclado.nextByte();
        crud.inicio(opcion);
        }
    }
}

