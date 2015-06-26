package utilidades;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipException;

import org.junit.Assert;
import org.junit.Test;

import dominio.InformacionEstadistica;

public class GestorDeArchivosTest {
	
	private static final String PATH_INVALIDO = "path invalido";
	private static final String PATH_ARCHIVOS_ZIP = "documentos/archivos zip para test";
	
	@Test(expected= IllegalArgumentException.class)
	public void cuandoSeQuiereObtenerArchivosDeUnDirectorioInexistenteEsperoExcepcion()
			throws ZipException, IOException{
		
		GestorDeArchivos gestorDeArchivos = new GestorDeArchivos();
		gestorDeArchivos.obtenerArchivosZip(PATH_INVALIDO);
	}	
	
	@Test
	public void cuandoGeneroElYMLEntoncesSeCreaElArchivoYML() throws IOException{
		
		GestorDeArchivos gestor = new GestorDeArchivos();
		InformacionEstadistica info = new InformacionEstadistica ();
		
		info.guardarBicicletasMasUsadas(3, 10);
		info.guardarBicicletasMenosUsadas(1, 2);
		info.guardarRecorridoMasRealizado(new RecorridoDTO (1,2), 2);
		info.setTiempoPromedio(30);
		
		gestor.crearYMLCon(info, PATH_ARCHIVOS_ZIP); 
		
		File file = new File (PATH_ARCHIVOS_ZIP+ "/estadisticas.yml");
			
		Assert.assertTrue(file.exists());
	}
	
	
}