package utilidades.tareasDeMonitor;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class TareaProcesarZipsTest {

	private static final String PATH_ARCHIVOS_ZIP = "documentos/archivosZipParaTest";

	@Test
	public void runDebeProcesarCadaArchivoPorSeparado() {

		TareaProcesarZips tarea = new TareaProcesarZips(PATH_ARCHIVOS_ZIP);
		tarea.run();
		
		File archivoYML1 = new File (PATH_ARCHIVOS_ZIP + "/salida"+"/recorridos-2010.zip.yml");
		File archivoYML2 = new File (PATH_ARCHIVOS_ZIP + "/salida"+"/recorridos-2010.zip.yml");
		File archivoYML3 = new File (PATH_ARCHIVOS_ZIP + "/salida"+"/recorridos-2010.zip.yml");
		
		Assert.assertTrue(archivoYML1.exists());
		Assert.assertTrue(archivoYML2.exists());
		Assert.assertTrue(archivoYML3.exists());
	}
	
}
