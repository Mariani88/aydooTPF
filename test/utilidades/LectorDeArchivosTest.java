package utilidades;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.junit.Assert;
import org.junit.Test;

import utilidades.LectorDeArchivos;

public class LectorDeArchivosTest {
	
	private static final String PATH_ARCHIVOS_ZIP = "documentos/archivos zip para test";
	private static final String PATH_ARCHIVO_ZIP_INVALIDO = "documentos/archivo zip invalido para test";

	@Test
	public void cuandoLeoUnDirectorioTodosLosArchivosZipDelDirectorioSonLeidos() throws ZipException, IOException{
		
		LectorDeArchivos lectorDeArchivos = new LectorDeArchivos();
		
		ZipFile[] listaDeArchivosZipEnDirectorio = lectorDeArchivos.getArchivosZip(PATH_ARCHIVOS_ZIP);
		
		int cantidadDeArchivosZipLeidos = 3;
		
		Assert.assertEquals(cantidadDeArchivosZipLeidos, listaDeArchivosZipEnDirectorio.length);
	}
	
	@Test
	public void cuandoLeoUnArchivoZipTodosLosArchivosCSVDelArchivoSonLeidos() throws ZipException, IOException{
		
		LectorDeArchivos lectorDeArchivosZip = new LectorDeArchivos();
		
		ZipFile[] listaDeArchivosZipEnDirectorio = lectorDeArchivosZip.getArchivosZip(PATH_ARCHIVOS_ZIP);
		
		Enumeration<? extends ZipEntry> listaDeArchivosEnZip = lectorDeArchivosZip.
				descomprimirArchivosDeZip(listaDeArchivosZipEnDirectorio[0]);
		
		int cantidadDeArchivosCSVQueSeEsperaQueLea = 4;
		
		int cantidadDeArchivosCSVLeidos = contarArchivosCSVLeidos(lectorDeArchivosZip , listaDeArchivosEnZip);
		
		Assert.assertEquals(cantidadDeArchivosCSVQueSeEsperaQueLea, cantidadDeArchivosCSVLeidos);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cuandoLeoUnArchivoZipConContenidoQueNoEsCSVEntoncesEsperoUnaExcepcion() throws ZipException, IOException{
		
		LectorDeArchivos lectorDeArchivosZip = new LectorDeArchivos();
		
		ZipFile[] listaDeArchivosZipEnDirectorio = lectorDeArchivosZip.getArchivosZip(PATH_ARCHIVO_ZIP_INVALIDO);
		
		Enumeration<? extends ZipEntry> listaDeArchivosEnZip = lectorDeArchivosZip.
				descomprimirArchivosDeZip(listaDeArchivosZipEnDirectorio[0]);
		
		contarArchivosCSVLeidos(lectorDeArchivosZip , listaDeArchivosEnZip);
		
	}

	private int contarArchivosCSVLeidos(LectorDeArchivos lectorDeArchivosZip ,
			Enumeration<? extends ZipEntry> listaDeArchivosCSVEnZip) {
		int archivosCSVLeidos = 0;
		
		while(listaDeArchivosCSVEnZip.hasMoreElements()){
			ZipEntry archivoDescomprimido = listaDeArchivosCSVEnZip.nextElement();
			lectorDeArchivosZip.validarQueElArchivoSeaCSV(archivoDescomprimido);
			archivosCSVLeidos++;
		}
		return archivosCSVLeidos;
	}
	
	
	
	//agregar test de validacion de que el directorio exista.
}

