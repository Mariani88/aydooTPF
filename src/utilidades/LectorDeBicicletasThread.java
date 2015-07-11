package utilidades;

import java.io.BufferedReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import dominio.Bicicleta;
import dominio.Estacion;
import dominio.Recorrido;

public class LectorDeBicicletasThread implements Callable<List<Bicicleta>> {

	private BufferedReader bufferedReader;

	public LectorDeBicicletasThread(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	@Override
	public List<Bicicleta> call() throws Exception {
		List<Bicicleta> bicicletas = new ArrayList<Bicicleta>();

		bufferedReader.readLine();

		for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
			Bicicleta bicicleta = generarBicicleta(line);
			bicicletas.add(bicicleta);
		}

		bufferedReader.close();

		return bicicletas;
	}

	private Bicicleta generarBicicleta(String registroLeido) throws ParseException {
		String[] campos = registroLeido.split(";");

		Integer bicicletaId = Integer.parseInt(campos[1]);
		Integer estacionOrigenId = Integer.parseInt(campos[3]);
		String estacionOrigenNombre = campos[4];
		Integer estacionDestinoId = Integer.parseInt(campos[6]);
		String estacionDestinoNombre = campos[7];
		Integer minutosRecorridos = 0;
		if (campos.length == 9) {
			minutosRecorridos = Integer.parseInt(campos[8]);
		}
		Estacion estacionOrigen = new Estacion(estacionOrigenId, estacionOrigenNombre);
		Estacion estacionDestino = new Estacion(estacionDestinoId, estacionDestinoNombre);
		Recorrido recorrido = new Recorrido(estacionOrigen, estacionDestino);
		recorrido.setMinutosRecorridos(minutosRecorridos);
		Bicicleta bicicleta = new Bicicleta(bicicletaId, recorrido);

		return bicicleta;
	}
}