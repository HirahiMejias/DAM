import java.io.IOException;
import java.io.RandomAccessFile;

public class Cancion {
    private String titulo;
    private String disco;
    private String autor;
    private boolean esFavorita;

    public Cancion(String titulo, String disco, String autor, boolean esFavorita) {
        this.titulo = titulo;
        this.disco = disco;
        this.autor = autor;
        this.esFavorita = esFavorita;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isEsFavorita() {
        return esFavorita;
    }

    public void setEsFavorita(boolean esFavorita) {
        this.esFavorita = esFavorita;
    }

    public void escribirEnFichero(RandomAccessFile fichero) throws IOException {
        fichero.writeUTF(titulo);
        fichero.writeUTF(disco);
        fichero.writeUTF(autor);
        fichero.writeBoolean(esFavorita);
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "titulo='" + titulo + '\'' +
                ", disco='" + disco + '\'' +
                ", autor='" + autor + '\'' +
                ", esFavorita=" + esFavorita +
                '}';
    }
}
