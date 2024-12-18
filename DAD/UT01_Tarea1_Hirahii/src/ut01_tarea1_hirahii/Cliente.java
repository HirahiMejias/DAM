/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut01_tarea1_hirahii;

/**
 *
 * @author 2dama
 */
public class Cliente {
    String NombreCentro;
    int NComensales;
    String ListaServicios;
    boolean CBalergias;
    String ObsArea;

    public Cliente(String NombreCentro, int NComensales, String ListaServicios, boolean CBalergias, String ObsArea) {
        this.NombreCentro = NombreCentro;
        this.NComensales = NComensales;
        this.ListaServicios = ListaServicios;
        this.CBalergias = CBalergias;
        this.ObsArea = ObsArea;
    }

    public String getNombreCentro() {
        return NombreCentro;
    }

    public void setNombreCentro(String NombreCentro) {
        this.NombreCentro = NombreCentro;
    }

    public int getNComensales() {
        return NComensales;
    }

    public void setNComensales(int NComensales) {
        this.NComensales = NComensales;
    }

    public String getListaServicios() {
        return ListaServicios;
    }

    public void setListaServicios(String ListaServicios) {
        this.ListaServicios = ListaServicios;
    }

    public boolean isCBalergias() {
        return CBalergias;
    }

    public void setCBalergias(boolean CBalergias) {
        this.CBalergias = CBalergias;
    }

    public String getObsArea() {
        return ObsArea;
    }

    @Override
    public String toString() {
        return "Cliente{" + "ObsArea=" + ObsArea + '}';
    }

    public void setObsArea(String ObsArea) {
        this.ObsArea = ObsArea;
    }
    
}
