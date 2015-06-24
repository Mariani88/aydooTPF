package utilidades;

import java.util.HashMap;
import java.util.Iterator;
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
		
		Iterator <Bicicleta> iterador = bicicletas.iterator();
		
		while (iterador.hasNext()) {

			Bicicleta bicicleta = iterador.next();

			if (this.historialUsoDeBicicletas.containsKey(bicicleta.getId())) {		
				int uso = this.historialUsoDeBicicletas.get(bicicleta.getId());
				this.historialUsoDeBicicletas.put(bicicleta.getId(), uso + 1);
				this.estadistica.evaluarUsoBicicleta(bicicleta.getId(), uso + 1);
			}else{
				this.historialUsoDeBicicletas.put(bicicleta.getId(), 1);
				this.estadistica.evaluarUsoBicicleta(bicicleta.getId(), 1);
			}
		}	
	}

	public InformacionEstadistica terminar() {
		
		return this.estadistica;
	}
	
	
	
	
	
	
	
}