package dominio;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import excepciones.BicicletaMasUsadaExcepcion;
import excepciones.BicicletaMenosUsadaExcepcion;
import excepciones.RecorridoMasRealizadoExcepcion;
import utilidades.RecorridoDTO;

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

		List<RecorridoDTO> recorridosMasRealizados = new LinkedList<RecorridoDTO>();

		this.info.guardarRecorridoMasRealizado(new RecorridoDTO(1, 1), 50);
		this.info.guardarRecorridoMasRealizado(new RecorridoDTO(1, 2), 50);

		recorridosMasRealizados.add(new RecorridoDTO(1, 1));
		recorridosMasRealizados.add(new RecorridoDTO(1, 2));
		
		Assert.assertEquals(recorridosMasRealizados,
				this.info.recorridosMasRealizados());	
	}
	
	
	@Test
	public void RecorridosMasRelizadosNoDebeDevolverMaximosViejos (){
		
		List<RecorridoDTO> recorridosMasRealizados = new LinkedList<RecorridoDTO>();

		this.info.guardarRecorridoMasRealizado(new RecorridoDTO(1, 1), 50);
		this.info.guardarRecorridoMasRealizado(new RecorridoDTO(1, 2), 50);
		
		this.info.guardarRecorridoMasRealizado(new RecorridoDTO(10, 10), 100);
		
		recorridosMasRealizados.add(new RecorridoDTO(10, 10));
		
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
		this.info.guardarRecorridoMasRealizado(new RecorridoDTO (1,2), 3);
		this.info.guardarRecorridoMasRealizado(new RecorridoDTO (3,4), 1);
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
		
		this.info.guardarRecorridoMasRealizado(new RecorridoDTO (1,1),200);
		
		List<RecorridoDTO> recorridosEsperados = new LinkedList<RecorridoDTO> ();
		recorridosEsperados.add(new RecorridoDTO (2,2));
		this.info.evaluarRecorrido(new RecorridoDTO (2,2), 3000);
		
		Assert.assertEquals(recorridosEsperados, this.info.recorridosMasRealizados());	
	}
}