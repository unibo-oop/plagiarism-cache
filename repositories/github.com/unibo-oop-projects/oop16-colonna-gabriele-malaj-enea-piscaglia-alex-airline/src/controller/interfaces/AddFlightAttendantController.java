package controller.interfaces;

import java.text.ParseException;

/**
 * Add Flight Attendant Controller interface.
 */
public interface AddFlightAttendantController {

    /**
     * Adds a flight attendant to the list of flight attendants.
     * 
     * @throws ParseException if the date of birth's format is not correct
     */
    void addFlightAttendant() throws ParseException;

}