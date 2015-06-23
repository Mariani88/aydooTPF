package dominio;

public class Bicicleta {

	private Recorrido recorrido;
	private int id;
	
	public Bicicleta (int id, Recorrido recorrido){
		this.id = id;
		this.recorrido = recorrido;
	}

	public Recorrido getRecorrido() {
		return this.recorrido;
	}

	public int getId() {
		return this.id;
	}	
	
	
	public boolean equals (Object obj){
		
		boolean iguales = this == obj;
		
		if (!iguales && obj !=null && obj instanceof Bicicleta){
			
			Bicicleta obje = (Bicicleta)obj;
			iguales = this.id == obje.getId();
		}		
		
		return iguales;
	}
	
	
	public int hashCode (){	
		Integer entero = new Integer (this.id);
		return entero.hashCode();
	}
}