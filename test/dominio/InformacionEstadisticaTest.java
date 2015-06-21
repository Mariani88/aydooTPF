package dominio;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class InformacionEstadisticaTest {

	@Test
	public void getBicicletasMasUsadasDebeDevolverListaDeBicicletasMasUsadas(){
		
		InformacionEstadistica info = new InformacionEstadistica();
		List <Integer> bicicletasMasUsadasEsperadas = new LinkedList<Integer>();
		
		Map <Integer,Integer> bicicletasMasUsadas = new HashMap <Integer, Integer>();
		info.getBicicletasMasUsadas().put(3, 40);
		info.getBicicletasMasUsadas().put(4, 40);
		
		bicicletasMasUsadasEsperadas.add(3);
		bicicletasMasUsadasEsperadas.add(4);
		
		Assert.assertEquals(bicicletasMasUsadasEsperadas, info.bicicletasMasUsadas());
	}
	
	
}
