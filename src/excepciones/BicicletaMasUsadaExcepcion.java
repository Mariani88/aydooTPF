package excepciones;

public class BicicletaMasUsadaExcepcion extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BicicletaMasUsadaExcepcion (){
		super ("No se puede guardar un id con cantidad inferior a la maxima");
	}
}
