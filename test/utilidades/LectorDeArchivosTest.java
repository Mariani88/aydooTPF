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
	
	private static final String DOCUMENTOS_ARCHIVOS_ZIP_PARA_TEST_RECORRIDOS_2010_ZIP = "documentos/archivos zip para test/recorridos-2010.zip";
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
	public void cuandoLeoUnArchivoZipTodosLosArchivosCSVQueContieneSonLeidos() throws ZipException, IOException{
		
		LectorDeArchivos lectorDeArchivosZip = new LectorDeArchivos();
		
		ZipFile[] listaDeArchivosZipEnDirectorio = lectorDeArchivosZip.getArchivosZip(PATH_ARCHIVOS_ZIP);
		
		int cantidadDeArchivosCSVQueSeEsperaQueLea = 7;
		
		int cantidadDeArchivosCSVLeidos = contarArchivosCSVLeidos(lectorDeArchivosZip , listaDeArchivosZipEnDirectorio);
		
		Assert.assertEquals(cantidadDeArchivosCSVQueSeEsperaQueLea, cantidadDeArchivosCSVLeidos);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cuandoLeoUnArchivoZipConContenidoQueNoEsCSVEntoncesEsperoUnaExcepcion() throws ZipException, IOException{
		
		LectorDeArchivos lectorDeArchivosZip = new LectorDeArchivos();
		
		ZipFile[] listaDeArchivosZipEnDirectorio = lectorDeArchivosZip.getArchivosZip(PATH_ARCHIVO_ZIP_INVALIDO);
		
		contarArchivosCSVLeidos(lectorDeArchivosZip , listaDeArchivosZipEnDirectorio);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cuandoLeoUnDirectorioInvalidoEntoncesEsperoUnaExcepcion() throws ZipException, IOException{
		
		LectorDeArchivos lectorDeArchivos = new LectorDeArchivos();
		
		ZipFile[] listaDeArchivosZipEnDirectorio = lectorDeArchivos.getArchivosZip(DOCUMENTOS_ARCHIVOS_ZIP_PARA_TEST_RECORRIDOS_2010_ZIP);
		
		int cantidadDeArchivosZipLeidos = 3;
		
		Assert.assertEquals(cantidadDeArchivosZipLeidos, listaDeArchivosZipEnDirectorio.length);
	}

	private int contarArchivosCSVLeidos(LectorDeArchivos lectorDeArchivosZip ,
			ZipFile[] listaDeArchivosZipEnDirectorio) {
		int archivosCSVLeidos = 0;
		
		for (ZipFile archivoZip : listaDeArchivosZipEnDirectorio) {
			Enumeration<? extends ZipEntry> listaDeArchivosEnZip = lectorDeArchivosZip.
					leerArchivosCSVContenidosEnZip(archivoZip);
		
			while(listaDeArchivosEnZip.hasMoreElements()){
				ZipEntry archivoDescomprimido = listaDeArchivosEnZip.nextElement();
				lectorDeArchivosZip.validarQueElArchivoSeaCSV(archivoDescomprimido);
				archivosCSVLeidos++;
			}
		}
		return archivosCSVLeidos;
	}
}

