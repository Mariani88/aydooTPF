package dominio;

import org.junit.Assert;
import org.junit.Test;

import utilidades.Ruta;

public class RecorridoTest {

	@Test
	public void dosRecorridosSonIgualesSiTienenMismoOrigenYDestino (){
		
		Estacion estacion1 = new Estacion (1, "Retiro");
		Estacion estacion2 = new Estacion (2, "Urquiza");
		Recorrido recorrido1 = new Recorrido (estacion1, estacion2);
		Recorrido recorrido2 = new Recorrido (estacion1, estacion2);
		
		Assert.assertTrue(recorrido1.equals(recorrido2));
	}
	
	@Test
	public void dosRecorridosIgualesDebenDevolverMismoHashCode (){
		
		Estacion estacion1 = new Estacion (1, "Retiro");
		Estacion estacion2 = new Estacion (2, "Urquiza");
		Recorrido recorrido1 = new Recorrido (estacion1, estacion2);
		Recorrido recorrido2 = new Recorrido (estacion1, estacion2);
		
		Assert.assertEquals (recorrido1.hashCode(), recorrido2.hashCode());
	}
	
	@Test
	public void parsearARecorridoDTODebeDevolverObjetoConExtremosRecorrido (){
		
		Estacion estacion1 = new Estacion (1, "Retiro");
		Estacion estacion2 = new Estacion (2, "Urquiza");
		Recorrido recorrido = new Recorrido (estacion1, estacion2);
		
		Ruta recorridoDTO = new Ruta (estacion1.getId(), estacion2.getId());
		
		Assert.assertEquals(recorridoDTO, recorrido.parsearADTO());
	}	
}