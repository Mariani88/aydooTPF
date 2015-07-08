package utilidades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import dominio.Bicicleta;
import dominio.InformacionEstadistica;

public class GestorDeArchivos {

	private Enumeration<? extends ZipEntry> archivosCSV;
	private ZipEntry archivoCSV;
	private ZipFile archivoZip;
	private Boolean esPrimeraLecturaDelArchivoZip;
	private Boolean esPrimeraLecturaDelArchivoCSV;

	private ZipFile[] filtrarArchivos(File[] listaArchivosEnDirectorio) throws ZipException, IOException {

		List<ZipFile> listaDeZips = new LinkedList<ZipFile>();

		for (int i = 0; i < listaArchivosEnDirectorio.length; i++) {
			boolean esZip = listaArchivosEnDirectorio[i].getName().contains(".zip");

			if (esZip)
				listaDeZips.add(new ZipFile(listaArchivosEnDirectorio[i]));
		}

		return listaDeZips.toArray(new ZipFile[listaDeZips.size()]);
	}

	public ZipFile[] obtenerArchivosZip(String path) throws ZipException, IOException {
		File directorio = new File(path);
		comprobarPath(directorio.toPath());
		File[] listaArchivosEnDirectorio = directorio.listFiles();
		ZipFile[] archivosZip = this.filtrarArchivos(listaArchivosEnDirectorio);

		return archivosZip;
	}

	public ZipFile getArchivoZip() {
		return archivoZip;
	}

	public void asignarArchivoZipParaProcesar(ZipFile archivoZip) {
		esPrimeraLecturaDelArchivoZip = Boolean.TRUE;
		esPrimeraLecturaDelArchivoCSV = Boolean.TRUE;
		this.archivoZip = archivoZip;
	}

	public List<Bicicleta> obtenerListaDeBicicletas() throws IOException {
		List<Bicicleta> bicicletas = new ArrayList<Bicicleta>();
		if (esPrimeraLecturaDelArchivoZip) {
			if (archivoZip == null) {
				return null;
			}
			archivosCSV = leerArchivosCSVContenidosEnZip(archivoZip);
			esPrimeraLecturaDelArchivoZip = Boolean.FALSE;
		}
		if (esPrimeraLecturaDelArchivoCSV) {
			archivoCSV = archivosCSV.nextElement();
			InputStream stream = archivoZip.getInputStream(archivoCSV);
			esPrimeraLecturaDelArchivoCSV = Boolean.FALSE;
			LectorDeBicicletas lector = new LectorDeBicicletas();
			bicicletas = lector.leer(stream);
		}

		if (archivosCSV.hasMoreElements()) {
			esPrimeraLecturaDelArchivoCSV = Boolean.TRUE;
		}

		return bicicletas;
	}

	public void crearYMLCon(InformacionEstadistica info, String directorioDeTrabajo) throws IOException {
		File directorio = new File(directorioDeTrabajo);

		if (!directorio.exists()) {
			directorio.mkdir();
		}

		File archivoYML = new File(directorioDeTrabajo + "/estadisticas.yml");
		FileWriter fw = new FileWriter(archivoYML);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);

		this.escribirYML(info, pw);
	}

	public void crearYMLCon(InformacionEstadistica info, String directorioDeTrabajo, String nombreYML) throws IOException {

		File directorio = new File(directorioDeTrabajo);

		if (!directorio.exists()) {
			directorio.mkdir();
		}

		File archivoYML = new File(directorioDeTrabajo + "/" + nombreYML + ".yml");
		FileWriter fw = new FileWriter(archivoYML);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);

		this.escribirYML(info, pw);
	}

	private void escribirYML(InformacionEstadistica info, PrintWriter pw) {

		pw.println("Bicicletas mas usadas: ");
		this.escribirIdsBicicletasMaximas(info, pw);

		pw.println("Bicicletas menos usadas: ");
		this.escribirIdsBicicletasMinimas(info, pw);

		pw.println("Recorridos mas realizados: ");
		this.escribirIdRecorridosMasRealizados(info, pw);

		pw.println("Tiempo promedio de uso: " + info.getTiempoPromedio());

		pw.println("Bicicletas utilizada mas tiempo: ");
		this.escribirIdsBicicletasUsadasMasTiempo(info, pw);

		pw.println("Tiempo de la bicicleta mas utilizada: " + info.getTiempoDeBicicletaMasUsada());

		pw.println("Tiempo de procesamiento: " + info.getTiempoDeProcesamiento());

		pw.close();
	}

	private void escribirIdsBicicletasUsadasMasTiempo(InformacionEstadistica info, PrintWriter pw) {
		for (Bicicleta b : info.getBicicletasUsadasMasTiempo()) {
			pw.println("id:" + b.getId());
		}
	}

	private void escribirIdRecorridosMasRealizados(InformacionEstadistica info, PrintWriter pw) {

		Iterator<Ruta> iterador = info.recorridosMasRealizados().iterator();

		while (iterador.hasNext()) {

			Ruta recorrido = iterador.next();
			pw.println("id origen:" + recorrido.getIdEstacionOrigen());
			pw.println("id destino:" + recorrido.getIdEstacionDestino());
			pw.println("");
		}
	}

	private void escribirIdsBicicletasMinimas(InformacionEstadistica info, PrintWriter pw) {

		Iterator<Integer> iterador = info.bicicletasMenosUsadas().iterator();

		while (iterador.hasNext()) {
			pw.println("id:" + iterador.next());
		}

		pw.println("");
	}

	private void escribirIdsBicicletasMaximas(InformacionEstadistica info, PrintWriter pw) {

		Iterator<Integer> iterador = info.bicicletasMasUsadas().iterator();

		while (iterador.hasNext()) {
			pw.println("id:" + iterador.next());
		}

		pw.println("");
	}

	public Enumeration<? extends ZipEntry> leerArchivosCSVContenidosEnZip(ZipFile zipFiles) {
		Enumeration<? extends ZipEntry> listaDeArchivosCSVEnZip = zipFiles.entries();

		return listaDeArchivosCSVEnZip;
	}

	private void comprobarPath(Path path) {
		try {
			Boolean elDirectorioEsValido = ((Boolean) Files.getAttribute(path, "basic:isDirectory", LinkOption.NOFOLLOW_LINKS));
			if (!elDirectorioEsValido) {
				throw new IllegalArgumentException("El Path: " + path + " no es un directorio");
			}
		} catch (IOException ioe) {
			throw new IllegalArgumentException("El directorio con el path: " + path + "no existe");
		}
	}

	public void moverZipAProcesados(ZipFile archivoZip) {

		File archivoAMover = new File(archivoZip.getName());
		File directorioProcesados = new File(archivoAMover.getParent() + "/procesados");
		File archivoYaProcesado = new File(directorioProcesados.getPath() + "/" + archivoAMover.getName());

		if (!directorioProcesados.exists())
			directorioProcesados.mkdir();

		archivoAMover.renameTo(archivoYaProcesado);
	}
}