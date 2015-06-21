package utilidades;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class LectorDeArchivos {

	public ZipFile[] getArchivosZip(String path) throws ZipException, IOException {
		
		File dir = new File(path);
		File[] listaArchivosEnDirectorio = dir.listFiles();
		ZipFile[] archivoZip = new ZipFile[listaArchivosEnDirectorio.length];
		
		for(int i= 0; i< listaArchivosEnDirectorio.length ; i++){
			archivoZip[i] = new ZipFile(listaArchivosEnDirectorio[i]);
		}
		return archivoZip;
	}

	public Enumeration<? extends ZipEntry> descomprimirArchivosDeZip(ZipFile zipFile) {
		
		Enumeration<? extends ZipEntry> listaDeArchivosCSVEnZip =  zipFile.entries();
		
		return listaDeArchivosCSVEnZip;
	}

	public void validarQueElArchivoSeaCSV(ZipEntry archivoCSV) {
		String name = archivoCSV.getName();
		if(!name.contains(".csv")){
			throw new IllegalArgumentException("El archivo con el nombre "+name+
					" no es un archivo csv");
		}
	}
	
	
	
	
	
}
