package utilidades;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import dominio.Bicicleta;

public class LectorDeBicicletas {

	public List<Bicicleta> leer(InputStream stream) {
		List<Bicicleta> bicicletas = new ArrayList<Bicicleta>();
		validarStreamVacio(stream);

		try {
			bicicletas = generarBicicletas(stream);

			System.out.println("Termino");
		} catch (IOException | InterruptedException | ExecutionException e) {
			System.out.println("Error al generar la lista de bicicletas.");
		}

		return bicicletas;
	}

	private void validarStreamVacio(InputStream inputStream) {
		if (inputStream == null) {
			throw new IllegalArgumentException("InputStream esta vacio");
		}
	}

	private List<Bicicleta> generarBicicletas(InputStream inputStream) throws IOException, InterruptedException, ExecutionException {
		CharBuffer buffer = getCharBuffer(inputStream);

		int tamanioTotal = buffer.length();
		int procesadores = Runtime.getRuntime().availableProcessors();
		int calls = 16;
		int tamanioBucket = (int) ((buffer.length() + 0.5) / calls);

		List<Callable<List<Bicicleta>>> callables = generarCallablesPorBucket(buffer, tamanioTotal, tamanioBucket);

		ExecutorService executorService = Executors.newFixedThreadPool(procesadores * 2);
		List<Bicicleta> bicicletas = getBicicletas(callables, executorService);

		executorService.shutdown();

		return bicicletas;
	}

	private List<Callable<List<Bicicleta>>> generarCallablesPorBucket(CharBuffer buffer, int tamanioTotal, int tamanioBucket) {
		List<Callable<List<Bicicleta>>> callables = new ArrayList<Callable<List<Bicicleta>>>();
		for (int i = 0, hasta = 0; tamanioTotal > 0; ++i, tamanioTotal -= tamanioBucket) {
			int desde = Math.max(i * tamanioBucket, hasta);
			hasta = buscarSaltoDeLinea(desde + tamanioBucket, buffer, tamanioBucket / 8);
			generateCallablesByRange(buffer, callables, hasta, desde);
		}
		return callables;
	}

	private List<Bicicleta> getBicicletas(List<Callable<List<Bicicleta>>> callables, ExecutorService executorService) throws InterruptedException,
			ExecutionException {
		List<Future<List<Bicicleta>>> futures = executorService.invokeAll(callables);

		List<Bicicleta> bicicletas = new ArrayList<Bicicleta>();
		for (Future<List<Bicicleta>> future : futures) {
			List<Bicicleta> biciletasPorFuture = future.get();
			bicicletas.addAll(biciletasPorFuture);
		}
		return bicicletas;
	}

	private void generateCallablesByRange(CharBuffer decode, List<Callable<List<Bicicleta>>> callables, int to, int from) {
		CharBuffer subSequence = decode.subSequence(from, to);
		BufferedReader buffer = new BufferedReader(new StringReader(subSequence.toString()));
		Callable<List<Bicicleta>> callBuffer = new LectorDeBicicletasThread(buffer);
		callables.add(callBuffer);
	}

	private CharBuffer getCharBuffer(InputStream inputStream) throws IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		int bytesLeidos;
		byte[] datos = new byte[2048];

		while ((bytesLeidos = inputStream.read(datos, 0, datos.length)) != -1) {
			buffer.write(datos, 0, bytesLeidos);
		}

		buffer.flush();

		CharsetDecoder decodificacion = StandardCharsets.UTF_8.newDecoder();
		return decodificacion.decode(ByteBuffer.wrap(buffer.toByteArray()));
	}

	private int buscarSaltoDeLinea(int inicio, CharBuffer buffer, int delta) {
		String saltoDeLinea = System.lineSeparator();
		int limite = buffer.length() - 1;
		if (inicio >= limite)
			return limite;
		int umbral = Math.min(inicio + delta, limite);
		for (int i = umbral; i >= inicio; --i) {
			String saltoChar = Character.toString(buffer.charAt(i));
			if (saltoDeLinea.contains(saltoChar))
				return i;
		}
		return buscarSaltoDeLinea(umbral + 1, buffer, delta);
	}
}