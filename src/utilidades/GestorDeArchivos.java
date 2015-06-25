package utilidades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import dominio.InformacionEstadistica;
import excepciones.DirectorioNoExisteExcepcion;

public class GestorDeArchivos {

	private String directorioDeDescompresion;
	private String directorioDeTrabajo;
	private String directorioArchivosProcesados;
	
	public GestorDeArchivos (String directorioDeTrabajo) throws DirectorioNoExisteExcepcion{
		
		File file = new File (directorioDeTrabajo);
		
		if ( file.exists()) {
			this.directorioDeTrabajo = directorioDeTrabajo;
		}
		else throw new DirectorioNoExisteExcepcion();
	}

	public void crearYMLCon(InformacionEstadistica info) throws IOException {
		
		File archivoYML = new File (this.directorioDeTrabajo + "/estadisticas.yml");
		FileWriter fw = new FileWriter (archivoYML);
		BufferedWriter bw = new BufferedWriter (fw);
		PrintWriter pw = new PrintWriter (bw);
		
		pw.println("Bicicletas mas usadas: ");
		this.escribirIdsBicicletasMaximas(info, pw);
		
		pw.println("Bicicletas menos usadas: ");
		this.escribirIdsBicicletasMinimas(info, pw);
		
		pw.println("Recorridos mas realizados: ");
		this.escribirIdRecorridosMasRealizados (info, pw);
		
		pw.println("Tiempo promedio de uso: " + info.getTiempoPromedio());
		pw.close();
	}

	private void escribirIdRecorridosMasRealizados(InformacionEstadistica info,
			PrintWriter pw) {
		
		Iterator <RecorridoDTO> iterador = info.recorridosMasRealizados().iterator();
		
		while (iterador.hasNext()){
			
			RecorridoDTO recorrido = iterador.next();
			pw.println("id origen:" + recorrido.getIdEstacionOrigen());
			pw.println("id destino" + recorrido.getIdEstacionDestino());
			pw.println("");
		}
	}

	private void escribirIdsBicicletasMinimas(InformacionEstadistica info,
			PrintWriter pw) {
		
		Iterator <Integer> iterador = info.bicicletasMenosUsadas().iterator();
		
		while (iterador.hasNext()){
			pw.println("id:" + iterador.next());
		}
		
		pw.println("");
		
	}

	private void escribirIdsBicicletasMaximas(InformacionEstadistica info,
			PrintWriter pw) {
		
		Iterator <Integer> iterador = info.bicicletasMasUsadas().iterator();
		
		while (iterador.hasNext()){
			pw.println("id:" + iterador.next());
		}
		
		pw.println("");
	}	
}