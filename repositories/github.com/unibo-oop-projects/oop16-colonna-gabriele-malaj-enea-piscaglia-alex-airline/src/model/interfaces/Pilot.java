package model.interfaces;

/**
 * 
 * Represents a pilot.
 */
public interface Pilot extends Person {

    /**
     * 
     * @return the pilot identifier
     */
    String getPilotId();

    /**
     * 
     * @return the flight license identifier
     */
    String getFlightLicenseId();

}