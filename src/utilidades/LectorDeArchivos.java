package utilidades;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class LectorDeArchivos {

	public ZipFile[] getArchivosZip(String path) throws ZipException, IOException {
		
		File directorio = new File(path);
		comprobarPath(directorio.toPath());
		File[] listaArchivosEnDirectorio = directorio.listFiles();
		ZipFile[] archivoZip = new ZipFile[listaArchivosEnDirectorio.length];
		
		for(int i= 0; i< listaArchivosEnDirectorio.length ; i++){
			archivoZip[i] = new ZipFile(listaArchivosEnDirectorio[i]);
		}
		return archivoZip;
	}

	public Enumeration<? extends ZipEntry> leerArchivosCSVContenidosEnZip(ZipFile zipFile) {
		
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
	
	private void comprobarPath(Path path) {
		try{
			Boolean elDirectorioEsValido = ((Boolean) Files.getAttribute(path,
					"basic:isDirectory", LinkOption.NOFOLLOW_LINKS));
			if (!elDirectorioEsValido) {
				throw new IllegalArgumentException("El Path: "+ path + " no es un directorio");
			}
		} catch (IOException ioe) {
			// El directorio no existe.
			ioe.printStackTrace();
		}
	}
}
