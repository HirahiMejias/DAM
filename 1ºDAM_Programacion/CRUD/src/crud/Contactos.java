/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

/**
 *
 * @author Admin
 */
public class Contactos {
    private static int contadorID = 0;
    private String nombre;
    private String telefono;
    private String email;
    private int id;
    
    public Contactos(String nombre,String telefono,String email){
        this.nombre=nombre;
        this.telefono=telefono;
        this.email=email;
        this.id=++contadorID;
    }

    @Override
    public String toString() {
        return   nombre +',' + telefono+',' + email +',' + id ;
    }

    public static int getContadorID() {
        return contadorID;
    }

    public static void setContadorID(int contadorID) {
        Contactos.contadorID = contadorID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}





