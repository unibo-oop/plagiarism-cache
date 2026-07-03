package model.interfaces;

import java.util.Date;
import java.util.List;

/**
 * 
 * Represents a flight.
 */
public interface Flight {

    /**
     * 
     * @return the flight duration in hours
     */
    long getDurationInHours();

    /**
     * 
     * @return the flight identifier
     */
    String getFlightId();

    /**
     * 
     * @return the plane
     */
    Plane getPlane();

    /**
     * 
     * @return the destination
     */
    Destination getDestination();

    /**
     * 
     * @return the date of departure
     */
    Date getDepartureDate();

    /**
     * 
     * @return the date of arrival
     */
    Date getArrivalDate();

    /**
     * 
     * @return the time of departure
     */
    String getDepartureTime();

    /**
     * 
     * @return the time of arrival
     */
    String getArrivalTime();

    /**
     * 
     * @return the flight's basic price
     */
    double getBasicPrice();

    /**
     * 
     * @return the list of the identifiers of the flight attendants
     */
    List<String> getFlightAttendantIdentifiersList();

}