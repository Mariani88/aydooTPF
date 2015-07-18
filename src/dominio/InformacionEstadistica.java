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
import utilidades.Ruta;

public class InformacionEstadistica {

	private Map <Integer, Integer> bicicletasMasUsadas;
	private Map <Integer, Integer> bicicletasMenosUsadas;
	private Map <Ruta, Integer> recorridoMasRealizado;
	private int tiempoPromedio;
	
	
	public InformacionEstadistica (){
		
		this.bicicletasMasUsadas = new HashMap <Integer, Integer> ();
		this.bicicletasMasUsadas.put(-1, 0);
		this.bicicletasMenosUsadas = new HashMap <Integer, Integer> ();
		this.bicicletasMenosUsadas.put(-1, 2147483647);
		this.recorridoMasRealizado = new HashMap <Ruta, Integer> ();
		this.recorridoMasRealizado.put(new Ruta (0,0), 0);
		this.tiempoPromedio = -1;
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


	public void guardarRecorridoMasRealizado(Ruta ruta, int cantidad) {
	
		boolean esMaximo = this.guardarMaximoRecorrido (ruta, cantidad);
		
		if (!esMaximo) throw new RecorridoMasRealizadoExcepcion ();
	}


	private boolean guardarMaximoRecorrido(Ruta ruta, int cantidad) {
		
		boolean guardado = false;
		
		Iterator<Entry<Ruta, Integer>> iterador = this.recorridoMasRealizado
				.entrySet().iterator();
		Entry<Ruta, Integer> primerPar = iterador.next();
		
		if ( cantidad == primerPar.getValue()){
			this.recorridoMasRealizado.put(ruta, cantidad);
			guardado = true;
		}else if(cantidad > primerPar.getValue()){
			this.recorridoMasRealizado.clear();
			this.recorridoMasRealizado.put(ruta, cantidad);
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


	public List <Ruta> recorridosMasRealizados() {
		
		List <Ruta> recorridosMasRealizados = new LinkedList <Ruta>();
		recorridosMasRealizados.addAll(this.recorridoMasRealizado.keySet());
		
		return recorridosMasRealizados;
	}


	public void evaluarUsoBicicleta(Map <Integer, Integer> historialBicicletas) {

		Iterator <Entry<Integer,Integer>> iterador = historialBicicletas.entrySet().iterator();
		
		while (iterador.hasNext()){
			Entry<Integer,Integer> par = iterador.next();
			this.guardarMaximoBicicleta(par.getKey(), par.getValue());
			this.guardarMinimoBicicleta(par.getKey(), par.getValue());
		}
	}

	public void evaluarRecorridos(Map <Ruta, Integer> historialRecorridos) {
		
		Iterator <Entry<Ruta, Integer>> iterador = historialRecorridos.entrySet().iterator();
		
		while ( iterador.hasNext()){
			
			Entry<Ruta,Integer> par = iterador.next();
			this.guardarMaximoRecorrido(par.getKey(), par.getValue());	
		}
	}
}