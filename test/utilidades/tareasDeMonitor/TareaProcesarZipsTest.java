package utilidades.tareasDeMonitor;

import java.io.File;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class TareaProcesarZipsTest {

	private static final String PATH_ARCHIVOS_ZIP = "paraTestear/archivosZipParaTest";

	@Test
	@Ignore("El metodo que se prueba solo se usa para test.")
	public void runDebeProcesarCadaArchivoPorSeparado() {

		TareaProcesarZips tarea = new TareaProcesarZips(PATH_ARCHIVOS_ZIP);
		tarea.run();

		File archivoYML1 = new File(PATH_ARCHIVOS_ZIP + "/salida" + "/recorridos-2010.zip.yml");
		File archivoYML2 = new File(PATH_ARCHIVOS_ZIP + "/salida" + "/recorridos-2012.zip.yml");
		File archivoYML3 = new File(PATH_ARCHIVOS_ZIP + "/salida" + "/recorridos-2013.zip.yml");

		Assert.assertTrue(archivoYML1.exists());
		Assert.assertTrue(archivoYML2.exists());
		Assert.assertTrue(archivoYML3.exists());

		this.volverArchivosAlOrigen();
	}

	private void volverArchivosAlOrigen() {
		File destino1 = new File(PATH_ARCHIVOS_ZIP + "/procesados/recorridos-2010.zip");
		File destino2 = new File(PATH_ARCHIVOS_ZIP + "/procesados/recorridos-2012.zip");
		File destino3 = new File(PATH_ARCHIVOS_ZIP + "/procesados/recorridos-2013.zip");
		File origen1 = new File(PATH_ARCHIVOS_ZIP + "/recorridos-2010.zip");
		File origen2 = new File(PATH_ARCHIVOS_ZIP + "/recorridos-2012.zip");
		File origen3 = new File(PATH_ARCHIVOS_ZIP + "/recorridos-2013.zip");

		destino1.renameTo(origen1);
		destino2.renameTo(origen2);
		destino3.renameTo(origen3);
	}
}