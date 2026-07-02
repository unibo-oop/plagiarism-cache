package utilities.exception;

/**
 * An exception usually used to signal an oversight in the project
 * implementation.
 */
public class NotImplementedException extends RuntimeException {

    private static final long serialVersionUID = -1507653630213007530L;

    public NotImplementedException() {
        this("This exception is caused by unfinished code.");
    }

    public NotImplementedException(final String message) {
        super(message);
    }

    public NotImplementedException(final Throwable cause) {
        super(cause);
    }

    public NotImplementedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NotImplementedException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
