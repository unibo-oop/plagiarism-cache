package model.exceptions;

/**
 * 
 * @author Stefano Benelli
 * This class represent the exception raised if Game Configuration is invalid
 */
public class InvalidConfigException extends Exception {

	private static final long serialVersionUID = 3233642006405734255L;
	
	public InvalidConfigException(String message) {
		super(message);
	}
}
