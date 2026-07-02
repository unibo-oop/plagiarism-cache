package it.unibo.aknightstale.exceptions;

@SuppressWarnings("unused") // Can be used in the future
public class ClassInstantiationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ClassInstantiationException(final String message) {
        super(message);
    }

    public ClassInstantiationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ClassInstantiationException(final Throwable cause) {
        super(cause);
    }

    public ClassInstantiationException() {
        super();
    }

    public ClassInstantiationException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
