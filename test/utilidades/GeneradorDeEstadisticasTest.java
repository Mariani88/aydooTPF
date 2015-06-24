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

public class GeneradorDeEstadisticasTest {

	private List<Bicicleta> bicicletas = new LinkedList<Bicicleta>();
	private List<Recorrido> recorridos = new LinkedList<Recorrido>();
	
	public List<Estacion> cargarEstaciones() {

		List<Estacion> estaciones = new LinkedList<Estacion>();
		estaciones.add(new Estacion(20, "O"));
		estaciones.add(new Estacion(30, "D"));
		estaciones.add(new Estacion(11, "O"));
		estaciones.add(new Estacion(22, "D"));
		estaciones.add(new Estacion(100, "O"));
		estaciones.add(new Estacion(200, "D"));

		return estaciones;
	}
	
	public void cargarRecorridos (){
		
		List<Estacion> estaciones = this.cargarEstaciones();

		this.recorridos.add( new Recorrido(estaciones.get(0),
				estaciones.get(1)) );
		this.recorridos.add( new Recorrido(estaciones.get(2),
				estaciones.get(3)) );
		this.recorridos.add( new Recorrido(estaciones.get(4),
				estaciones.get(5)) );
		
		this.recorridos.get(0).setMinutosRecorridos(11);
		this.recorridos.get(1).setMinutosRecorridos(20);
		this.recorridos.get(2).setMinutosRecorridos(10);
	}

	@Before
	public void cargarDatos() {

		this.cargarRecorridos();
		this.bicicletas.add(new Bicicleta(1, this.recorridos.get(0)));
		this.bicicletas.add(new Bicicleta(3, this.recorridos.get(1)));
		this.bicicletas.add(new Bicicleta(3, this.recorridos.get(0)));
		this.bicicletas.add(new Bicicleta(9, this.recorridos.get(0)));
		this.bicicletas.add(new Bicicleta(1, this.recorridos.get(2)));
		this.bicicletas.add(new Bicicleta(1, this.recorridos.get(1)));
		this.bicicletas.add(new Bicicleta(3, this.recorridos.get(1)));
	}

	@Test
	public void generarEstadisticasDebeObtenerBicicletaMasUsada() {
		
		GeneradorDeEstadistica generador = new GeneradorDeEstadistica();
		List<Integer> idBicicletasMasUsadas = new LinkedList<Integer>();
		idBicicletasMasUsadas.add(1);
		idBicicletasMasUsadas.add(3);

		generador.generarEstadistica(this.bicicletas);

		Assert.assertEquals(idBicicletasMasUsadas, generador.terminar()
				.bicicletasMasUsadas());
	}
	
	@Test
	public void generarEstadisticasDebeObtenerBicicletaMenosUsada() {
		
		GeneradorDeEstadistica generador = new GeneradorDeEstadistica();
		List<Integer> idBicicletasMenosUsadas = new LinkedList<Integer>();
		idBicicletasMenosUsadas.add(9);
		
		generador.generarEstadistica(this.bicicletas);

		Assert.assertEquals(idBicicletasMenosUsadas, generador.terminar()
				.bicicletasMenosUsadas());
	}
	
	@Test
	public void generarEstadisticasDebeObtenerRecorridoMasRealizado(){
		
		GeneradorDeEstadistica generador = new GeneradorDeEstadistica();
		List<RecorridoDTO> recorridosEsperados = new LinkedList<RecorridoDTO>();
		
		recorridosEsperados.add(Recorrido.parsearADTO(this.recorridos.get(0)));
		recorridosEsperados.add(Recorrido.parsearADTO(this.recorridos.get(1)));
		
		generador.generarEstadistica(this.bicicletas);
		Assert.assertEquals(recorridosEsperados, generador.terminar().recorridosMasRealizados());
	}
	
	
	@Test
	public void generarEstadisticasDebeObtenerPromedioDeUsoDeBicicletas(){
		
		GeneradorDeEstadistica generador = new GeneradorDeEstadistica();
		generador.generarEstadistica(this.bicicletas);
		
		int tiempoPromedio = 14;		
		Assert.assertEquals(tiempoPromedio, generador.terminar().getTiempoPromedio());
	}	
	
	
	@Test
	public void terminarDebeDevolverInformacionYReiniciarParametros (){
		
		GeneradorDeEstadistica generador = new GeneradorDeEstadistica();
		generador.generarEstadistica(this.bicicletas);

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
		generador.generarEstadistica(this.bicicletas);
		generador.terminar();
		generador.generarEstadistica(this.bicicletas);
		InformacionEstadistica info = generador.terminar();
		
		List<Integer> idBicicletasMasUsadas = new LinkedList<Integer>();
		idBicicletasMasUsadas.add(1);
		idBicicletasMasUsadas.add(3);
		
		List <Integer> idBicicletasMenosUsadas = new LinkedList <Integer> ();
		idBicicletasMenosUsadas.add(9);
		
		List<RecorridoDTO> recorridosEsperados = new LinkedList<RecorridoDTO>();
		recorridosEsperados.add(Recorrido.parsearADTO(this.recorridos.get(0)));
		recorridosEsperados.add(Recorrido.parsearADTO(this.recorridos.get(1)));
		
		int tiempoPromedio = 14;		
		
		Assert.assertEquals(idBicicletasMasUsadas, info.bicicletasMasUsadas());
		Assert.assertEquals(idBicicletasMenosUsadas, info.bicicletasMenosUsadas());
		Assert.assertEquals(recorridosEsperados, info.recorridosMasRealizados());
		Assert.assertEquals(tiempoPromedio, info.getTiempoPromedio());
	}
}