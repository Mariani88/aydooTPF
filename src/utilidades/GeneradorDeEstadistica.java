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
	private Map<RecorridoDTO, Integer> historialUsoDeRecorridos;
	private InformacionEstadistica estadistica;
	private int tiempoTotal;
	private int bicicletasProcesadas;

	public GeneradorDeEstadistica() {
		this.historialUsoDeBicicletas = new HashMap<Integer, Integer>();
		this.historialUsoDeRecorridos = new HashMap<RecorridoDTO, Integer>();
		this.estadistica = new InformacionEstadistica();
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
			this.almacenarHistorialDeRecorridosYEvaluar(bicicleta
					.getRecorrido());
			this.actualizarPromedioDeUso(bicicleta);
		}
	}

	private void actualizarPromedioDeUso(Bicicleta bicicleta) {

		this.tiempoTotal += bicicleta.getRecorrido().getMinutosRecorridos();
		this.bicicletasProcesadas++;
		int tiempoPromedio = this.tiempoTotal / this.bicicletasProcesadas;

		this.estadistica.setTiempoPromedio(tiempoPromedio);
	}

	private void almacenarHistorialDeRecorridosYEvaluar(Recorrido recorrido) {

		RecorridoDTO recorridoDTO = Recorrido.parsearADTO(recorrido);

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

		return this.estadistica;
	}
}