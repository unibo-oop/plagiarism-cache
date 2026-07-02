package controller.users;

/**
 * Represents an extension of {@link IllegalAccountArgumentException}.
 * 
 */
public class IllegalAccountArgumentException extends IllegalArgumentException {

    /**
     * Represents the default Serial Version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Consists of the basic constructor.
     */
    public IllegalAccountArgumentException() {
    }

    /**
     * Consists of the costructor with a specific string message.
     * 
     * @param message
     *          the exception message.
     */
    public IllegalAccountArgumentException(final String message) {
        super(message);
    }

    /**
     * Consists of the costructor with a specific string message 
     * and a {@link Throwable} object.
     * 
     * @param message
     *          the exception message.
     * @param objT
     *          the throwable object.
     */
    public IllegalAccountArgumentException(final String message, final Throwable objT) {
        super(message, objT);
    }

    public final String getMessage() {
        return super.getMessage();
    }

}
