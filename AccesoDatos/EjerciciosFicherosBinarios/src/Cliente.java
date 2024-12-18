import java.io.Serializable;

public class Cliente implements Serializable {
    String nombre;
    String Direccion ;
    int id;
    Double saldo;
    String cuenta;

    public Cliente(String nombre, String direccion, int id, Double saldo, String cuenta) {
        this.nombre = nombre;
        Direccion = direccion;
        this.id = id;
        this.saldo = saldo;
        this.cuenta = cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
}
