package utilidades;

import org.junit.Assert;
import org.junit.Test;

public class RutaTest {

	@Test
	public void rutaConMismosIDSonIguales (){
		
		Ruta recorrido = new Ruta (1,1);
		Ruta recorrido1 = new Ruta (1,1);
		
		Assert.assertTrue (recorrido.equals(recorrido1));
	}
	
	@Test
	public void rutaConDistintosIDNoSonIguales (){
		
		Ruta recorrido = new Ruta (1,1);
		Ruta recorrido1 = new Ruta (1,2);
		
		Assert.assertFalse (recorrido.equals(recorrido1));
	}
}
