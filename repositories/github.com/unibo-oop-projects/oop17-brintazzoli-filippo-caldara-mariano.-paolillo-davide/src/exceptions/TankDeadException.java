package exceptions;

/**
 * Exception for Model.
 */
public class TankDeadException extends IllegalStateException {

    /**
     * 
     */
    private static final long serialVersionUID = 2201790410334724906L;

    /**
     * Constructor.
     */
    public TankDeadException() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param msg
     *            {@inheritDoc IllegalStateException#IllegalStateException(String)}
     */
    public TankDeadException(final String msg) {
        super(msg);
    }
}
