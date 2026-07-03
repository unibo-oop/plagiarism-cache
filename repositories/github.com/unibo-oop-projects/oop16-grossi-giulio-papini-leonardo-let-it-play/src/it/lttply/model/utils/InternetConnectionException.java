package it.lttply.model.utils;

import java.io.IOException;

/**
 * Thrown when an Internet connection problem has occurred. For details, see the
 * {@link InternetConnectionException#InternetConnectionException(IOException)}
 * doc.
 */
public class InternetConnectionException extends Exception {

    private static final long serialVersionUID = 1973901512402720274L;

    /**
     * Constructs a new {@link InternetConnectionException} which wraps another
     * different "Internet connection exception", that is normally represented
     * as a subclass of {@link IOException}.
     * 
     * @param ex
     *            the {@link IOException} to be wrapped
     */
    public InternetConnectionException(final IOException ex) {
        super("An internet connection problem has occurred: " + ex.getClass().getName());
    }
}
