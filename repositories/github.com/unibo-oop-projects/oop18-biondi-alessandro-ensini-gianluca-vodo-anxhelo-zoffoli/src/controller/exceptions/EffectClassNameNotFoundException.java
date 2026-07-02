package controller.exceptions;

/**
 * Exception to be invoked if no effect name founded in EffectClassName Map.
 */
public class EffectClassNameNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Construct an exception if Effect Class name not founded.
     * 
     * @param message to be shown
     */
    public EffectClassNameNotFoundException(final String message) {
        super(message);
    }
}
