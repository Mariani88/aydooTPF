package utilidades;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dominio.Bicicleta;
import dominio.Estacion;
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

	@Before
	public void cargarDatos() {

		List<Estacion> estaciones = this.cargarEstaciones();

		this.recorridos.add( new Recorrido(estaciones.get(0),
				estaciones.get(1)) );
		this.recorridos.add( new Recorrido(estaciones.get(2),
				estaciones.get(3)) );
		this.recorridos.add( new Recorrido(estaciones.get(4),
				estaciones.get(5)) );

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
	
	/*@Test
	public void generarEstadisticasDebeObtenerRecorridoMasRealizado(){
		
		GeneradorDeEstadistica generador = new GeneradorDeEstadistica();
		List<RecorridoDTO> recorridosEsperados = new LinkedList<RecorridoDTO>();
		
		int origen = this.recorridos.get(0).getEstacionOrigen().getId();
		int destino = this.recorridos.get(0).getEstacionDestino().getId();
		recorridosEsperados.add(new RecorridoDTO( origen, destino));
		
		origen = this.recorridos.get(1).getEstacionOrigen().getId();
		destino = this.recorridos.get(1).getEstacionDestino().getId();
		recorridosEsperados.add(new RecorridoDTO (origen, destino));
		
		generador.generarEstadistica(this.bicicletas);
		Assert.assertEquals(recorridosEsperados, generador.terminar().recorridosMasRealizados());
	}*/
	
	
	
	
}