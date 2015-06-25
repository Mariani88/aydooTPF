package excepciones;

public class DirectorioNoExisteExcepcion extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DirectorioNoExisteExcepcion() {
		super("No existe el directorio especificado");
	}
	
}
