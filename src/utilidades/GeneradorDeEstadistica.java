package utilidades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dominio.Bicicleta;
import dominio.InformacionEstadistica;

public class GeneradorDeEstadistica {

	private Map <Integer, Integer> historialUsoDeBicicletas;
	private Map <RecorridoDTO, Integer> historialUsoDeRecorridos;
	private InformacionEstadistica estadistica; 
	
	public GeneradorDeEstadistica (){
		this.historialUsoDeBicicletas = new HashMap <Integer, Integer> ();
		this.historialUsoDeRecorridos = new HashMap <RecorridoDTO, Integer> ();
		this.estadistica = new InformacionEstadistica ();
	}

	public void generarEstadistica(List<Bicicleta> bicicletas) {
		
		
	}

	public InformacionEstadistica terminar() {
		
		return this.estadistica;
	}
	
	
	
	
	
	
	
}