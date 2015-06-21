package dominio;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class InformacionEstadisticaTest {

	private InformacionEstadistica info = new InformacionEstadistica();
	
	@Test
	public void getBicicletasMasUsadasDebeDevolverListaDeBicicletasMasUsadas(){
		
		List <Integer> bicicletasMasUsadasEsperadas = new LinkedList<Integer>();
		
		this.info.getBicicletasMasUsadas().put(3, 40);
		this.info.getBicicletasMasUsadas().put(4, 40);
		
		bicicletasMasUsadasEsperadas.add(3);
		bicicletasMasUsadasEsperadas.add(4);
		
		Assert.assertEquals(bicicletasMasUsadasEsperadas, this.info.bicicletasMasUsadas());
	}
	
	
	@Test
	public void getBicicletasMenosUsadasDebeDevolverListaDeBicicletasMenosUsadas(){
		
		List <Integer> bicicletasMenosUsadasEsperadas = new LinkedList<Integer>();
		
		this.info.getBicicletasMenosUsadas().put(3, 40);
		this.info.getBicicletasMenosUsadas().put(4, 40);
		
		bicicletasMenosUsadasEsperadas.add(3);
		bicicletasMenosUsadasEsperadas.add(4);
		
		Assert.assertEquals(bicicletasMenosUsadasEsperadas, this.info.bicicletasMenosUsadas());		
	}
	
	@Test
	public void getRecorridosMasUsados (){
		
		
		
		
		
		
		
		
	}
	
	
	
}