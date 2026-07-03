package model;
/**
 * This interface represents the booking of the tickets for the cinema.
 * 
 */
public interface Booking {

     /**
     * Choose if you buy tickets online (true).
     */
    void setOnline();

     /**
     * Return the price of the tickets.
     * 
     * @param discount
     *        the discounts applied to the tickets bought
     * @param balance
     *        used to calculate the money earned
     * @return the total price of the tickets bought with discount applied
     */
    double computePrice(Discount discount, Balance balance);

    /**
    * Set the room where to watch the movie.
    * 
    * @param room
    *         the room where to watch the movie
    */
    void setRoomForBooking(Room room);

    /**
     * Return the room where you watch the movie.
     * 
     * @return the room where you watch the movie
     */
    Room getRoomForBooking();
}
