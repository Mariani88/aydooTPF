package utilidades;

public class RecorridoDTO {

	private Integer idEstacionOrigen;
	private Integer idEstacionDestino;
	
	public RecorridoDTO (int idEstacionOrigen, int idEstacionDestino){
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
		
		if (!iguales && obj != null && obj instanceof RecorridoDTO) {

			RecorridoDTO obje = (RecorridoDTO) obj;
			iguales = this.idEstacionDestino == obje.getIdEstacionDestino()
					&& this.idEstacionOrigen == obje.getIdEstacionOrigen();
		}
		
		return iguales;
	}
	
	public int hashCode (){
		
		return this.idEstacionDestino.hashCode()
				+ this.idEstacionDestino.hashCode();
	}

}

