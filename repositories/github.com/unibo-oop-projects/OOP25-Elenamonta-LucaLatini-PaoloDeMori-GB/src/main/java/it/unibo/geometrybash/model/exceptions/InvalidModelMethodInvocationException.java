package it.unibo.geometrybash.model.exceptions;

/**
 * An Exception thrown if an object of the model isnt correctly initialise.
 */
public class InvalidModelMethodInvocationException extends Exception {
    private static final String DEFAULT_MESSAGE = "Error during method invocation in model";

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new Exception for an error thrown if an object of the model isnt
     * correctly initialise.
     * 
     * <p>
     * Add a string to the default message.
     * 
     * @param message The message to add to the default message.
     */
    public InvalidModelMethodInvocationException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

    /**
     * Constructs a new Exception for an error thrownif an object of the model isnt
     * correctly initialise.
     * 
     * <p>
     * Uses the default message.
     */
    public InvalidModelMethodInvocationException() {
        super(DEFAULT_MESSAGE);
    }
}
