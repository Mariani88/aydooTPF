package dominio;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import excepciones.BicicletaMasUsadaExcepcion;
import excepciones.BicicletaMenosUsadaExcepcion;
import excepciones.RecorridoMasRealizadoExcepcion;
import utilidades.RecorridoDTO;

public class InformacionEstadistica {

	private Map <Integer, Integer> bicicletasMasUsadas;
	private Map <Integer, Integer> bicicletasMenosUsadas;
	private Map <RecorridoDTO, Integer> recorridoMasRealizado;
	private int tiempoPromedio;
	
	
	public InformacionEstadistica (){
		
		this.bicicletasMasUsadas = new HashMap <Integer, Integer> ();
		this.bicicletasMasUsadas.put(0, 0);
		this.bicicletasMenosUsadas = new HashMap <Integer, Integer> ();
		this.bicicletasMenosUsadas.put(0, 2147483647);
		this.recorridoMasRealizado = new HashMap <RecorridoDTO, Integer> ();
		this.recorridoMasRealizado.put(new RecorridoDTO (0,0), 0);
		this.tiempoPromedio = 0;
	}


	public void setTiempoPromedio(int tiempoPromedio) {
		this.tiempoPromedio = tiempoPromedio;
	}


	public void guardarBicicletasMasUsadas(int id, int cantidad) {
		
		Iterator<Entry<Integer, Integer>> iterador = this.bicicletasMasUsadas
				.entrySet().iterator();
		Entry<Integer, Integer> primerPar = iterador.next();
		
		if ( cantidad == primerPar.getValue() ){
			this.bicicletasMasUsadas.put(id, cantidad);
		
		}else if (cantidad > primerPar.getValue() ){
			this.bicicletasMasUsadas.clear();
			this.bicicletasMasUsadas.put(id, cantidad);
		}	
		else
			throw new BicicletaMasUsadaExcepcion ();
	}


	public void guardarBicicletasMenosUsadas(int id, int cantidad) {
		
		Iterator<Entry<Integer, Integer>> iterador = this.bicicletasMenosUsadas
				.entrySet().iterator();
		Entry<Integer, Integer> primerPar = iterador.next();
		
		if ( cantidad == primerPar.getValue()){
			this.bicicletasMenosUsadas.put(id, cantidad);
			
		}else if(cantidad < primerPar.getValue()){
			this.bicicletasMenosUsadas.clear();
			this.bicicletasMenosUsadas.put(id, cantidad);
		}else{
			throw new BicicletaMenosUsadaExcepcion ();
		}
	}


	public void guardarRecorridoMasRealizado(RecorridoDTO recorrido, int cantidad) {
	
		Iterator<Entry<RecorridoDTO, Integer>> iterador = this.recorridoMasRealizado
				.entrySet().iterator();
		Entry<RecorridoDTO, Integer> primerPar = iterador.next();
		
		if ( cantidad == primerPar.getValue()){
			this.recorridoMasRealizado.put(recorrido, cantidad);
			
		}else if(cantidad > primerPar.getValue()){
			this.recorridoMasRealizado.clear();
			this.recorridoMasRealizado.put(recorrido, cantidad);
		}else{
			throw new RecorridoMasRealizadoExcepcion ();
		}
		
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