package excepciones;

public class RecorridoMasRealizadoExcepcion extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecorridoMasRealizadoExcepcion() {

		super(
				"No se puede guardar un id recorrido con una cantidad inferior al maximo");
	}
}
