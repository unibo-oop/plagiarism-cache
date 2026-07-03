package controller;

import model.Discount;
import model.Room;

/**
 * It is the interface used to book seats in the cinema.
 *
 */
public interface BookingController {

    /**
     * It searches the room in which is screened the film with that title.
     *
     * @param filmTitle
     *            the title of the movie that he wants to book.
     * @throws IllegalArgumentException
     *             if the movie isn't screened in a room.
     */
    void searchRoom(String filmTitle) throws IllegalArgumentException;

    /**
     * It sets the booking.
     *
     * @param isOnline
     *            is true if the booking is done by the client, false if it is done by the owner.
     * @param nTickets
     *            the number of tickets to book.
     * @param dis
     *            the discount computed for that booking.
     * @return the total price of the booking.
     */
    double setBooking(boolean isOnline, int nTickets, Discount dis);

    /**
     * It gets the value of the field bookedRoom.
     *
     * @return the booked room.
     */
    Room getBookedRoom();
}
