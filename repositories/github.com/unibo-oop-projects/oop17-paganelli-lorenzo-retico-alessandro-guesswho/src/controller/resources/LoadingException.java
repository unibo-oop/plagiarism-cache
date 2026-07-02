package controller.resources;

/**
 * Error occurring during the loading of resources.
 */
public class LoadingException extends Exception {

    /***/
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * @param message
     *              the message to display
     */
    public LoadingException(final String message) {
        super(message);
    }

}
