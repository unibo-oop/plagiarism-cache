package it.unibo.aknightstale.views.exceptions;

public class ViewLoadingException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ViewLoadingException(final String message) {
        super(message);
    }

    public ViewLoadingException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ViewLoadingException(final Throwable cause) {
        super(cause);
    }

    public ViewLoadingException() {
        super();
    }

    public ViewLoadingException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
