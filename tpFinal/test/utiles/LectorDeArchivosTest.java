package utiles;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.junit.Assert;
import org.junit.Test;

public class LectorDeArchivosTest {
	
	@Test
	public void cuandoLeoUnDirectorioTodosLosArchivosZipDelDirectorioSonLeidos() throws ZipException, IOException{
		
		LectorDeArchivos lectorDeArchivos = new LectorDeArchivos();
		
		ZipFile[] listaDeArchivosZipEnDirectorio = lectorDeArchivos.getArchivosZip("documentos");
		
		int cantidadDeArchivosZipLeidos = 3;
		
		Assert.assertEquals(cantidadDeArchivosZipLeidos, listaDeArchivosZipEnDirectorio.length);
	}
	
	@Test
	public void cuandoLeoUnArchivoZipTodosLosArchivosCSVDelArchivoSonLeidos() throws ZipException, IOException{
		
		LectorDeArchivos lectorDeArchivosZip = new LectorDeArchivos();
		
		ZipFile[] listaDeArchivosZipEnDirectorio = lectorDeArchivosZip.getArchivosZip("documentos");
		
		Enumeration<? extends ZipEntry> listaDeArchivosCSVEnZip = lectorDeArchivosZip.
				descomprimirZip(listaDeArchivosZipEnDirectorio[0]);
		
		int cantidadDeArchivosCSVQueSeEsperaQueLea = 4;
		
		int cantidadDeArchivosCSVLeidos = contarArchivosCSVLeidos(listaDeArchivosCSVEnZip);
		
		Assert.assertEquals(cantidadDeArchivosCSVQueSeEsperaQueLea, cantidadDeArchivosCSVLeidos);
	}

	private int contarArchivosCSVLeidos(
			Enumeration<? extends ZipEntry> listaDeArchivosCSVEnZip) {
		int archivosCSVLeidos = 0;
		
		while(listaDeArchivosCSVEnZip.hasMoreElements()){
			listaDeArchivosCSVEnZip.nextElement();
			archivosCSVLeidos++;
		}
		return archivosCSVLeidos;
	}
	
	//agregar test de validacion de que el directorio exista.
}

