package dominio;

public class Bicicleta implements Comparable<Object> {

	private Recorrido recorrido;
	private Integer id;
	
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
		
		return this.id;
	}

	@Override
	public int compareTo(Object o) {
		Bicicleta bici = (Bicicleta)o;
		return this.id.compareTo(bici.getId());
	}

}