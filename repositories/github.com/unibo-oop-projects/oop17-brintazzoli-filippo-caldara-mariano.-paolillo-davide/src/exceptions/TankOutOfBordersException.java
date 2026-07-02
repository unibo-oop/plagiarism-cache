package exceptions;

/**
 * Exception threw when a {@link Tank} goes out from the {@link World} borders.
 */
public class TankOutOfBordersException extends IllegalStateException {

    /**
     * 
     */
    private static final long serialVersionUID = -5611188951875412575L;

    /**
     * Constructor.
     */
    public TankOutOfBordersException() {
        super();
    }

    /**
     * Constructor.
     * @param msg
     *          {@inheritDoc IllegalStateException#IllegalStateException(String)}
     */
    public TankOutOfBordersException(final String msg) {
        super(msg);
    }

}
