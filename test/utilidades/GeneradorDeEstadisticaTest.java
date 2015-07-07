package utilidades;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import dominio.Bicicleta;
import dominio.Estacion;
import dominio.InformacionEstadistica;
import dominio.Recorrido;

public class GeneradorDeEstadisticaTest {

	private  List<Bicicleta> bicicletas = new LinkedList<Bicicleta>();
	private  List<Recorrido> recorridos = new LinkedList<Recorrido>();
	
	
	public void cargarEstaciones(List <Estacion> estaciones) {
		
		estaciones.add(new Estacion(20, "O"));
		estaciones.add(new Estacion(30, "D"));
		estaciones.add(new Estacion(11, "O"));
		estaciones.add(new Estacion(22, "D"));
		estaciones.add(new Estacion(100, "O"));
		estaciones.add(new Estacion(200, "D"));
	}
	
	
	public void cargarRecorridos (){
		
		List<Estacion> estaciones = new LinkedList <Estacion> ();
		cargarEstaciones(estaciones);

		recorridos.add( new Recorrido(estaciones.get(0),
				estaciones.get(1)) );
		recorridos.add( new Recorrido(estaciones.get(2),
				estaciones.get(3)) );
		recorridos.add( new Recorrido(estaciones.get(4),
				estaciones.get(5)) );
		
		recorridos.get(0).setMinutosRecorridos(11);
		recorridos.get(1).setMinutosRecorridos(20);
		recorridos.get(2).setMinutosRecorridos(10);
	}

	@Before
	public void cargarDatos() {

		cargarRecorridos();
		bicicletas.add(new Bicicleta(1, recorridos.get(0)));
		bicicletas.add(new Bicicleta(3, recorridos.get(1)));
		bicicletas.add(new Bicicleta(3, recorridos.get(0)));
		bicicletas.add(new Bicicleta(9, recorridos.get(0)));
		bicicletas.add(new Bicicleta(1, recorridos.get(2)));
		bicicletas.add(new Bicicleta(1, recorridos.get(1)));
		bicicletas.add(new Bicicleta(3, recorridos.get(1)));
	}

	@Test
	public void generarEstadisticasDebeObtenerBicicletaMasUsada() {
		
		GeneradorDeEstadistica generador = new GeneradorDeEstadistica();
		List<Integer> idBicicletasMasUsadas = new LinkedList<Integer>();
		idBicicletasMasUsadas.add(1);
		idBicicletasMasUsadas.add(3);

		generador.generarEstadistica(bicicletas);

		Assert.assertEquals(idBicicletasMasUsadas, generador.terminar()
				.bicicletasMasUsadas());
	}
	
	@Test
	public void generarEstadisticasDebeObtenerBicicletaMenosUsada() {
		
		GeneradorDeEstadistica generador = new GeneradorDeEstadistica();
		List<Integer> idBicicletasMenosUsadas = new LinkedList<Integer>();
		idBicicletasMenosUsadas.add(9);
		
		generador.generarEstadistica(bicicletas);

		Assert.assertEquals(idBicicletasMenosUsadas, generador.terminar()
				.bicicletasMenosUsadas());
	}
	
	@Test
	public void generarEstadisticasDebeObtenerRecorridoMasRealizado(){
		
		GeneradorDeEstadistica generador = new GeneradorDeEstadistica();
		List<Ruta> recorridosEsperados = new LinkedList<Ruta>();
		
		recorridosEsperados.add(this.recorridos.get(0).parsearARuta());
		recorridosEsperados.add(this.recorridos.get(1).parsearARuta());
		
		generador.generarEstadistica(bicicletas);
		Assert.assertEquals(recorridosEsperados, generador.terminar().recorridosMasRealizados());
	}
	
	
	@Test
	public void generarEstadisticasDebeObtenerPromedioDeUsoDeBicicletas(){
		
		GeneradorDeEstadistica generador = new GeneradorDeEstadistica();
		generador.generarEstadistica(bicicletas);
		
		int tiempoPromedio = 14;		
		Assert.assertEquals(tiempoPromedio, generador.terminar().getTiempoPromedio());
	}	
	
	
	@Test
	public void terminarDebeDevolverInformacionYReiniciarParametros (){
		
		GeneradorDeEstadistica generador = new GeneradorDeEstadistica();
		generador.generarEstadistica(bicicletas);

		List<Integer> idBicicletaInicializado = new LinkedList<Integer>();
		idBicicletaInicializado.add(-1);

		List<Ruta> idRecorridoInicializado = new LinkedList<Ruta>();
		idRecorridoInicializado.add(new Ruta(0, 0));

		generador.terminar();
		InformacionEstadistica infoReiniciada= generador.terminar();
		
		Assert.assertEquals(idBicicletaInicializado, infoReiniciada.bicicletasMasUsadas());
		Assert.assertEquals(idBicicletaInicializado, infoReiniciada.bicicletasMenosUsadas());
		Assert.assertEquals(idRecorridoInicializado, infoReiniciada.recorridosMasRealizados());
	}
	
	
	@Test
	public void terminarDebeReiniciarParametrosDeCalculo (){
		
		GeneradorDeEstadistica generador = new GeneradorDeEstadistica();
		generador.generarEstadistica(bicicletas);
		generador.terminar();
		generador.generarEstadistica(bicicletas);
		InformacionEstadistica info = generador.terminar();
		
		List<Integer> idBicicletasMasUsadas = new LinkedList<Integer>();
		idBicicletasMasUsadas.add(1);
		idBicicletasMasUsadas.add(3);
		
		List <Integer> idBicicletasMenosUsadas = new LinkedList <Integer> ();
		idBicicletasMenosUsadas.add(9);
		
		List<Ruta> recorridosEsperados = new LinkedList<Ruta>();
		recorridosEsperados.add(recorridos.get(0).parsearARuta());
		recorridosEsperados.add(recorridos.get(1).parsearARuta());
		
		int tiempoPromedio = 14;		
		
		Assert.assertEquals(idBicicletasMasUsadas, info.bicicletasMasUsadas());
		Assert.assertEquals(idBicicletasMenosUsadas, info.bicicletasMenosUsadas());
		Assert.assertEquals(recorridosEsperados, info.recorridosMasRealizados());
		Assert.assertEquals(tiempoPromedio, info.getTiempoPromedio());
	}
	
	@Test 
	public void generarEstadisticaDebeCrearInformacionEstadisticaConTiempoDe60Test(){
		GeneradorDeEstadistica generador = new GeneradorDeEstadistica();
		List<Bicicleta> bicis = new LinkedList<Bicicleta>();
		Recorrido recorrido1 = new Recorrido(new Estacion(10, "Palermo"), new Estacion(12, "Once"));
		recorrido1.setMinutosRecorridos(60);
		Bicicleta bici1 = new Bicicleta(1, recorrido1);		
		bicis.add(bici1);		
		generador.generarEstadistica(bicis);
		InformacionEstadistica info = generador.terminar();
		
		Assert.assertEquals(new Integer(60), info.getTiempoDeBicicletaMasUsada());
	}
	
	@Test 
	public void generarEstadisticaDebeCrearInformacionEstadisticaConTiempoTotalDeBici1Test(){
		GeneradorDeEstadistica generador = new GeneradorDeEstadistica();
		List<Bicicleta> bicis = new LinkedList<Bicicleta>();
		Recorrido recorrido1 = new Recorrido(new Estacion(10, "Palermo"), new Estacion(12, "Once"));
		recorrido1.setMinutosRecorridos(50);
		Recorrido recorrido2 = new Recorrido(new Estacion(15, "Retiro"), new Estacion(12, "Once"));
		recorrido2.setMinutosRecorridos(150);
		Bicicleta bici1 = new Bicicleta(1, recorrido1);	
		Bicicleta bici2 = new Bicicleta(2, recorrido2);		
		bicis.add(bici1);	
		bicis.add(bici2);
		bicis.add(bici1);  
		//bici1 es la mas usada, hace 2 viajes de 50, total 100
		generador.generarEstadistica(bicis);
		InformacionEstadistica info = generador.terminar();
		
		Assert.assertEquals(new Integer(100), info.getTiempoDeBicicletaMasUsada());
	}
	
	@Test 
	public void generadorDebeCrearInformacionConTiempoMayorDeBiciMasUsadaTest(){
		GeneradorDeEstadistica generador = new GeneradorDeEstadistica();
		List<Bicicleta> bicis = new LinkedList<Bicicleta>();
		Recorrido recorrido1 = new Recorrido(new Estacion(10, "Palermo"), new Estacion(12, "Once"));
		recorrido1.setMinutosRecorridos(50);
		Recorrido recorrido2 = new Recorrido(new Estacion(15, "Retiro"), new Estacion(12, "Once"));
		recorrido2.setMinutosRecorridos(150);
		Bicicleta bici1 = new Bicicleta(1, recorrido1);	
		Bicicleta bici2 = new Bicicleta(2, recorrido2);		
		bicis.add(bici1);	
		bicis.add(bici2);		
		generador.generarEstadistica(bicis);
		InformacionEstadistica info = generador.terminar();
		
		Assert.assertEquals(new Integer(150), info.getTiempoDeBicicletaMasUsada());
	}
		
}