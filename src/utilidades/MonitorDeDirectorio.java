package utilidades;

import java.util.Timer;
import java.util.TimerTask;

import utilidades.tareasDeMonitor.TareaProcesarZips;

public class MonitorDeDirectorio {

	private Timer temporizadorDeMonitoreo;
	private TimerTask tarea;
	
	public MonitorDeDirectorio(String directorioDeMonitoreo) {
		this.temporizadorDeMonitoreo = new Timer ();
		this.tarea = new TareaProcesarZips (directorioDeMonitoreo);
	}
	
	
	public void monitorear() {
		
		this.temporizadorDeMonitoreo.schedule(this.tarea,10, 3000);
	}
	
	
	public void pararMonitoreo (){
		
		this.temporizadorDeMonitoreo.cancel();
	}
	
	
}