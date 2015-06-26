package dominio;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipFile;
import utilidades.GeneradorDeEstadistica;
import utilidades.GestorDeArchivos;
import utilidades.MonitorDeDirectorio;

public class ProcesadorEstadistico {

	private static MonitorDeDirectorio monitorDeDirectorio;
	private static GeneradorDeEstadistica generadorDeEstadisticas;
	private static GestorDeArchivos gestorDeArchivos;
	private static Scanner escaner = new Scanner (System.in);
	private static String directorioDeTrabajo = null;
	
	public static void main (String args []){
		
		inicializar();
		monitorDeDirectorio.monitorear ();
		menu();
		escaner.close();
			
			
	}
	
	private static void inicializar (){
		System.out.println ("Bienvenido a procesador estadistico.");
		pedirDirectorioDeMonitoreo();
		generadorDeEstadisticas = new GeneradorDeEstadistica ();
	}
	
	private static void menu (){
		
		String opcion = "";
		
		while (!"2".equals(opcion)){
			System.out.println ("Ingrese opcion a realizar:");
			System.out.println ("1- Procesar archivos de directorio especifico");
			System.out.println ("2- salir");
			opcion = escaner.next();
			
			switch (opcion){
			case "1":comenzarProcesamiento();
			break;
			case "2": {
				System.out.println ("Saliendo...");
				monitorDeDirectorio.pararMonitoreo();
			}
			break;
			default : 
				if (!"1".equals(opcion) && !"2".equals(opcion))
					System.out.println("Opcion invalida");
			}
		}
	}
	
	private static void comenzarProcesamiento () { //ACA VA LA LOGICA DE PROCESAMIENTO
		pedirDirectorioDeTrabajo ();
		generadorDeEstadisticas = new GeneradorDeEstadistica ();
		
		try {	
			ZipFile [] archivosZip = gestorDeArchivos.obtenerArchivosZip(directorioDeTrabajo);
			
			for ( int i = 0; i < archivosZip.length; i++){	
				gestorDeArchivos.asignarArchivoZipParaProcesar(archivosZip[i]);
				List <Bicicleta> bicicletas = gestorDeArchivos.obtenerListaDeBicicletas(500);
				float porcentaje = (float)(i+1) *100/archivosZip.length;
				System.out.println ("procesando archivos, espere..."+ porcentaje + "%");
				
				while (bicicletas.size() != 0){
					generadorDeEstadisticas.generarEstadistica(bicicletas);
					bicicletas = gestorDeArchivos.obtenerListaDeBicicletas(500);
				}
			}
			
			InformacionEstadistica estadisticas = generadorDeEstadisticas.terminar();
			gestorDeArchivos.crearYMLCon(estadisticas, directorioDeTrabajo + "/salida");
			System.out.println ("archivos procesados");
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	private static void pedirDirectorioDeTrabajo()  {
		
		boolean configuracionCorrecta = false;
		
		
		while (!configuracionCorrecta){
			System.out.print ("Ingrese directorio de los archivos ZIP a procesar:");
			directorioDeTrabajo = escaner.next();
			configuracionCorrecta = comprobarExistenciaDeDirectorio (directorioDeTrabajo);
		}
		
		gestorDeArchivos = new GestorDeArchivos ();
		
	}

	private static void pedirDirectorioDeMonitoreo() {
		
		boolean configuracionCorrecta = false;
		String directorioDeMonitoreo = null;
		
		while (!configuracionCorrecta){
			System.out.println ("");
			System.out.println ("");
			System.out.print ("Ingrese directorio a monitorear: ");
			directorioDeMonitoreo = escaner.next();
			configuracionCorrecta = comprobarExistenciaDeDirectorio (directorioDeMonitoreo);
		}
		monitorDeDirectorio = new MonitorDeDirectorio (directorioDeMonitoreo);
	}

	private static boolean comprobarExistenciaDeDirectorio(
			String directorioDeTrabajo) {
		
		File directorioAEvaluar = new File (directorioDeTrabajo);
		boolean existe = directorioAEvaluar.exists();
		
		if (!existe){
			System.out.println ("No existe el directorio.");
		}
		
		return existe;
	}
}
