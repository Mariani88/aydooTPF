package utilidades;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import org.junit.Assert;
import org.junit.Test;
import dominio.Bicicleta;
import dominio.InformacionEstadistica;

public class GestorDeArchivosTest {
	
	private  String DOCUMENTOS_ZIP_PROCESADOS_TEST_ZIP= "documentos/zipProcesadosTest";
	private  String PATH_ARCHIVOS_YML = "salida";
	private  String PATH_INVALIDO = "path invalido";
	private  String PATH_ARCHIVOS_ZIP = "documentos/archivosZipParaTest";
	//private  String PATH_ARCHIVOS_MEZCLADOS = "documentos/directorioArchivosMezclados";
	
	@Test(expected= IllegalArgumentException.class)
	public void cuandoSeQuiereObtenerArchivosDeUnDirectorioInexistenteEsperoExcepcion()
			throws ZipException, IOException{
		
		GestorDeArchivos gestorDeArchivos = new GestorDeArchivos();
		gestorDeArchivos.obtenerArchivosZip(PATH_INVALIDO);
	}	
	
	
	/*@Test
	public void obtenerArchivosZipDebeSoloDevolverZips() throws ZipException,
			IOException {
		GestorDeArchivos gestorDeArchivos = new GestorDeArchivos();

		Assert.assertEquals(
				1,
				gestorDeArchivos
						.obtenerArchivosZip(PATH_ARCHIVOS_MEZCLADOS ).length);
	}*/
	
	@Test
	public void cuandoGeneroElYMLEntoncesSeCreaElArchivoYML() throws IOException{
		
		GestorDeArchivos gestor = new GestorDeArchivos();
		InformacionEstadistica info = new InformacionEstadistica ();
		
		info.guardarBicicletasMasUsadas(3, 10);
		info.guardarBicicletasMenosUsadas(1, 2);
		info.guardarRecorridoMasRealizado(new Ruta (1,2), 2);
		info.setTiempoPromedio(30);
		
		gestor.crearYMLCon(info, PATH_ARCHIVOS_YML); 
		
		File file = new File (PATH_ARCHIVOS_YML +"/estadisticas.yml");
			
		Assert.assertTrue(file.exists());
	}
	
	@Test
	public void cuandoGeneroElYMLConNombreEntoncesSeCreaElArchivoYML() throws IOException{
		
		GestorDeArchivos gestor = new GestorDeArchivos();
		InformacionEstadistica info = new InformacionEstadistica ();
		
		info.guardarBicicletasMasUsadas(3, 10);
		info.guardarBicicletasMenosUsadas(1, 2);
		info.guardarRecorridoMasRealizado(new Ruta (1,2), 2);
		info.setTiempoPromedio(30);
		
		gestor.crearYMLCon(info, PATH_ARCHIVOS_YML, "nombre"); 
		
		File file = new File (PATH_ARCHIVOS_YML + "/nombre.yml");
			
		Assert.assertTrue(file.exists());
	}
	
	
	
	
	@Test
	public void obtenerMenosBicicletasDeLasHayEnArchivoCSV() throws ZipException,
		IOException, ParseException{
		
		GestorDeArchivos gestor = new GestorDeArchivos();
		
		ZipFile[] archivosZip = gestor.obtenerArchivosZip(PATH_ARCHIVOS_ZIP);
		gestor.asignarArchivoZipParaProcesar(archivosZip[0]);
		
		int cantidadDeBicicletasAObtener = 500;
		
		List<Bicicleta> bicicletas = gestor.obtenerListaDeBicicletas(cantidadDeBicicletasAObtener);
		
		Assert.assertEquals(cantidadDeBicicletasAObtener, bicicletas.size());
	}
	
	@Test
	public void obtenertTodasLasBicicletasDeLasQueHayEnUnArchivoZipPidiendoDeAMil() throws ZipException,
		IOException, ParseException{
		
		GestorDeArchivos gestor = new GestorDeArchivos();
		ZipFile zip = new ZipFile (PATH_ARCHIVOS_ZIP + "/recorridos-2010.zip");
		
		ZipFile[] archivosZip = {zip};
		gestor.asignarArchivoZipParaProcesar(archivosZip[0]);
		int cantidadTotalDeBicicletasEnElZip = 12632;
		int cantidadDeBicicletasAObtener = 1000;
		List<Bicicleta> bicicletas = new ArrayList<Bicicleta>();
		List<Bicicleta> bicicletasObtenidas;
		while(!(bicicletasObtenidas = gestor.obtenerListaDeBicicletas(cantidadDeBicicletasAObtener)).isEmpty()){
			
			bicicletas.addAll(bicicletasObtenidas);
		}
		Assert.assertEquals(cantidadTotalDeBicicletasEnElZip, bicicletas.size());
	}
	
	
	@Test 
	public void moverArchivoZipAProcesados() throws IOException{
		
		GestorDeArchivos gestorDeArchivos = new GestorDeArchivos();
		ZipFile zipAProcesar = new ZipFile(DOCUMENTOS_ZIP_PROCESADOS_TEST_ZIP + "/zipTest.zip");
		gestorDeArchivos.moverZipAProcesados(zipAProcesar);
		File zipProcesado = new File(DOCUMENTOS_ZIP_PROCESADOS_TEST_ZIP  +"/procesados/zipTest.zip" );
		Assert.assertTrue( zipProcesado.exists());
		
		this.volverArchivoAlOrigen();
	}


	private void volverArchivoAlOrigen() {
		
		File destino = new File (DOCUMENTOS_ZIP_PROCESADOS_TEST_ZIP  +"/procesados/zipTest.zip");
		File origen = new File (DOCUMENTOS_ZIP_PROCESADOS_TEST_ZIP + "/zipTest.zip");
		File directorioProcesados = new File (destino.getParent());
		
		destino.renameTo(origen);
		directorioProcesados.delete();
		
	}

}