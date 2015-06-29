package aydoo;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipFile;

import dominio.Bicicleta;
import dominio.InformacionEstadistica;
import utilidades.GeneradorDeEstadistica;
import utilidades.GestorDeArchivos;
import utilidades.MonitorDeDirectorio;

public class Procesador {

    private static MonitorDeDirectorio monitorDeDirectorio;
    private static GeneradorDeEstadistica generadorDeEstadisticas;
    private static GestorDeArchivos gestorDeArchivos;
    private static String directorioDeTrabajo = null;
   
    public static void main (String args []) throws IOException{
   
        directorioDeTrabajo = args[0];
        gestorDeArchivos = new GestorDeArchivos ();
       
        if(args.length == 2 && args[1].equals("demonio")){
        	evaluarDirectorioDeTrabajo();
            monitorDeDirectorio = new MonitorDeDirectorio (directorioDeTrabajo);
            monitorDeDirectorio.monitorear();
        }else{
        	evaluarDirectorioDeTrabajo();
            comenzarProcesamiento();
        }
    }
    
    private static void evaluarDirectorioDeTrabajo()  {
		
		boolean configuracionCorrecta = comprobarExistenciaDeDirectorio (directorioDeTrabajo);
		Scanner escaner = new Scanner (System.in);
		
		while (!configuracionCorrecta){
			System.out.print ("Ingrese directorio:");
			directorioDeTrabajo = escaner.next();
			configuracionCorrecta = comprobarExistenciaDeDirectorio (directorioDeTrabajo);
		}
		
		gestorDeArchivos = new GestorDeArchivos ();
		escaner.close();
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
   
    private static void comenzarProcesamiento () { //ACA VA LA LOGICA DE PROCESAMIENTO
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
                gestorDeArchivos.moverZipAProcesados(archivosZip [i]);
            }
           
            InformacionEstadistica estadisticas = generadorDeEstadisticas.terminar();
            gestorDeArchivos.crearYMLCon(estadisticas, directorioDeTrabajo + "/salida");
            System.out.println ("archivos procesados");
           
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}