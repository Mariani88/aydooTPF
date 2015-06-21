package utiles;

import java.io.File;

public class LectorDeArchivos {

	public File[] getListaDeArchivos(String path) {
		
		File dir = new File(path);
		File[] listaArchivosEnDirectorio = dir.listFiles();
		
		return listaArchivosEnDirectorio;
	}

}
