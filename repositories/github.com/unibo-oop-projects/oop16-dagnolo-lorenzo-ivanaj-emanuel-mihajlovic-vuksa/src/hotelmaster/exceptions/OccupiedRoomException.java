package hotelmaster.exceptions;

/**
 * Two stays' dates for a room are conflicting.
 */
public class OccupiedRoomException extends HotelMasterException {

    /**
     * 
     */
    private static final long serialVersionUID = 2467914741143999151L;

    /**
     * 
     */
    public OccupiedRoomException() {
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
    public OccupiedRoomException(final String arg0, final Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @param arg0
     *            description
     */
    public OccupiedRoomException(final String arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @param arg0
     *            cause
     */
    public OccupiedRoomException(final Throwable arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

}
