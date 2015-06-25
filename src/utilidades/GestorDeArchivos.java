package utilidades;

import java.io.File;

import excepciones.DirectorioNoExisteExcepcion;

public class GestorDeArchivos {

	private String directorioDeDescompresion;
	private String directorioDeTrabajo;
	private String directorioArchivosProcesados;
	
	public GestorDeArchivos (String directorioDeTrabajo) throws DirectorioNoExisteExcepcion{
		
		File file = new File (directorioDeTrabajo);
		
		if ( file.exists()) this.directorioDeTrabajo = directorioDeTrabajo;
		else throw new DirectorioNoExisteExcepcion();
	}
	
	
}
