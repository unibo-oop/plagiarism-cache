package model;

/**
 * Interface for OperatingRoom.
 */
public interface OperatingRoom {

    /**
     * Adds a patient to the operating room.
     * 
     * @param patient
     *    Patient to add.
     * @throws IllegalStateException e
     */
    void addPatient(Patient patient) throws IllegalStateException;

    /**
     * Removes the Patient from the operating room.
     * 
     * @throws IllegalStateException e
     */
    void removePatient() throws IllegalStateException;

    /**
     * returns the name of the operating room.
     * @return String name of the operating room
     */
    String getName();

    /**
     * returns the patient in the operating room.
     * @return Patient Patient in the operating room
     */
    Patient getPatient();

    /**
     * returns the doctor in the operating room.
     * @return Doctor Doctor in the operating room
     */
    Doctor getDoctor();

}
