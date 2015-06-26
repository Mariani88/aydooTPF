package utilidades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import dominio.InformacionEstadistica;

public class GestorDeArchivos {
	
	public ZipFile[] obtenerArchivosZip(String path) throws ZipException, IOException {
		File directorio = new File(path);
		comprobarPath(directorio.toPath());
		File[] listaArchivosEnDirectorio = directorio.listFiles();
		ZipFile[] archivoZip = new ZipFile[listaArchivosEnDirectorio.length];
		
		for(int i= 0; i< listaArchivosEnDirectorio.length ; i++){
			archivoZip[i] = new ZipFile(listaArchivosEnDirectorio[i]);
		}
		return archivoZip;
	}

	public Enumeration<? extends ZipEntry> leerArchivosCSVContenidosEnZip(ZipFile zipFiles) {
		Enumeration<? extends ZipEntry> listaDeArchivosCSVEnZip =  zipFiles.entries();
		
		return listaDeArchivosCSVEnZip;
	}

	public void validarQueElArchivoSeaCSV(ZipEntry archivoCSV) {
		String name = archivoCSV.getName();
		if(!name.contains(".csv")){
			throw new IllegalArgumentException("El archivo con el nombre "+name+
					" no es un archivo csv");
		}
	}

	private void comprobarPath(Path path) {
		try{
			Boolean elDirectorioEsValido = ((Boolean) Files.getAttribute(path,
					"basic:isDirectory", LinkOption.NOFOLLOW_LINKS));
			if (!elDirectorioEsValido) {
				throw new IllegalArgumentException("El Path: "+ path + " no es un directorio");
			}
		} catch (IOException ioe) {
			throw new IllegalArgumentException("El directorio con el path: "+path+"no existe" );
		}
	}
	
	public void crearYMLCon(InformacionEstadistica info, String directorioDeTrabajo) throws IOException {
		
		File archivoYML = new File (directorioDeTrabajo + "/estadisticas.yml");
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