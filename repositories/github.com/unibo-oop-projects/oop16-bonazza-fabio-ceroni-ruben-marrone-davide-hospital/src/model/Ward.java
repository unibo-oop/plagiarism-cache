package model;

import java.util.List;
import java.util.Map;
/**
 * Ward with rooms for patients.
 * 
 *
 */
public interface Ward {

    /**
     * occupiedRooms getter, filters out the rooms where the value (a patient) is empty.
     * @return Map<Integer, Patient> occupiedRooms
     */
    Map<Integer, Patient> getOccupiedRooms();

    /**
     * returns all patients in a ward.
     * @return List of Patients
     */
    List<Patient> getPatients();

    /**
     * Name getter.
     * @return String name
     */
    String getName();

    /**
     * numRooms getter.
     * @return int numRooms
     */
    int getNumRooms();


    /**
     * enters a Patient into a ward.
     * @throws IllegalStateException e
     * @param p Patient
     */
    void enterPatient(Patient p) throws IllegalStateException;

    /**
     * removes a patient from its ward and from the hospital.
     * 
     * @param p Patient
     * @throws IllegalArgumentException 
     */

    void removePatient(Patient p) throws IllegalArgumentException;


}