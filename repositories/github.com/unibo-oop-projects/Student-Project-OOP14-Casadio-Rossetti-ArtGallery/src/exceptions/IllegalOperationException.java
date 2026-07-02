package exceptions;

/**
 * Thrown when the user wants to add an artwork that is already added to an 
 * exhibit or when the user wants to delete an artwork that is already used by 
 * an exhibit or when the user wants to add or to delete an artwork from an 
 * exhibit that has already finished or when the user wants to modify or to 
 * delete an exhibit that has already started.
 * @author Elisa Casadio
 *
 */

public class IllegalOperationException extends Exception {

	private static final long serialVersionUID = 6755539631876115754L;

	/**
	 * Constructor.
	 * 
	 * @param msg
	 * 			the message shown when the exception is thrown.
	 */
	public IllegalOperationException(final String msg) {
		super(msg);
	}
	
}
