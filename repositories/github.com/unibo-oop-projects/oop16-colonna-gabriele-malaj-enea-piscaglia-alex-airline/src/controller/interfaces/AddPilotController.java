package controller.interfaces;

import java.text.ParseException;

/**
 * Add Pilot Controller interface.
 */
public interface AddPilotController {

    /**
     * Adds a pilot to the list of pilots.
     * 
     * @throws ParseException if date of birth's format is not correct
     */
    void addPilot() throws ParseException;

}