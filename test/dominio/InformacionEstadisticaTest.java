package dominio;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		this.info.evaluarUsoBicicleta (9,200);
		bicicletasEsperadas.add(9);
		
		Assert.assertEquals(bicicletasEsperadas, this.info.bicicletasMasUsadas());
	}
	
	@Test
	public void evaluarDatoBicicletaDebeGuardarDatoSiEsMinimo (){
		this.info.guardarBicicletasMenosUsadas(3, 100);
		List <Integer> bicicletasEsperadas = new LinkedList <Integer>();
		this.info.evaluarUsoBicicleta (9,10);
		bicicletasEsperadas.add(9);
		
		Assert.assertEquals(bicicletasEsperadas, this.info.bicicletasMenosUsadas());
	}
	
	@Test
	public void evaluarDatoBicicletaDebeGuardarMinimoYRemoverViejosMinimos() {

		List<Integer> bicicletasEsperadas = new LinkedList<Integer>();
		this.info.evaluarUsoBicicleta(1, 1);
		this.info.evaluarUsoBicicleta(3, 1);
		this.info.evaluarUsoBicicleta(3, 2);
		this.info.evaluarUsoBicicleta(9, 1);
		this.info.evaluarUsoBicicleta(1, 2);
		this.info.evaluarUsoBicicleta(1, 3);
		this.info.evaluarUsoBicicleta(3, 3);
		bicicletasEsperadas.add(9);

		Assert.assertEquals(bicicletasEsperadas,
				this.info.bicicletasMenosUsadas());
	}
	
	@Test
	public void evaluarDatoDebeGuardarDatoSiRecorridoEsMaximo (){
		
		this.info.guardarRecorridoMasRealizado(new Ruta (1,1),200);
		
		List<Ruta> recorridosEsperados = new LinkedList<Ruta> ();
		recorridosEsperados.add(new Ruta (2,2));
		this.info.evaluarRecorrido(new Ruta (2,2), 3000);
		
		Assert.assertEquals(recorridosEsperados, this.info.recorridosMasRealizados());	
	}
	
	@Test
	public void getBicicletasUsadasMasTiempoDebeDevolverBici123Test(){
		Recorrido recorrido1 = new Recorrido(new Estacion(12, "Palermo"), new Estacion(10, "Once"));
		Bicicleta bici = new Bicicleta(123, recorrido1);
		Map <Bicicleta,Integer> tiemposDeBicicletas = new HashMap<Bicicleta, Integer>();
		tiemposDeBicicletas.put(bici, 16);		
		InformacionEstadistica info = new InformacionEstadistica();
		info.generarBicicletasUsadasMasTiempo(tiemposDeBicicletas);
		Set <Bicicleta> conjuntoDeBicicletasUsadasMasTiempo =info.getBicicletasUsadasMasTiempo();
		
		Assert.assertEquals(1 ,conjuntoDeBicicletasUsadasMasTiempo.size());
		Assert.assertTrue(conjuntoDeBicicletasUsadasMasTiempo.contains(bici));
		Assert.assertEquals(123, conjuntoDeBicicletasUsadasMasTiempo.iterator().next().getId());
	}
	
	@Test
	public void getBicicletasUsadasMasTiempoDebeDevolverBici200PeroNoBici300Test(){
		Recorrido recorrido1 = new Recorrido(new Estacion(12, "Palermo"), new Estacion(10, "Once"));
		Bicicleta bici200 = new Bicicleta(200, recorrido1);
		Bicicleta bici300 = new Bicicleta(300, recorrido1);
		Map <Bicicleta,Integer> tiemposDeBicicletas = new HashMap<Bicicleta, Integer>();
		tiemposDeBicicletas.put(bici200, 50);
		tiemposDeBicicletas.put(bici300, 30);
		InformacionEstadistica info = new InformacionEstadistica();
		info.generarBicicletasUsadasMasTiempo(tiemposDeBicicletas);
		Set <Bicicleta> conjuntoDeBicicletasUsadasMasTiempo =info.getBicicletasUsadasMasTiempo();
		
		Assert.assertEquals(1 ,conjuntoDeBicicletasUsadasMasTiempo.size());
		Assert.assertTrue(conjuntoDeBicicletasUsadasMasTiempo.contains(bici200));
		Assert.assertEquals(200, conjuntoDeBicicletasUsadasMasTiempo.iterator().next().getId());
	}
	
	@Test
	public void getBicicletasUsadasMasTiempoDebeDevolverBici200Bici300PeroNoBici400Test(){
		Recorrido recorrido1 = new Recorrido(new Estacion(12, "Palermo"), new Estacion(10, "Once"));
		Bicicleta bici200 = new Bicicleta(200, recorrido1);
		Bicicleta bici300 = new Bicicleta(300, recorrido1);
		Bicicleta bici400 = new Bicicleta(400, recorrido1);
		Map <Bicicleta,Integer> tiemposDeBicicletas = new HashMap<Bicicleta, Integer>();
		tiemposDeBicicletas.put(bici200, 50);
		tiemposDeBicicletas.put(bici300, 30);
		tiemposDeBicicletas.put(bici400, 50);
		InformacionEstadistica info = new InformacionEstadistica();
		info.generarBicicletasUsadasMasTiempo(tiemposDeBicicletas);
		Set <Bicicleta> conjuntoDeBicicletasUsadasMasTiempo =info.getBicicletasUsadasMasTiempo();
		Iterator <Bicicleta> iterador = conjuntoDeBicicletasUsadasMasTiempo.iterator();
		
		Assert.assertEquals(2 ,conjuntoDeBicicletasUsadasMasTiempo.size());
		Assert.assertTrue(conjuntoDeBicicletasUsadasMasTiempo.contains(bici200));
		Assert.assertTrue(conjuntoDeBicicletasUsadasMasTiempo.contains(bici400));
		Assert.assertEquals(200, iterador.next().getId());
		Assert.assertEquals(400, iterador.next().getId());
	}
	
	@Test
	public void getBicicletasUsadasMasTiempoDebeDevolverBici123Bici145yBici150Test(){
		Recorrido recorrido1 = new Recorrido(new Estacion(12, "Palermo"), new Estacion(10, "Once"));
		Bicicleta bici1 = new Bicicleta(123, recorrido1);
		Bicicleta bici2 = new Bicicleta(145, recorrido1);
		Bicicleta bici3 = new Bicicleta(150, recorrido1);
		Map <Bicicleta,Integer> tiemposDeBicicletas = new HashMap<Bicicleta, Integer>();
		tiemposDeBicicletas.put(bici1, 16);
		tiemposDeBicicletas.put(bici2, 16);
		tiemposDeBicicletas.put(bici3, 16);
		InformacionEstadistica info = new InformacionEstadistica();
		info.generarBicicletasUsadasMasTiempo(tiemposDeBicicletas);
		Set <Bicicleta> conjuntoDeBicicletasUsadasMasTiempo =info.getBicicletasUsadasMasTiempo();
		Iterator <Bicicleta> iterador = conjuntoDeBicicletasUsadasMasTiempo.iterator();
		
		Assert.assertEquals(3 ,conjuntoDeBicicletasUsadasMasTiempo.size());
		Assert.assertTrue(conjuntoDeBicicletasUsadasMasTiempo.contains(bici1));
		Assert.assertTrue(conjuntoDeBicicletasUsadasMasTiempo.contains(bici2));
		Assert.assertTrue(conjuntoDeBicicletasUsadasMasTiempo.contains(bici3));		
		Assert.assertEquals(123, iterador.next().getId());
		Assert.assertEquals(145, iterador.next().getId());
		Assert.assertEquals(150, iterador.next().getId());
	}
	
}

