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
}