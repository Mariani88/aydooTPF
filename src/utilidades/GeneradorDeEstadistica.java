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
	private Map<Bicicleta, Integer> tiempoDeUsoDeBicicletas;
	private InformacionEstadistica estadistica;
	private int tiempoTotal;
	private int bicicletasProcesadas;

	public GeneradorDeEstadistica() {
		this.historialUsoDeBicicletas = new HashMap<Integer, Integer>();
		this.historialUsoDeRecorridos = new HashMap<Ruta, Integer>();
		this.estadistica = new InformacionEstadistica();
		this.tiempoDeUsoDeBicicletas = new HashMap<Bicicleta, Integer>();
		this.tiempoTotal = 0;
		this.bicicletasProcesadas = 0;
	}

	private void almacenarHistorialDeUsoYEvaluar(Bicicleta bicicleta) {

		if (this.historialUsoDeBicicletas.containsKey(bicicleta.getId())) {
			int uso = this.historialUsoDeBicicletas.get(bicicleta.getId());
			this.historialUsoDeBicicletas.put(bicicleta.getId(), uso + 1);
			this.estadistica.evaluarUsoBicicleta(bicicleta.getId(), uso + 1);
		} else {
			this.historialUsoDeBicicletas.put(bicicleta.getId(), 1);
			this.estadistica.evaluarUsoBicicleta(bicicleta.getId(), 1);
		}
	}

	public void generarEstadistica(List<Bicicleta> bicicletas) {

		Iterator<Bicicleta> iterador = bicicletas.iterator();

		while (iterador.hasNext()) {

			Bicicleta bicicleta = iterador.next();
			this.almacenarHistorialDeUsoYEvaluar(bicicleta);
			this.almacenarHistorialDeRecorridosYEvaluar(bicicleta.getRecorrido());
			this.actualizarPromedioDeUso(bicicleta);
			this.almacenarTiempoDeUsoDeBicicletas(bicicleta);
		}
		estadistica.generarBicicletasUsadasMasTiempo(tiempoDeUsoDeBicicletas);
		Integer tiempo = this.calcularTiempoDeBicicletaMasUsada(tiempoDeUsoDeBicicletas);
		estadistica.setTiempoDeBicicletaMasUsada(tiempo);

	}

	private Integer calcularTiempoDeBicicletaMasUsada(Map<Bicicleta, Integer> tiempoDeUsoDeBicicletas) {
		Integer tiempoMayor = 0;
		Integer tiempoDeBiciActual = 0;
		List<Integer> bicisId = estadistica.bicicletasMasUsadas();
		for (Integer i : bicisId) {
			Bicicleta bicicletaMasUsada = new Bicicleta(i, null);
			tiempoDeBiciActual = tiempoDeUsoDeBicicletas.get(bicicletaMasUsada);
			if (tiempoDeBiciActual > tiempoMayor) {
				tiempoMayor = tiempoDeBiciActual;
			}
		}
		return tiempoMayor;
	}

	private void almacenarTiempoDeUsoDeBicicletas(Bicicleta bicicleta) {
		Integer tiempo = bicicleta.getRecorrido().getMinutosRecorridos();
		if (tiempoDeUsoDeBicicletas.containsKey(bicicleta)) {
			tiempo = tiempo + tiempoDeUsoDeBicicletas.get(bicicleta);
		}
		tiempoDeUsoDeBicicletas.put(bicicleta, tiempo);
	}

	private void actualizarPromedioDeUso(Bicicleta bicicleta) {

		this.tiempoTotal += bicicleta.getRecorrido().getMinutosRecorridos();
		this.bicicletasProcesadas++;
		int tiempoPromedio = this.tiempoTotal / this.bicicletasProcesadas;

		this.estadistica.setTiempoPromedio(tiempoPromedio);
	}

	private void almacenarHistorialDeRecorridosYEvaluar(Recorrido recorrido) {

		Ruta recorridoDTO = recorrido.parsearARuta();

		if (this.historialUsoDeRecorridos.containsKey(recorridoDTO)) {
			int uso = this.historialUsoDeRecorridos.get(recorridoDTO);
			this.historialUsoDeRecorridos.put(recorridoDTO, uso + 1);
			this.estadistica.evaluarRecorrido(recorridoDTO, uso + 1);
		} else {
			this.historialUsoDeRecorridos.put(recorridoDTO, 1);
			this.estadistica.evaluarRecorrido(recorridoDTO, 1);
		}

	}

	public InformacionEstadistica terminar() {

		InformacionEstadistica info = this.estadistica;
		this.estadistica = new InformacionEstadistica();
		this.bicicletasProcesadas = 0;
		this.tiempoTotal = 0;
		this.historialUsoDeBicicletas = new HashMap<Integer, Integer>();
		this.historialUsoDeRecorridos = new HashMap<Ruta, Integer>();
		this.tiempoDeUsoDeBicicletas = new HashMap<Bicicleta, Integer>();

		return info;
	}
}