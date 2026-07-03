package model.interfaces;

import model.enumerations.Status;
import model.enumerations.TravelClass;

/**
 * 
 * Represents a plane.
 */
public interface Plane {

    /**
     * Books a seat of the specified travel class.
     * 
     * @param tc    the travel class to which the seat belongs
     */
    void bookSeat(TravelClass tc);

    /**
     * @param tc    the travel class
     * 
     * @return a Boolean which specifies if the seats of the specified class are all booked (true) or not (false)
     */
     Boolean isClassFull(TravelClass tc);

    /**
     * 
     * @return a Boolean which specifies if all plane's seats are booked (true) or not (false)
     */
    boolean isPlaneFull();

    /**
     * Resets all seats.
     */
    void resetSeats();

    /**
     * 
     * @return the plane's identifier
     */
    String getPlaneId();

    /**
     * 
     * @return the name of the airline to which the plane belongs
     */
    String getAirlineName();

    /**
     * 
     * @return the number of economy class seats available
     */
    int getNEconomyClassAvailableSeats();

    /**
     * 
     * @return the total number of economy class seats
     */
    int getNEconomyClassTotalSeats();

    /**
     * 
     * @return the number of business class seats available
     */
    int getNBusinessClassAvailableSeats();

    /**
     * 
     * @return the total number of business class seats
     */
    int getNBusinessClassTotalSeats();

    /**
     * 
     * @return the number of first class seats available
     */
    int getNFirstClassAvailableSeats();

    /**
     * 
     * @return the total number of first class seats
     */
    int getNFirstClassTotalSeats();

    /**
     * 
     * @return the plane's status
     */
    Status getStatus();

    /**
     * Sets the plane's status.
     * 
     * @param stat    the plane's status
     */
    void setStatus(Status stat);
}