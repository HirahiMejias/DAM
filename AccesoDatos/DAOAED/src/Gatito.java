public class Gatito {
    private String nombre;
    private int edad;
    private boolean esAlergico;

    // Constructor
    public Gatito(String nombre, int edad, boolean esAlergico) {
        this.nombre = nombre;
        this.edad = edad;
        this.esAlergico = esAlergico;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isEsAlergico() {
        return esAlergico;
    }

    public void setEsAlergico(boolean esAlergico) {
        this.esAlergico = esAlergico;
    }

    // toString() para depuración
    @Override
    public String toString() {
        return "Gatito{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", esAlergico=" + esAlergico +
                '}';
    }
}
