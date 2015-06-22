package excepciones;

public class BicicletaMenosUsadaExcepcion extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BicicletaMenosUsadaExcepcion() {

		super(
				"No se puede guardar un id de bicicleta con un valor superior al minimo");

	}
	
	
}
