package utilidades;

import org.junit.Assert;
import org.junit.Test;

public class RecorridoDTOTest {

	@Test
	public void recorridoDTOConMismosIDSonIguales (){
		
		RecorridoDTO recorrido = new RecorridoDTO (1,1);
		RecorridoDTO recorrido1 = new RecorridoDTO (1,1);
		
		Assert.assertTrue (recorrido.equals(recorrido1));
	}
	
	@Test
	public void recorridoDTOConDistintosIDNoSonIguales (){
		
		RecorridoDTO recorrido = new RecorridoDTO (1,1);
		RecorridoDTO recorrido1 = new RecorridoDTO (1,2);
		
		Assert.assertFalse (recorrido.equals(recorrido1));
	}
	
	
}
