package hotelmaster.exceptions;

/**
 * Base HotelMaster exception.
 */
public class HotelMasterException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -322272243574115494L;

    /**
     * 
     */
    public HotelMasterException() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @param arg0
     *            description
     * @param arg1
     *            cause
     */
    public HotelMasterException(final String arg0, final Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @param arg0
     *            description
     */
    public HotelMasterException(final String arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @param arg0
     *            cause
     */
    public HotelMasterException(final Throwable arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

}
