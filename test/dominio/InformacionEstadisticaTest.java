package dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import excepciones.BicicletaMasUsadaExcepcion;
import excepciones.BicicletaMenosUsadaExcepcion;
import excepciones.RecorridoMasRealizadoExcepcion;
import utilidades.Ruta;

public class InformacionEstadisticaTest {

	private InformacionEstadistica info = new InformacionEstadistica();

	@Test
	public void getBicicletasMasUsadasDebeDevolverListaDeBicicletasMasUsadas() {

		List<Integer> bicicletasMasUsadasEsperadas = new LinkedList<Integer>();

		this.info.guardarBicicletasMasUsadas(3, 40);
		this.info.guardarBicicletasMasUsadas(4, 40);
		bicicletasMasUsadasEsperadas.add(3);
		bicicletasMasUsadasEsperadas.add(4);

		Assert.assertEquals(bicicletasMasUsadasEsperadas,
				this.info.bicicletasMasUsadas());
	}
	
	
	@Test
	public void getBicicletasMasUsadasNoDebeDevolverMaximoViejos() {
		
		List<Integer> bicicletasMasUsadasEsperadas = new LinkedList<Integer>();
		
		this.info.guardarBicicletasMasUsadas(3, 40);
		this.info.guardarBicicletasMasUsadas(4, 40);
		this.info.guardarBicicletasMasUsadas(1, 1000);
		
		bicicletasMasUsadasEsperadas.add(1);
		Assert.assertEquals(bicicletasMasUsadasEsperadas,
				this.info.bicicletasMasUsadas());
	}
	

	@Test
	public void BicicletasMenosUsadasDebeDevolverListaDeBicicletasMenosUsadas() {

		List<Integer> bicicletasMenosUsadasEsperadas = new LinkedList<Integer>();

		this.info.guardarBicicletasMenosUsadas(3, 40);
		this.info.guardarBicicletasMenosUsadas(4, 40);
		bicicletasMenosUsadasEsperadas.add(3);
		bicicletasMenosUsadasEsperadas.add(4);

		Assert.assertEquals(bicicletasMenosUsadasEsperadas,
				this.info.bicicletasMenosUsadas());
	}
	
	@Test
	public void BicicletasMenosUsadasNoDebeDEvolverMinimosViejos (){
		
		this.info.guardarBicicletasMenosUsadas(3, 40);
		this.info.guardarBicicletasMenosUsadas(4, 40);
		this.info.guardarBicicletasMenosUsadas(1, 10);
		
		List<Integer> bicicletasMenosUsadasEsperadas = new LinkedList<Integer>();
		bicicletasMenosUsadasEsperadas.add(1);
		Assert.assertEquals(bicicletasMenosUsadasEsperadas,
				this.info.bicicletasMenosUsadas());
	}
	
	@Test
	public void RecorridosMasRelizadosDebeDevolverRecorridoMasRealizado() {

		List<Ruta> recorridosMasRealizados = new LinkedList<Ruta>();

		this.info.guardarRecorridoMasRealizado(new Ruta(1, 1), 50);
		this.info.guardarRecorridoMasRealizado(new Ruta(1, 2), 50);

		recorridosMasRealizados.add(new Ruta(1, 1));
		recorridosMasRealizados.add(new Ruta(1, 2));

		Assert.assertTrue(recorridosMasRealizados.containsAll(this.info
				.recorridosMasRealizados()));
	}
	
	@Test
	public void RecorridosMasRelizadosNoDebeDevolverMaximosViejos (){
		
		List<Ruta> recorridosMasRealizados = new LinkedList<Ruta>();

		this.info.guardarRecorridoMasRealizado(new Ruta(1, 1), 50);
		this.info.guardarRecorridoMasRealizado(new Ruta(1, 2), 50);
		
		this.info.guardarRecorridoMasRealizado(new Ruta(10, 10), 100);
		
		recorridosMasRealizados.add(new Ruta(10, 10));
		
		Assert.assertEquals(recorridosMasRealizados,
				this.info.recorridosMasRealizados());	
		
	}
	
	
	
	@Test(expected=BicicletaMasUsadaExcepcion.class)
	public void excepcionSiSeIntentaGuardarBicicletaConMenorUsoAlMaximo (){
		this.info.guardarBicicletasMasUsadas(2, 1);
		this.info.guardarBicicletasMasUsadas(3, 0);
	}
	
	@Test(expected=BicicletaMenosUsadaExcepcion.class)
	public void excepcionSiSeIntentaGuardarBicicletaConMasUsoAlMinimo (){
		this.info.guardarBicicletasMenosUsadas(2, 1);
		this.info.guardarBicicletasMenosUsadas(3, 2);
	}
	
	@Test(expected=RecorridoMasRealizadoExcepcion.class)
	public void excepcionSiSeIntentaGuardarUnRecorridoMenosRealizadoQueElMaximo (){
		this.info.guardarRecorridoMasRealizado(new Ruta (1,2), 3);
		this.info.guardarRecorridoMasRealizado(new Ruta (3,4), 1);
	}
	
	@Test
	public void evaluarDatoBicicletaDebeGuardarDatoSiEsMaximo (){
		
		this.info.guardarBicicletasMasUsadas(3, 100);
		List <Integer> bicicletasEsperadas = new LinkedList <Integer>();
		Map <Integer,Integer> historial = new HashMap<Integer,Integer> ();
		historial.put(9,2000);
		historial.put(8,200);
		historial.put(4,200);
		historial.put(19,2000);
		
		this.info.evaluarUsoBicicleta (historial);
		bicicletasEsperadas.add(9);
		bicicletasEsperadas.add(19);
		
		Assert.assertTrue(bicicletasEsperadas.containsAll( this.info.bicicletasMasUsadas()) );
	}
	
	@Test
	public void evaluarDatoBicicletaDebeGuardarDatoSiEsMinimo (){
		this.info.guardarBicicletasMenosUsadas(3, 100);
		List <Integer> bicicletasEsperadas = new LinkedList <Integer>();
		Map <Integer,Integer> historial = new HashMap<Integer,Integer> ();
		historial.put(9,10);
		
		this.info.evaluarUsoBicicleta (historial);
		bicicletasEsperadas.add(9);
		
		Assert.assertEquals(bicicletasEsperadas, this.info.bicicletasMenosUsadas());
	}
	
	
	@Test
	public void evaluarDatoBicicletaDebeGuardarMinimoYRemoverViejosMinimos() {

		List<Integer> bicicletasEsperadas = new LinkedList<Integer>();
		
		Map <Integer, Integer> historialBicicletas = new HashMap<Integer,Integer> ();
		
		historialBicicletas.put(3, 2);
		historialBicicletas.put(9, 2);
		historialBicicletas.put(4, 7);
		historialBicicletas.put(6, 10);
		historialBicicletas.put(10, 10);
		
		this.info.evaluarUsoBicicleta(historialBicicletas);
		
		bicicletasEsperadas.add(9);
		bicicletasEsperadas.add(3);
		
		Assert.assertTrue(bicicletasEsperadas.containsAll(this.info.bicicletasMenosUsadas()));
	}
	
	@Test
	public void evaluarRecorridosDebeGuardarRecorridoMaximo (){
		
		this.info.guardarRecorridoMasRealizado(new Ruta (1,1),200);
		
		List<Ruta> recorridosEsperados = new LinkedList<Ruta> ();
		recorridosEsperados.add(new Ruta (2,2));
		recorridosEsperados.add(new Ruta (3,2));
		
		Map<Ruta, Integer> historialRecorridos = new HashMap <Ruta, Integer> ();
		
		historialRecorridos.put(new Ruta (2,2), 1000);
		historialRecorridos.put(new Ruta (3,2), 1000);
		historialRecorridos.put(new Ruta (6,2), 1);
		historialRecorridos.put(new Ruta (7,2), 10);
		
			
		this.info.evaluarRecorridos(historialRecorridos);
		
		Assert.assertTrue (recorridosEsperados.containsAll(this.info.recorridosMasRealizados()));
		
	}
}