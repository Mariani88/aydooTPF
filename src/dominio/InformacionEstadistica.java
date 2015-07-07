package dominio;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import excepciones.BicicletaMasUsadaExcepcion;
import excepciones.BicicletaMenosUsadaExcepcion;
import excepciones.RecorridoMasRealizadoExcepcion;
import utilidades.Ruta;

public class InformacionEstadistica {

	private Map <Integer, Integer> bicicletasMasUsadas;
	private Map <Integer, Integer> bicicletasMenosUsadas;
	private Map <Ruta, Integer> recorridoMasRealizado;
	private int tiempoPromedio;
	private Set<Bicicleta> bicicletasUsadasMasTiempo;
	private Integer tiempoDeBicicletaMasUsada = 0;
	
	
	public InformacionEstadistica (){
		
		this.bicicletasMasUsadas = new HashMap <Integer, Integer> ();
		this.bicicletasMasUsadas.put(-1, 0);
		this.bicicletasMenosUsadas = new HashMap <Integer, Integer> ();
		this.bicicletasMenosUsadas.put(-1, 2147483647);
		this.recorridoMasRealizado = new HashMap <Ruta, Integer> ();
		this.recorridoMasRealizado.put(new Ruta (0,0), 0);
		this.bicicletasUsadasMasTiempo = new TreeSet<Bicicleta>();		
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


	public void evaluarUsoBicicleta(int id, int cantidad) {

		this.guardarMaximoBicicleta(id, cantidad);

		boolean existe = this.bicicletasMenosUsadas.containsKey(id);
		boolean hayOtroId = this.bicicletasMenosUsadas.size() > 0;

		if (existe && hayOtroId) {
			
			this.bicicletasMenosUsadas.remove(id);
			
		} else if (!existe && !hayOtroId) {
			
			this.bicicletasMenosUsadas.put(id, cantidad);
			
		} else
			
			this.guardarMinimoBicicleta(id, cantidad);
	}

	public void evaluarRecorrido(Ruta ruta, int cantidad) {
		this.guardarMaximoRecorrido(ruta, cantidad);
	}
	
	public void generarBicicletasUsadasMasTiempo(Map <Bicicleta, Integer> bicicletas){
		
		Integer tiempoMaximo = Collections.max(bicicletas.values());		
		
		for (Map.Entry<Bicicleta, Integer> b : bicicletas.entrySet()) {
			Bicicleta bici = b.getKey();
			Integer tiempo = b.getValue();
			if (tiempo.equals(tiempoMaximo)) {				
				this.bicicletasUsadasMasTiempo.add(bici);
			}			
		}

	}
	
	public Set<Bicicleta> getBicicletasUsadasMasTiempo(){
		return this.bicicletasUsadasMasTiempo;
	}
	
	public Integer getTiempoDeBicicletaMasUsada(){
		return this.tiempoDeBicicletaMasUsada;
	}


	public void setTiempoDeBicicletaMasUsada(Integer tiempo) {
		this.tiempoDeBicicletaMasUsada = tiempo;
	}
}