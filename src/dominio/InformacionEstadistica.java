package dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import utilidades.RecorridoDTO;

public class InformacionEstadistica {

	private Map <Integer, Integer> bicicletasMasUsadas;
	private Map <Integer, Integer> bicicletasMenosUsadas;
	private Map <RecorridoDTO, Integer> recorridoMasRealizado;
	private int tiempoPromedio;
	
	
	public InformacionEstadistica (){
		
		this.bicicletasMasUsadas = new HashMap <Integer, Integer> ();
		this.bicicletasMenosUsadas = new HashMap <Integer, Integer> ();
		this.recorridoMasRealizado = new HashMap <RecorridoDTO, Integer> ();
		this.tiempoPromedio = 0;
	}


	public void setBicicletasMasUsadas(Map<Integer, Integer> bicicletasMasUsadas) {
		this.bicicletasMasUsadas = bicicletasMasUsadas;
	}


	public void setBicicletasMenosUsadas(Map<Integer, Integer> bicicletasMenosUsadas) {
		this.bicicletasMenosUsadas = bicicletasMenosUsadas;
	}


	public void setRecorridoMasRealizado(
			Map<RecorridoDTO, Integer> recorridoMasRealizado) {
		this.recorridoMasRealizado = recorridoMasRealizado;
	}


	public void setTiempoPromedio(int tiempoPromedio) {
		this.tiempoPromedio = tiempoPromedio;
	}


	public Map<Integer, Integer> getBicicletasMasUsadas() {
		return this.bicicletasMasUsadas;
	}


	public Map<Integer, Integer> getBicicletasMenosUsadas() {
		return this.bicicletasMenosUsadas;
	}


	public Map<RecorridoDTO, Integer> getRecorridoMasRealizado() {
		return this.recorridoMasRealizado;
	}


	public int getTiempoPromedio() {
		return this.tiempoPromedio;
	}


	public List<Integer> bicicletasMasUsadas() {
		
		List <Integer> bicicletas = new LinkedList <Integer> ();
		bicicletas.addAll(this.bicicletasMasUsadas.keySet());
		
		return bicicletas;
	}


	public List <Integer> bicicletasMenosUsadas() {
		
		List <Integer> bicicletas = new LinkedList <Integer> ();
		bicicletas.addAll(this.bicicletasMenosUsadas.keySet());
		
		return bicicletas;
	}


	public List <RecorridoDTO> recorridosMasRealizados() {
		
		List <RecorridoDTO> recorridosMasRealizados = new LinkedList <RecorridoDTO>();
		recorridosMasRealizados.addAll(this.recorridoMasRealizado.keySet());
		
		return recorridosMasRealizados;
	}	
}