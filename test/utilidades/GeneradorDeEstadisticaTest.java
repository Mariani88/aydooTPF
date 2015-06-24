package utilidades;

import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import dominio.Bicicleta;
import dominio.Estacion;
import dominio.InformacionEstadistica;
import dominio.Recorrido;

public class GeneradorDeEstadisticaTest {

	private static List<Bicicleta> bicicletas = new LinkedList<Bicicleta>();
	private static List<Recorrido> recorridos = new LinkedList<Recorrido>();
	
	
	public static List<Estacion> cargarEstaciones() {

		List<Estacion> estaciones = new LinkedList<Estacion>();
		estaciones.add(new Estacion(20, "O"));
		estaciones.add(new Estacion(30, "D"));
		estaciones.add(new Estacion(11, "O"));
		estaciones.add(new Estacion(22, "D"));
		estaciones.add(new Estacion(100, "O"));
		estaciones.add(new Estacion(200, "D"));

		return estaciones;
	}
	
	
	public static void cargarRecorridos (){
		
		List<Estacion> estaciones = cargarEstaciones();

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

	@BeforeClass
	public static void cargarDatos() {

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
		List<RecorridoDTO> recorridosEsperados = new LinkedList<RecorridoDTO>();
		
		recorridosEsperados.add(Recorrido.parsearADTO(recorridos.get(0)));
		recorridosEsperados.add(Recorrido.parsearADTO(recorridos.get(1)));
		
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

		List<RecorridoDTO> idRecorridoInicializado = new LinkedList<RecorridoDTO>();
		idRecorridoInicializado.add(new RecorridoDTO(0, 0));

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
		
		List<RecorridoDTO> recorridosEsperados = new LinkedList<RecorridoDTO>();
		recorridosEsperados.add(Recorrido.parsearADTO(recorridos.get(0)));
		recorridosEsperados.add(Recorrido.parsearADTO(recorridos.get(1)));
		
		int tiempoPromedio = 14;		
		
		Assert.assertEquals(idBicicletasMasUsadas, info.bicicletasMasUsadas());
		Assert.assertEquals(idBicicletasMenosUsadas, info.bicicletasMenosUsadas());
		Assert.assertEquals(recorridosEsperados, info.recorridosMasRealizados());
		Assert.assertEquals(tiempoPromedio, info.getTiempoPromedio());
	}
}