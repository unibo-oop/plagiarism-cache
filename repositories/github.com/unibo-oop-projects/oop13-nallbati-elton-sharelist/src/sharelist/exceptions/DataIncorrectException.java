package sharelist.exceptions;

/**
 * Eccezione lanciata se il server non risponde.
 * 
 * @author Elton Nallbati
 * @version 1.0
 */

public class DataIncorrectException extends Exception {
	private static final long serialVersionUID = 1055119066980530858L;

	public DataIncorrectException(String message) {
		super(message);
	}

}