import java.util.ArrayList;
import java.util.List;

public class Musico {

	private String nombre;
	private int formacion;
	private String genero;
	private String frontman;
	private String discografia;
	private String paisOrigen;
	private ArrayList <String> discos;
	
	public Musico(String nombre, int formacion, String genero, String frontman, String discografia, String paisOrigen, ArrayList <String> discos) {
		super();
		this.nombre = nombre;
		this.formacion = formacion;
		this.genero = genero;
		this.frontman = frontman;
		this.discografia = discografia;
		this.paisOrigen = paisOrigen;
		this.discos = discos;
	}

	public ArrayList<String> getDiscos() {
		return discos;
	}

	public void setDiscos(ArrayList<String> discos) {
		this.discos = discos;
	}

	public String getPaisOrigen() {
		return paisOrigen;
	}

	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	public String getDiscografia() {
		return discografia;
	}

	public void setDiscografia(String discografia) {
		this.discografia = discografia;
	}

	public String getFrontman() {
		return frontman;
	}

	public void setFrontman(String frontman) {
		this.frontman = frontman;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getFormacion() {
		return formacion;
	}
	public void setFormacion(int formacion) {
		this.formacion = formacion;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	@Override
	public String toString() {
		return "El grupo se llama " + nombre + " formado en " + formacion + " y su estilo musical es " + genero;
	}
	
}