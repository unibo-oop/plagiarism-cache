package hotelmaster.exceptions;
/**
 * A guest has a booking or is having a stay.
 */
public class GuestException extends HotelMasterException {
    /**
     * 
     */
    public GuestException() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * @param arg0 description
     * @param arg1 cause
     */
    public GuestException(final String arg0, final Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * @param arg0 description
     */
    public GuestException(final String arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * @param arg0 cause
     */
    public GuestException(final Throwable arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
