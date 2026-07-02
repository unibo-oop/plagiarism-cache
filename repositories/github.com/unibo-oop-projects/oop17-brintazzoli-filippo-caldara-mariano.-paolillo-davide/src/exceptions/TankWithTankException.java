package exceptions;

/**
 * Exception threw when two {@link Tank} collide each other.
 */
public class TankWithTankException extends IllegalStateException {

    /**
     * 
     */
    private static final long serialVersionUID = 3372436700594704936L;

    /**
     * Constructor.
     */
    public TankWithTankException() {
        super();
    }

    /**
     * Constructor.
     * @param msg
     *          {@inheritDoc IllegalStateException#IllegalStateException(String)}
     */
    public TankWithTankException(final String msg) {
        super(msg);
    }

}
