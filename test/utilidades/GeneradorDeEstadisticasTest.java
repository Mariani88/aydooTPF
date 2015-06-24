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

		Recorrido recorrido1 = new Recorrido(estaciones.get(0),
				estaciones.get(1));
		Recorrido recorrido2 = new Recorrido(estaciones.get(2),
				estaciones.get(3));
		Recorrido recorrido3 = new Recorrido(estaciones.get(4),
				estaciones.get(5));

		this.bicicletas.add(new Bicicleta(1, recorrido1));
		this.bicicletas.add(new Bicicleta(3, recorrido2));
		this.bicicletas.add(new Bicicleta(3, recorrido1));
		this.bicicletas.add(new Bicicleta(9, recorrido1));
		this.bicicletas.add(new Bicicleta(1, recorrido3));
		this.bicicletas.add(new Bicicleta(1, recorrido2));
		this.bicicletas.add(new Bicicleta(3, recorrido2));
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
}