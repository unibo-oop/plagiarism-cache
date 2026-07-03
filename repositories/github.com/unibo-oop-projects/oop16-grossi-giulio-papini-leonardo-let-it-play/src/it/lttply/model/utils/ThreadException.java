package it.lttply.model.utils;

/**
 * Wrapper for an {@link Exception}, can be used to intercept and store any kind
 * of exception which happens when a thread works.
 */
public class ThreadException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -8741185616141237227L;

    /**
     * Constructs a new {@link ThreadException} which wraps another
     * {@link Exception}.
     * 
     * @param ex
     *            the {@link Exception} to be wrapped
     */
    public ThreadException(final Exception ex) {
        super(ex);
    }

}