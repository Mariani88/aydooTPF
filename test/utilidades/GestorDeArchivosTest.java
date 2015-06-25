package utilidades;

import java.io.File;




import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import dominio.InformacionEstadistica;
import excepciones.DirectorioNoExisteExcepcion;

public class GestorDeArchivosTest {
	
	private String directorio = "documentos";
	
	@Test(expected=DirectorioNoExisteExcepcion.class)
	public void directorioInexistenteDeDevolverExcepcion () throws DirectorioNoExisteExcepcion{
		
		GestorDeArchivos gestor = new GestorDeArchivos ("hola");
	}	
	
	@Test
	public void generarYMLDebeCrearYML () throws DirectorioNoExisteExcepcion, IOException{
		
		GestorDeArchivos gestor = new GestorDeArchivos (this.directorio);
		InformacionEstadistica info = new InformacionEstadistica ();
		
		info.guardarBicicletasMasUsadas(3, 10);
		info.guardarBicicletasMenosUsadas(1, 2);
		info.guardarRecorridoMasRealizado(new RecorridoDTO (1,2), 2);
		info.setTiempoPromedio(30);
		
		gestor.crearYMLCon (info);
		
		File file = new File (this.directorio + "/estadisticas.yml");
			
		Assert.assertTrue(file.exists());
	}
}