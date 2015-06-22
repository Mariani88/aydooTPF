package dominio;

import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

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
	public void getBicicletasMenosUsadasDebeDevolverListaDeBicicletasMenosUsadas() {

		List<Integer> bicicletasMenosUsadasEsperadas = new LinkedList<Integer>();

		this.info.guardarBicicletasMenosUsadas(3, 40);
		this.info.guardarBicicletasMenosUsadas(4, 40);

		bicicletasMenosUsadasEsperadas.add(3);
		bicicletasMenosUsadasEsperadas.add(4);

		Assert.assertEquals(bicicletasMenosUsadasEsperadas,
				this.info.bicicletasMenosUsadas());
	}

	@Test
	public void getRecorridosMasRelizados() {

		List<RecorridoDTO> recorridosMasRealizados = new LinkedList<RecorridoDTO>();

		this.info.guardarRecorridoMasRealizado(new RecorridoDTO(1, 1), 50);
		this.info.guardarRecorridoMasRealizado(new RecorridoDTO(1, 2), 50);

		recorridosMasRealizados.add(new RecorridoDTO(1, 1));
		recorridosMasRealizados.add(new RecorridoDTO(1, 2));
		
		Assert.assertEquals(recorridosMasRealizados,
				this.info.recorridosMasRealizados());		
	}
	
	
	
	
	
}