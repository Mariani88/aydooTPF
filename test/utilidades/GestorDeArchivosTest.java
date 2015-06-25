package utilidades;

import org.junit.Test;

import dominio.InformacionEstadistica;
import excepciones.DirectorioNoExisteExcepcion;

public class GestorDeArchivosTest {
	
	@Test(expected=DirectorioNoExisteExcepcion.class)
	public void directorioInexistenteDeDevolverExcepcion () throws DirectorioNoExisteExcepcion{
		
		GestorDeArchivos gestor = new GestorDeArchivos ("hola");
		
		
	}	
	
	/*@Test
	public void generarYMLDebeCrearYML (){
		
		GestorDeArchivos gestor = new GestorDeArchivos ();
		InformacionEstadistica info = new InformacionEstadistica ();
		
		
		gestor.crearYMLCon (info);
		
		File
		*/	
			
		
		
	

}
