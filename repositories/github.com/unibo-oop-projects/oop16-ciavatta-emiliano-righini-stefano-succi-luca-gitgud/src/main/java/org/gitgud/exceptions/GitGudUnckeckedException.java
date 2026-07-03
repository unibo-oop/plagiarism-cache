package org.gitgud.exceptions;

/**
 * A generic application unchecked exception.
 */
public class GitGudUnckeckedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * @param message
     *            the exception message
     */
    public GitGudUnckeckedException(final String message) {
        super(message);
    }

}
