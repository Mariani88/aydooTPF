package utilidades.tareasDeMonitor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.TimerTask;
import java.util.zip.ZipFile;

import utilidades.GeneradorDeEstadistica;
import utilidades.GestorDeArchivos;
import dominio.Bicicleta;
import dominio.InformacionEstadistica;

public class TareaProcesarZips extends TimerTask {

	private GestorDeArchivos gestorDeArchivos = new GestorDeArchivos();
	private String directorioDeTrabajo;

	public TareaProcesarZips(String directorioDeTrabajo) {
		this.directorioDeTrabajo = directorioDeTrabajo;
	}

	@Override
	public void run() {
		GeneradorDeEstadistica generadorDeEstadisticas = new GeneradorDeEstadistica();

		try {
			ZipFile[] archivosZip = gestorDeArchivos.obtenerArchivosZip(directorioDeTrabajo);

			for (int i = 0; i < archivosZip.length; i++) {
				gestorDeArchivos.asignarArchivoZipParaProcesar(archivosZip[i]);

				List<Bicicleta> bicicletas = gestorDeArchivos.obtenerListaDeBicicletas();
				float porcentaje = (float) (i + 1) * 100 / archivosZip.length;
				System.out.println("procesando archivos, espere..." + porcentaje + "%");

				while (bicicletas.size() != 0) { // leo todos los CSV del zip
					generadorDeEstadisticas.generarEstadistica(bicicletas);
					bicicletas = gestorDeArchivos.obtenerListaDeBicicletas();
				}

				InformacionEstadistica estadisticas = generadorDeEstadisticas.terminar();

				String nombreYML = this.obtenerNombreArchivo(archivosZip[i]);
				gestorDeArchivos.crearYMLCon(estadisticas, directorioDeTrabajo + "/salida", nombreYML);
				gestorDeArchivos.moverZipAProcesados(archivosZip[i]);
				System.out.println("archivos procesados:" + (i + 1));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String obtenerNombreArchivo(ZipFile zipFile) {

		File archivo = new File(zipFile.getName());
		return archivo.getName();
	}
}