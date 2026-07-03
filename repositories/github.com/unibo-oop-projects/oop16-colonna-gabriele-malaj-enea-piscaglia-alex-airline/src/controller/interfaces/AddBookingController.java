package controller.interfaces;

/**
 * Add Booking Controller interface.
 */
public interface AddBookingController {

    /**
     * Adds a booking to the list of bookings.
     * 
     * @throws NumberFormatException if age do not contain an integer
     */
    void addBooking() throws NumberFormatException;

}