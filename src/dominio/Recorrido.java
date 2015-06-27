package dominio;

import utilidades.Ruta;

public class Recorrido {

	private Estacion estacionOrigen;
	private Estacion estacionDestino;
	private int minutosRecorridos;
	
	public Recorrido (Estacion origen, Estacion destino){
		this.estacionDestino = destino;
		this.estacionOrigen = origen;
		this.minutosRecorridos = 0;		
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

	public boolean equals(Object obj) {

		boolean iguales = this == obj;

		if (!iguales && obj != null && obj instanceof Recorrido) {
			Recorrido obje = (Recorrido) obj;

			iguales = this.estacionOrigen.equals(obje.getEstacionOrigen())
					&& this.estacionDestino.equals(obje.getEstacionDestino());
		}

		return iguales;
	}
	
	
	
	public  Ruta parsearARuta() {
		
		int origen = this.estacionOrigen.getId();
		int destino =this.estacionDestino.getId();
		
		return new Ruta (origen, destino);
	}
	
	public int hashCode (){
		
		return this.estacionOrigen.getId();
	}
}