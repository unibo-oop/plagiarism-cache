package hotelmaster.exceptions;
/**
 * Exception for price describer.
 *
 */
public class PriceDescriberRemovalException extends HotelMasterException {
     /**
      * 
      */
    public PriceDescriberRemovalException() {
        super();
    }
    /**
     * 
     * @param arg0 description
     * @param arg1 cause
     */
    public PriceDescriberRemovalException(final String arg0, final Throwable arg1) {
        super(arg0, arg1);
    }
    /**
     * 
     * @param arg0 description
     */
    public PriceDescriberRemovalException(final String arg0) {
        super(arg0);
    }
    /**
     * 
     * @param arg0 cause
     */
    public PriceDescriberRemovalException(final Throwable arg0) {
        super(arg0);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
