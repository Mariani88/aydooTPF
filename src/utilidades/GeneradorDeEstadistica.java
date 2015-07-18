package utilidades;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dominio.Bicicleta;
import dominio.InformacionEstadistica;
import dominio.Recorrido;

public class GeneradorDeEstadistica {

	private Map<Integer, Integer> historialUsoDeBicicletas;
	private Map<Ruta, Integer> historialUsoDeRecorridos;
	private InformacionEstadistica estadistica;
	private int tiempoTotal;
	private int bicicletasProcesadas;

	public GeneradorDeEstadistica() {
		this.historialUsoDeBicicletas = new HashMap<Integer, Integer>();
		this.historialUsoDeRecorridos = new HashMap<Ruta, Integer>();
		this.estadistica = new InformacionEstadistica();
		this.tiempoTotal = 0;
		this.bicicletasProcesadas = 0;
	}

	private void almacenarHistorialDeUso(Bicicleta bicicleta) {

		if (this.historialUsoDeBicicletas.containsKey(bicicleta.getId())) {
			int uso = this.historialUsoDeBicicletas.get(bicicleta.getId());
			this.historialUsoDeBicicletas.put(bicicleta.getId(), uso + 1);
		} else {
			this.historialUsoDeBicicletas.put(bicicleta.getId(), 1);
		}
	}

	public void generarDatosEstadisticos(List<Bicicleta> bicicletas) {

		Iterator<Bicicleta> iterador = bicicletas.iterator();

		while (iterador.hasNext()) {

			Bicicleta bicicleta = iterador.next();
			this.almacenarHistorialDeUso(bicicleta);
			this.almacenarHistorialDeRecorridos(bicicleta
					.getRecorrido());
			
			this.tiempoTotal += bicicleta.getRecorrido().getMinutosRecorridos();
			this.bicicletasProcesadas++;
		}
	}

	private void almacenarHistorialDeRecorridos(Recorrido recorrido) {

		Ruta recorridoDTO = recorrido.parsearARuta();

		if (this.historialUsoDeRecorridos.containsKey(recorridoDTO)) {
			int uso = this.historialUsoDeRecorridos.get(recorridoDTO);
			this.historialUsoDeRecorridos.put(recorridoDTO, uso + 1);
		} else {
			this.historialUsoDeRecorridos.put(recorridoDTO, 1);
		}

	}

	public InformacionEstadistica terminar() {

		InformacionEstadistica info = this.estadistica;	
		this.calcularEstadisticas ();
		this.reinicializarGenerador();
		
		return info;
	}

	private void reinicializarGenerador() {
		this.estadistica = new InformacionEstadistica ();
		this.bicicletasProcesadas = 0;
		this.tiempoTotal = 0;
		this.historialUsoDeBicicletas = new HashMap<Integer, Integer>();
		this.historialUsoDeRecorridos = new HashMap<Ruta, Integer>();
	}

	private void calcularEstadisticas() {
		
		this.estadistica.evaluarUsoBicicleta(this.historialUsoDeBicicletas);
		this.estadistica.evaluarRecorridos(this.historialUsoDeRecorridos);
		
		if (this.bicicletasProcesadas != 0 ){
			this.estadistica.setTiempoPromedio(this.tiempoTotal/this.bicicletasProcesadas);
		}
		
	}
}