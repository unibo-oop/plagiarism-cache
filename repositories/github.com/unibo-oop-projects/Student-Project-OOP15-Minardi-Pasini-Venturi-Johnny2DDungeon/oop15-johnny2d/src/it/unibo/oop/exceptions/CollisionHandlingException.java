package it.unibo.oop.exceptions;

/**
 * An exception that can occur in a collision.
 */
public class CollisionHandlingException extends Exception {

    private static final long serialVersionUID = -3161439127315782693L;

    public CollisionHandlingException() {
        super();
    }

    public CollisionHandlingException(final String message) {
        super(message);
    }
}