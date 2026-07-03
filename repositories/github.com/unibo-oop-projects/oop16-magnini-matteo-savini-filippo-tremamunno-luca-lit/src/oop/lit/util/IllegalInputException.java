package oop.lit.util;

/**
 * An exception to be thrown if the input inserted by the user is invalid.
 */
public class IllegalInputException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 7106987166875828268L;

    /**
     * @param message
     *      why this exception was thrown.
     */
    public IllegalInputException(final String message) {
        super(message);
    }
}
