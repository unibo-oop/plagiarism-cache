package org.gitgud.exceptions;

/**
 * A generic application checked exception.
 */
public class GitGudCheckedException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * @param message
     *            the exception message
     */
    public GitGudCheckedException(final String message) {
        super(message);
    }

}
