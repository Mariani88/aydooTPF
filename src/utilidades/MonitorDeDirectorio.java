package utilidades;

import java.util.Timer;
import java.util.TimerTask;

import utilidades.tareasDeMonitor.TareaProcesarZips;

public class MonitorDeDirectorio {

	private String directorioDeMonitoreo = null;
	private Timer temporizadorDeMonitoreo;
	private TimerTask tarea;
	
	public MonitorDeDirectorio(String directorioDeMonitoreo) {
		this.directorioDeMonitoreo = directorioDeMonitoreo;
		this.temporizadorDeMonitoreo = new Timer ();
		this.tarea = new TareaProcesarZips (directorioDeMonitoreo);
	}

	
	
	
	
	
	public void monitorear() {
		// TODO Auto-generated method stub
		
	}
}