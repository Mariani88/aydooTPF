package dominio;

public class Estacion {

	private int id;
	private String nombre;
	
	public Estacion (int id, String nombre){
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}	
}