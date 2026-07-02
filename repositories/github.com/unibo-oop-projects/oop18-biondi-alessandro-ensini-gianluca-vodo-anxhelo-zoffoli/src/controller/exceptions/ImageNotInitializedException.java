package controller.exceptions;

/**
 * Exception to be invoked if image is not loaded.
 */
public class ImageNotInitializedException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Construct an exception if image is not loaded.
     * 
     * @param message to be shown
     */
    public ImageNotInitializedException(final String message) {
        super(message);
    }
}
