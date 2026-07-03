package com.jlearn.controller.timer;

/**
 * Represents an exception occurring when you try to decrement below 0.
 */
public class OutOfTimeException extends Exception {

    /**
     * First version 1L.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Standard Exception Constructor.
     */
    public OutOfTimeException() {
        super();
    }

    /**
     *
     * @param message
     *            the {@link Exception} message
     */
    public OutOfTimeException(final String message) {
        super(message);
    }

    /**
     *
     * @param message
     *            the {@link Exception} message.
     *
     * @param cause
     *            {@link Throwable} cause.
     */
    public OutOfTimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause
     *            the {@link Throwable} cause.
     */
    public OutOfTimeException(final Throwable cause) {
        super(cause);
    }

}
