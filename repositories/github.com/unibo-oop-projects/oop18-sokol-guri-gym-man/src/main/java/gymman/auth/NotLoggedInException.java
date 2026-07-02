package gymman.auth;

public class NotLoggedInException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8268407054780958287L;
	
	public NotLoggedInException() {
		super();
	}
	
	public NotLoggedInException(String message) {
		super(message);
	}
}
