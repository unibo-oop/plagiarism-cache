package model.interfaces;

/**
 * 
 * Represents a destination.
 */
public interface Destination {

    /**
     * 
     * @return the destination identifier
     */
    String getDestinationId();

    /**
     * 
     * @return the destination country
     */
    String getCountry();

    /**
     * 
     * @return the destination city
     */
    String getCity();

    /**
     * 
     * @return the destination airport's name
     */
    String getAirportName();

}