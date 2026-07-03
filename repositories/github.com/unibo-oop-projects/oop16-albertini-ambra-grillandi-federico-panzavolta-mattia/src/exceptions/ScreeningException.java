package exceptions;

/**
 * this is the exception that is thrown when the empty seats in a screening are not enough for the booking.
 *
 */
public class ScreeningException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -2346008437499362833L;

    @Override
    public String getMessage() {
        return "There aren't enough seats for the booking.";
    }

}
