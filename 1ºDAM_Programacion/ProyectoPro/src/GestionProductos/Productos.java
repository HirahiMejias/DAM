/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionProductos;

/**
 *
 * @author Admin
 */
public class Productos {

    private int IdProducto;
    private String Descripcion;
    private int ean13;
    private String keyRFID;

    public Productos(int idProducto, String descripcion, int ean13, String keyRFID) {
        this.IdProducto = idProducto;
        this.Descripcion = descripcion;
        this.ean13 = ean13;
        this.keyRFID = keyRFID;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProdcuto(int idProducto) {
        this.IdProducto = idProducto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    public int getEan13() {
        return ean13;
    }

    public void setEan13(int ean13) {
        this.ean13 = ean13;
    }

    public String getKeyRFID() {
        return keyRFID;
    }

    public void setKeyRFID(String keyRFID) {
        this.keyRFID = keyRFID;
    }

    @Override
    public String toString() {
        return "Producto: "
                + "IdProducto= " + IdProducto
                + ", Descripcion= " + Descripcion
                + ", EAN13= " + ean13
                + ", keyRFID= " + keyRFID;
    }
}
