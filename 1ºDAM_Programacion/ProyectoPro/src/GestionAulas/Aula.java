/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionAulas;

/**
 *
 * @author 1dama
 */
public class Aula {
    private int idAula;          // Autonumérico entero
    private String numeracion;   // Formato: 2.2.2 Pabellón.Piso.Aula
    private String descripcion;  // Descripción del aula
    private String ip;           // Dirección IP

    // Constructor
    public Aula(int idAula, String numeracion, String descripcion, String ip) {
        this.idAula = idAula;
        this.numeracion = numeracion;
        this.descripcion = descripcion;
        this.ip = ip;
    }

    // Getters
    public int getIdAula() {
        return idAula;
    }

    public String getNumeracion() {
        return numeracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getIp() {
        return ip;
    }

    // Setters
    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public void setNumeracion(String numeracion) {
        this.numeracion = numeracion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "idAula=" + idAula +
                ", numeracion='" + numeracion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
