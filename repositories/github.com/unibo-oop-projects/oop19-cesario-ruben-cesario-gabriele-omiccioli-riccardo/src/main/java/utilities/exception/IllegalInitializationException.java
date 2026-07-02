package utilities.exception;

/**
 * An exception thrown when some fields of a class aren't properly
 * initialized for it to be working correctly.
 */
public class IllegalInitializationException extends RuntimeException {

    private static final long serialVersionUID = -4439581259417957060L;

    public IllegalInitializationException() {
        this("This exception is caused by wrong data initialization.");
    }

    public IllegalInitializationException(final String message) {
        super(message);
    }

    public IllegalInitializationException(final Throwable cause) {
        super(cause);
    }

    public IllegalInitializationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public IllegalInitializationException(final String message, final Throwable cause, final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
