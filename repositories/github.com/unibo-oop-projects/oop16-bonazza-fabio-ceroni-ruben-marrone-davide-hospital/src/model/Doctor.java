package model;

import java.util.List;

/**
 * 
 * Interface for Doctor.
 *
 */
public interface Doctor {

    /**
     * name getter.
     * @return doctor's name
     */
    String getName();

    /**
     * surname getter.
     * @return doctor's surname
     */
    String getSurname();

    /**
     * codfis getter.
     * @return doctor's fiscal code
     */
    String getCodfis();

    /**
     * sex getter.
     * @return doctor's sex
     */
    String getSex();

    /**
     * specialization getter.
     * @return doctor's specialization
     */
    String getSpecialization();

    /**
     * age getter.
     * @return doctor's age
     */
    int getAge();

    /**
     * gets the assigned Patients.
     * @return list of assigned patients, null if empty
     * 
     */
    List<Patient> getAssignedPatients();

    /**
     * gets the doctors surgery status.
     * @return true if in surgery, false otherwise
     */
    boolean isInSurgery();

    /**
     * sets the doctors surgery status.
     * @param status boolean
     */
    void setSurgery(boolean status);

    /**
     * assigns a patient to the doctor.
     * @throws IllegalArgumentException e
     * @param p patient
     */
    void assignPatient(Patient p) throws IllegalArgumentException;

    /**
     * removes a patient from the doctor's assigned patients list.
     * @throws IllegalArgumentException e
     * @param p patient
     */
    void dismissPatient(Patient p) throws IllegalArgumentException;

}