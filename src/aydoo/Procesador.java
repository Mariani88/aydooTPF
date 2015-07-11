package aydoo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipFile;

import utilidades.GeneradorDeEstadistica;
import utilidades.GestorDeArchivos;
import utilidades.MonitorDeDirectorio;
import dominio.Bicicleta;
import dominio.InformacionEstadistica;

public class Procesador {

	private static MonitorDeDirectorio monitorDeDirectorio;
	private static GeneradorDeEstadistica generadorDeEstadisticas;
	private static GestorDeArchivos gestorDeArchivos;
	private static String directorioDeTrabajo = null;

	public static void main(String args[]) throws IOException {
		directorioDeTrabajo = args[0];
		gestorDeArchivos = new GestorDeArchivos();

		if (args.length == 2 && args[1].equals("demonio")) {
			evaluarDirectorioDeTrabajo();
			monitorDeDirectorio = new MonitorDeDirectorio(directorioDeTrabajo);
			monitorDeDirectorio.monitorear();
		} else {
			evaluarDirectorioDeTrabajo();
			comenzarProcesamiento();
		}
	}

	private static void evaluarDirectorioDeTrabajo() {

		boolean configuracionCorrecta = comprobarExistenciaDeDirectorio(directorioDeTrabajo);
		Scanner escaner = new Scanner(System.in);

		while (!configuracionCorrecta) {
			System.out.print("Ingrese directorio:");
			directorioDeTrabajo = escaner.next();
			configuracionCorrecta = comprobarExistenciaDeDirectorio(directorioDeTrabajo);
		}

		gestorDeArchivos = new GestorDeArchivos();
		escaner.close();
	}

	private static boolean comprobarExistenciaDeDirectorio(String directorioDeTrabajo) {

		File directorioAEvaluar = new File(directorioDeTrabajo);
		boolean existe = directorioAEvaluar.exists();

		if (!existe) {
			System.out.println("No existe el directorio.");
		}

		return existe;
	}

	private static void comenzarProcesamiento() {
		generadorDeEstadisticas = new GeneradorDeEstadistica();

		try {
			ZipFile[] archivosZip = gestorDeArchivos.obtenerArchivosZip(directorioDeTrabajo);

			long startTime = System.currentTimeMillis();

			for (int i = 0; i < archivosZip.length; i++) {
				gestorDeArchivos.asignarArchivoZipParaProcesar(archivosZip[i]);
				List<Bicicleta> bicicletas = gestorDeArchivos.obtenerListaDeBicicletas();

				while (bicicletas.size() != 0) {
					generadorDeEstadisticas.generarEstadistica(bicicletas);
					bicicletas = gestorDeArchivos.obtenerListaDeBicicletas();
				}
				gestorDeArchivos.moverZipAProcesados(archivosZip[i]);
			}

			long endTime = System.currentTimeMillis();
			String tiempoTotalDeProcesamiento = tiempoTotalDeProcesamiento(endTime - startTime);

			System.out.println(tiempoTotalDeProcesamiento);

			InformacionEstadistica estadisticas = generadorDeEstadisticas.terminar();
			estadisticas.setTiempoDeProcesamiento(tiempoTotalDeProcesamiento);

			gestorDeArchivos.crearYMLCon(estadisticas, directorioDeTrabajo + "/salida");
			System.out.println("archivos procesados");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String tiempoTotalDeProcesamiento(long totalTime) {
		String result = String.format("Tiempo de procesamiento: %d.%d seconds",
				TimeUnit.MILLISECONDS.toSeconds(totalTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totalTime)),
				TimeUnit.MILLISECONDS.toMillis(totalTime));

		return result;
	}
}