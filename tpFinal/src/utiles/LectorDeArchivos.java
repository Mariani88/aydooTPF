package utiles;

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

	public Enumeration<? extends ZipEntry> descomprimirZip(ZipFile zipFile) {
		
		return zipFile.entries();
	}
}
