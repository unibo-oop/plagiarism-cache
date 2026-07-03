package controller.interfaces;

import java.text.ParseException;

/**
 * Add Flight Controller interface.
 */
public interface AddFlightController {

    /**
     * Adds a flight to the list of flights.
     * 
     * @throws NumberFormatException if basic price do not contain a number
     * @throws ParseException if departure date's and arrival date's format is not correct
     */
    void addFlight() throws NumberFormatException, ParseException;

}