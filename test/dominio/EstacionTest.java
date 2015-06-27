package dominio;

import org.junit.Assert;
import org.junit.Test;

public class EstacionTest {

	@Test
	public void dosEstacionesSonIgualesSiTienenMismoId (){
		
		Estacion estacion1 = new Estacion (2, "hola");
		Estacion estacion2 = new Estacion (2, "r");
		Estacion estacion3 = new Estacion (3, "g");
		
		Assert.assertTrue(estacion1.equals(estacion2));
		Assert.assertFalse(estacion1.equals(estacion3));
	}
	
	@Test
	public void dosEstacionesDebenTenerMismoHashCode (){
		Estacion estacion1 = new Estacion (2, "hola");
		Estacion estacion2 = new Estacion (2, "r");
		
		Assert.assertEquals(estacion1.hashCode(), estacion2.hashCode());
	}
}