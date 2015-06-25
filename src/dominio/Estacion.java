package dominio;

public class Estacion {

	private int id;
	private String nombre;

	public Estacion(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public boolean equals(Object obj) {

		boolean iguales = this == obj;

		if (!iguales && obj != null && obj instanceof Estacion) {
			Estacion obje = (Estacion) obj;
			iguales = this.id == obje.getId();
		}

		return iguales;
	}
	
	
	public int hashCode (){
		
		return this.id;
	}
}