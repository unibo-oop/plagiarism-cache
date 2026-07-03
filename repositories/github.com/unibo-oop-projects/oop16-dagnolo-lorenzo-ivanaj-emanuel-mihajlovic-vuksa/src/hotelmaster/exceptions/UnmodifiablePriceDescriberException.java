package hotelmaster.exceptions;
/**
 * The price describer cannot be modified.
 *
 */
public class UnmodifiablePriceDescriberException extends HotelMasterException {
    /**
     * 
     */
    public UnmodifiablePriceDescriberException() {
        super();
    }
    /**
     * 
     * @param arg0 description
     * @param arg1 cause 
     */
    public UnmodifiablePriceDescriberException(final String arg0, final Throwable arg1) {
        super(arg0, arg1);
    }
    /**
     * 
     * @param arg0 description
     */
    public UnmodifiablePriceDescriberException(final String arg0) {
        super(arg0);
    }
    /**
     * 
     * @param arg0 cause
     */
    public UnmodifiablePriceDescriberException(final Throwable arg0) {
        super(arg0);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
