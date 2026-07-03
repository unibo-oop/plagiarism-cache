package controller.interfaces;

import java.io.IOException;

/**
 * Utilities interface.
 */
public interface Utilities {

    /**
     * Saves all data on files.
     * 
     * @throws IOException if an I/O error has occurred
     */
    void saveData() throws IOException;

    /**
     * Loads all data from files.
     * 
     * @throws IOException if an I/O error has occurred
     * @throws ClassNotFoundException if class of a serialized object cannot be found
     */
    void loadData() throws IOException, ClassNotFoundException;

    /**
     * Saves id on file.
     * 
     * @throws IOException if an I/O error has occurred
     */
    void saveId() throws IOException;

    /**
     * Saves the list of staff on file.
     * 
     * @throws IOException if an I/O error has occurred
     */
    void saveStaff() throws IOException;

    /**
     * Saves the list of planes on file.
     * 
     * @throws IOException if an I/O error has occurred
     */
    void savePlanes() throws IOException;

    /**
     * Saves the list of destinations on file.
     * 
     * @throws IOException if an I/O error has occurred
     */
    void saveDestinations() throws IOException;

    /**
     * Saves the list of pilots on file.
     * 
     * @throws IOException if an I/O error has occurred
     */
    void savePilots() throws IOException;

    /**
     * Saves the list of flight attendants on file.
     * 
     * @throws IOException if an I/O error has occurred
     */
    void saveFlightAttendants() throws IOException;

    /**
     * Saves luggage on file.
     * 
     * @throws IOException if an I/O error has occurred
     */
    void saveLuggage() throws IOException;

    /**
     * Saves flights on file.
     * 
     * @throws IOException if an I/O error has occurred
     */
    void saveFlights() throws IOException;

    /**
     * Saves the list of reservations on file.
     * 
     * @throws IOException if an I/O error has occurred
     */
    void saveReservations() throws IOException;

}