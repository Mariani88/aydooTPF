package dominio;

import java.io.File;
import java.util.Scanner;

import javax.management.monitor.Monitor;

import excepciones.DirectorioNoExisteExcepcion;
import utilidades.GeneradorDeEstadistica;
import utilidades.GestorDeArchivos;
import utilidades.MonitorDeDirectorio;

public class ProcesadorEstadistico {

	private static MonitorDeDirectorio monitorDeDirectorio;
	private static GeneradorDeEstadistica generadorDeEstadisticas;
	private static GestorDeArchivos gestorDeArchivos;
	private static Scanner escaner = new Scanner (System.in);
	
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
			case "2": System.out.println ("Saliendo...");
			break;
			default : 
				if (!"1".equals(opcion) && !"2".equals(opcion))
					System.out.println("Opcion invalida");
			}
		}
	}
	
	private static void comenzarProcesamiento (){ //ACA VA LA LOGICA DE PROCESAMIENTO
		pedirDirectorioDeTrabajo ();
		
		
		
		
		
	}

	private static void pedirDirectorioDeTrabajo()  {
		
		boolean configuracionCorrecta = false;
		String directorioDeTrabajo = null;
		
		while (!configuracionCorrecta){
			System.out.print ("Ingrese directorio de los archivos ZIP a procesar:");
			directorioDeTrabajo = escaner.next();
			configuracionCorrecta = comprobarExistenciaDeDirectorio (directorioDeTrabajo);
		}
		
		//gestorDeArchivos = new GestorDeArchivos (directorioDeTrabajo);
		
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
