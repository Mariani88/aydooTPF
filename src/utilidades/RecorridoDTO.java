package utilidades;

public class RecorridoDTO {

	private int idEstacionOrigen;
	private int idEstacionDestino;
	
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
}


