package hotelmaster.exceptions;

/**
 * An operation involving the removal of one or more rooms failed because a stay
 * is associated to those rooms.
 */
public class RoomRemovalException extends HotelMasterException {

    /**
    *
    */
    private static final long serialVersionUID = -4897828782510591662L;

    /**
     * 
     */
    public RoomRemovalException() {
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
    public RoomRemovalException(final String arg0, final Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @param arg0
     *            description
     */
    public RoomRemovalException(final String arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @param arg0
     *            cause
     */
    public RoomRemovalException(final Throwable arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

}
