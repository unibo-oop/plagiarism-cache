package controller.exceptions;

/**
 * Exception to be invoked if no Hstory is setted.
 */
public class HistoryNotInitializedException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Construct an exception if history is not setted.
     * 
     * @param message to be shown
     */
    public HistoryNotInitializedException(final String message) {
        super(message);
    }
}
