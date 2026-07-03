package controller.data;

/**
 * 
 * @author Stefano Benelli
 * This class represents the exception raised if an error occurs during Persistent Operations
 */
public class PersistentException extends Exception {

	private static final long serialVersionUID = -2826914696373056254L;

	private final Exception innerException;
	
	public PersistentException (final Exception innerException) {
		this.innerException = innerException;
	}
	
	public Exception getInnerException() {
		return this.innerException;
	}
	
	@Override
	public String toString() {
		if(this.innerException != null) {
			return this.innerException.toString();
		}
		
		return "";
	}
}
