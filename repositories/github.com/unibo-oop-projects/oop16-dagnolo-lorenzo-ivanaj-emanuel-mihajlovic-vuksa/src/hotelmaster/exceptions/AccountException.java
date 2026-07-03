package hotelmaster.exceptions;
/**
 * An account already exists.
 */
public class AccountException extends HotelMasterException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public AccountException() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * @param arg0 description
     * @param arg1 cause
     */
    public AccountException(final String arg0, final Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * @param arg0 description
     */
    public AccountException(final String arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * @param arg0 cause
     */
    public AccountException(final Throwable arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

}
