package model.interfaces;

import model.enumerations.TravelClass;

/**
 * 
 * Represents a booking.
 */
public interface Booking {

    /**
     * 
     * @return the booking identifier
     */
    String getBookingId();

    /**
     * 
     * @return the booked flight
     */
    Flight getFlight();

    /**
     * 
     * @return the booked travel class
     */
    TravelClass getTravelClass();

    /**
     * 
     * @return the passenger who made the reservation
     */
    Passenger getPassenger();

    /**
     * 
     * @return the final price of the booking
     */
    double getFinalPrice();

    /**
     * Sets the final price of the booking.
     * 
     * @param price    the final price of the booking
     */
    void setFinalPrice(double price);
}