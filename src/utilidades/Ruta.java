package utilidades;

public class Ruta {

	private Integer idEstacionOrigen;
	private Integer idEstacionDestino;
	
	public Ruta (int idEstacionOrigen, int idEstacionDestino){
		this.idEstacionDestino = idEstacionDestino;
		this.idEstacionOrigen = idEstacionOrigen;
	}

	public int getIdEstacionOrigen() {
		return this.idEstacionOrigen;
	}

	public int getIdEstacionDestino() {
		return this.idEstacionDestino;
	}
	
	public boolean equals (Object obj){
		
		boolean iguales = this == obj;
		
		if (!iguales && obj != null && obj instanceof Ruta) {

			Ruta obje = (Ruta) obj;
			iguales = this.idEstacionDestino == obje.getIdEstacionDestino()
					&& this.idEstacionOrigen == obje.getIdEstacionOrigen();
		}
		
		return iguales;
	}
	
	public int hashCode (){
		
		return this.idEstacionOrigen.hashCode();
	}
}