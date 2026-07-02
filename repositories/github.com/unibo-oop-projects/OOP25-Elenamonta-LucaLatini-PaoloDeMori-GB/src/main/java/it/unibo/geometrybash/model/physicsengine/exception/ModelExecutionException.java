package it.unibo.geometrybash.model.physicsengine.exception;

/**
 * An Exception thrown if an object of the physics engine isnt correctly
 * initialise.
 */
public class ModelExecutionException extends Exception {
    private static final String DEFAULT_MESSAGE = "Error during execution in model";

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new Exception thrown if a problem occured
     * while executing the model.
     * 
     * <p>
     * Add a string to the default message.
     * 
     * @param message The message to add to the default message.
     */
    public ModelExecutionException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

    /**
     * Constructs a new Exception thrown if a problem occured
     * while executing the model.
     * 
     * <p>
     * Add a string to the default message.
     * 
     * @param message The message to add to the default message.
     * @param e       The exception that generated this one.
     */
    public ModelExecutionException(final String message, final Exception e) {
        super(DEFAULT_MESSAGE + " " + message, e);
    }

    /**
     * Constructs a new Exception thrown if a problem occured
     * while executing the model.
     * 
     * <p>
     * Uses the default message.
     */
    public ModelExecutionException() {
        super(DEFAULT_MESSAGE);
    }
}
