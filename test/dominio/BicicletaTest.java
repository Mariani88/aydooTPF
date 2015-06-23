package dominio;


import org.junit.Assert;
import org.junit.Test;

public class BicicletaTest {

	@Test
	public void dosBicicletasSonIgualesSiTienenMismoId (){
		
		Bicicleta bicicleta1 = new Bicicleta (1, null);
		Bicicleta bicicleta2 = new Bicicleta (1, null);
		Bicicleta bicicleta3 = new Bicicleta (2, null);
		
		Assert.assertTrue(bicicleta1.equals(bicicleta2));
		Assert.assertFalse(bicicleta1.equals(bicicleta3));
	}	
	
	
	@Test
	public void dosBicicletasDebenDevolverMismoHashCode (){
		
		Bicicleta bicicleta1 = new Bicicleta (1, null);
		Bicicleta bicicleta2 = new Bicicleta (1, null);
		
		Assert.assertEquals(bicicleta1.hashCode(), bicicleta2.hashCode());
	}
}