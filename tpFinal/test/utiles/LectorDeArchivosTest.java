package utiles;

import java.io.File;
import org.junit.Assert;
import org.junit.Test;

public class LectorDeArchivosTest {
	
	@Test
	public void cuandoLeoUnDirectorioTodosLosArchivosZipDelDirectorioSonLeidos(){
		
		LectorDeArchivos lectorDeArchivos = new LectorDeArchivos();
		
		File[] listaDeArchivosEnDirectorio = lectorDeArchivos.getListaDeArchivos("documentos");
		
		int cantidadDeArchivosLeidos = 3;
		
		Assert.assertEquals(cantidadDeArchivosLeidos, listaDeArchivosEnDirectorio.length);
	}
}
