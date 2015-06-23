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

	/**
	 * guarda el id de la bicicleta mas usada y su cantidad de forma arbitraria.
	 * 
	 * @param id
	 * @param cantidad
	 * @throws BicicletaMasUsadaExcepcion si cantidad no es un maximo
	 */
	public void guardarBicicletasMasUsadas(int id, int cantidad) {
		
		boolean esMaximo = this.guardarMaximoBicicleta ( id, cantidad);
		
		if (!esMaximo) throw new BicicletaMasUsadaExcepcion ();
	}


	private boolean guardarMaximoBicicleta(int id, int cantidad) {
		
		Iterator<Entry<Integer, Integer>> iterador = this.bicicletasMasUsadas
				.entrySet().iterator();
		Entry<Integer, Integer> primerPar = iterador.next();
	
		boolean guardado = false;
		
		if ( cantidad == primerPar.getValue() ){
			this.bicicletasMasUsadas.put(id, cantidad);
			guardado = true;
			
		}else if (cantidad > primerPar.getValue() ){
			this.bicicletasMasUsadas.clear();
			this.bicicletasMasUsadas.put(id, cantidad);
			guardado = true;
		}	
		return guardado;
	}


	/**
	 * guarda el id de la bicicleta menos usada y su cantidad de forma arbitraria.
	 * 
	 * @param id
	 * @param cantidad
	 * @throws BicicletaMenosUsadaExcepcion si cantidad no es un minimo
	 */
	public void guardarBicicletasMenosUsadas(int id, int cantidad) {
		
		boolean esMinimo = this.guardarMinimoBicicleta (id, cantidad);
		
		if (!esMinimo) throw new BicicletaMenosUsadaExcepcion ();
	
	}


	private boolean guardarMinimoBicicleta(int id, int cantidad) {
		
		Iterator<Entry<Integer, Integer>> iterador = this.bicicletasMenosUsadas
				.entrySet().iterator();
		Entry<Integer, Integer> primerPar = iterador.next();
		
		boolean guardado = false;
		
		if ( cantidad == primerPar.getValue()){
			this.bicicletasMenosUsadas.put(id, cantidad);
			guardado = true;
		}else if(cantidad < primerPar.getValue()){
			this.bicicletasMenosUsadas.clear();
			this.bicicletasMenosUsadas.put(id, cantidad);
			guardado = true;
		}
		
		return guardado;
	}


	public void guardarRecorridoMasRealizado(RecorridoDTO recorrido, int cantidad) {
	
		boolean esMaximo = this.guardarMaximoRecorrido (recorrido, cantidad);
		
		if (!esMaximo) throw new RecorridoMasRealizadoExcepcion ();
	}


	private boolean guardarMaximoRecorrido(RecorridoDTO recorrido, int cantidad) {
		
		boolean guardado = false;
		
		Iterator<Entry<RecorridoDTO, Integer>> iterador = this.recorridoMasRealizado
				.entrySet().iterator();
		Entry<RecorridoDTO, Integer> primerPar = iterador.next();
		
		if ( cantidad == primerPar.getValue()){
			this.recorridoMasRealizado.put(recorrido, cantidad);
			guardado = true;
		}else if(cantidad > primerPar.getValue()){
			this.recorridoMasRealizado.clear();
			this.recorridoMasRealizado.put(recorrido, cantidad);
			guardado = true;
		}
		
		return guardado;
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


	public void evaluarUsoBicicleta(int id, int cantidad) {
		
		this.guardarMaximoBicicleta(id, cantidad);
		this.guardarMinimoBicicleta(id, cantidad);
	}


	public void evaluarRecorrido(RecorridoDTO recorrido, int cantidad) {
		this.guardarMaximoRecorrido(recorrido, cantidad);
	}	
}