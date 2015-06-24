package dominio;

import java.util.Date;

import utilidades.RecorridoDTO;

public class Recorrido {

	private Date fechaOrigen;
	private Date fechaDestino;
	private Estacion estacionOrigen;
	private Estacion estacionDestino;
	private int minutosRecorridos;
	
	public Recorrido (Estacion origen, Estacion destino){
		this.estacionDestino = destino;
		this.estacionOrigen = origen;
		this.minutosRecorridos = 0;		
	}

	public Date getFechaOrigen() {
		return this.fechaOrigen;
	}

	public void setFechaOrigen(Date fechaOrigen) {
		this.fechaOrigen = fechaOrigen;
	}

	public Date getFechaDestino() {
		return this.fechaDestino;
	}

	public void setFechaDestino(Date fechaDestino) {
		this.fechaDestino = fechaDestino;
	}

	public int getMinutosRecorridos() {
		return this.minutosRecorridos;
	}

	public void setMinutosRecorridos(int minutosRecorridos) {
		this.minutosRecorridos = minutosRecorridos;
	}

	public Estacion getEstacionOrigen() {
		return this.estacionOrigen;
	}

	public Estacion getEstacionDestino() {
		return this.estacionDestino;
	}

	public static RecorridoDTO parsearADTO(Recorrido recorrido) {
		
		int origen = recorrido.getEstacionOrigen().getId();
		int destino = recorrido.getEstacionDestino().getId();
		
		return new RecorridoDTO (origen, destino);
	}
}
