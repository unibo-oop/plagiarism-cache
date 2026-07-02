package exceptions;

/**
 * Thrown when the dating selected by the user does not include the required 
 * year. Generally used when you want to select the year 0 in dating "A.C." or 
 * when assigning dating "D.C." to a year following than the current one.
 * @author Elisa Casadio
 *
 */

public class DatingNotExistException extends Exception {
	
	private static final long serialVersionUID = -4750200169598581122L;

	/**
	 * Constructor.
	 * 
	 * @param msg
	 * 			the message shown when the exception is thrown.
	 */
	public DatingNotExistException(final String msg) {
		super(msg);
	}

}
