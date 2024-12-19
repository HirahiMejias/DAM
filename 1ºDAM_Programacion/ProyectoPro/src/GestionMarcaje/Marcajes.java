/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionMarcaje;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Marcajes {

    private int idMarcaje;
    private int idProducto;
    private int idAula;
    private int ipo;
    private Date timeStamp;

    public Marcajes(int idMarcaje, int idProducto, int idAula, int ipo, Date timeStamp) {
        this.idMarcaje = idMarcaje;
        this.idProducto = idProducto;
        this.idAula = idAula;
        this.ipo = ipo;
        this.timeStamp = timeStamp;
    }

    public int getIdMarcaje() {
        return idMarcaje;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getIdAula() {
        return idAula;
    }

    public int getIpo() {
        return ipo;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "Marcajes{" + "idMarcaje=" + idMarcaje + ", idProducto=" + idProducto + ", idAula=" + idAula + ", ipo=" + ipo + ", timeStamp=" + timeStamp + '}';
    }

    
    
}
