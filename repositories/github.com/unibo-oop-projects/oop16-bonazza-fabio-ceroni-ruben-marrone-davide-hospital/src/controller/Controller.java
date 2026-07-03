package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import model.*;

/**
 * Controller interface.
 */

public interface Controller {

    /**
     * creates a Patient using the input info and
     * uses the Model method to add him to the queue.
     *
     * @param name
     *          name of the Patient
     * @param surname
     *          surname of the Patient
     * @param codFis
     *          fiscal code of the Patient
     * @param age
     *          age of the Patient
     * @param sex
     *          sex of the Patient
     * @param disease
     *          disease of the Patient
     * @param priority
     *          priority of the Patient
     * @throws IllegalStateException e
     * @throws IllegalArgumentException e
     */
    void createPatient(String name, String surname, String codFis,
        String sex, String disease, int age, String priority) throws IllegalArgumentException, IllegalStateException;

    /**
     * uses the Model method to dismiss a Patient.
     * 
     * @param patient
     *          the Patient to dismiss
     */
    void dismissPatient(Patient patient) throws IllegalStateException;

    /**
     * creates a Doctor using the input info and
     * uses the Model method to add him to the queue.
     * 
     * @param name
     *          name of the Doctor
     * @param surname
     *          surname of the Doctor
     * @param codFis
     *          fiscal code of the Doctor
     * @param age
     *          age of the Doctor
     * @param sex
     *          sex of the Doctor
     * @param specialization
     *          specialization of the Doctor
     * @throws IllegalStateException e
     */
    void createDoctor(String name, String surname, String codFis, int age, 
        String sex, String specialization) throws IllegalArgumentException, IllegalStateException;

    /**
     * uses the Model method to fire a Patient.
     * 
     * @param doctor
     *          the Doctor to fire
     * @throws IllegalStateException e
     */
    void fireDoctor(Doctor doctor) throws IllegalStateException;

    /**
     * creates a Ward using the input info and
     * uses the Model method to add it to the hospital.
     * 
     * @param name
     *          name of the Ward
     * @param numRooms
     *          number of rooms in the Ward
     * @throws IllegalStateException e
     */
    void createWard(String name, int numRooms) throws IllegalStateException;

    /**
     * creates a OperatingRoom using the input info and
     * uses the Model method to add it to the hospital.
     * 
     * @param name
     *          name of the OperatingRoom
     * @throws IllegalStateException e
     */
    void createOperatingRoom(String name) throws IllegalStateException;

    /**
     * uses the Model method to cancel an OperatingRoom.
     * 
     * @param room
     *          the OperatingRoom to cancel
     * @throws IllegalStateException e
     */
    void deleteOperatingRoom(OperatingRoom room) throws IllegalStateException;

    /**
     * uses the Model method to assign a Patient to a Doctor.
     * 
     * @param patient
     *    the Patient to assign
     * @param doctor
     *    the Doctor to whom the Patient is assigned
     * @param ward
     *    the Ward in which the Patient will be placed
     * @throws IllegalStateException e
     */
    void assignPatient(Patient patient, Doctor doctor, Ward ward) throws IllegalStateException;

    /**
     * uses the Model method to return the List of Patients in the Wards.
     * 
     * @return list of patients
     * @throws IllegalStateException e
     */
    List<Patient> getPatients() throws IllegalStateException;

    /**
     * uses the Model method to return the List of Patients in the waiting room.
     * 
     * @return list of waiting patients
     * @throws IllegalStateException e
     */
    List<Patient> getWaitingPatients() throws IllegalStateException;

    /**
     * uses the Model method to return the List of all Doctors.
     * 
     * @return list of doctors
     * @throws IllegalStateException e
     */
    List<Doctor> getDoctors() throws IllegalStateException;

    /**
     * uses the model method to return a list of the wards in the hospital.
     * 
     * @return list of wards
     * @throws IllegalStateException e
     */
    List<Ward> getWards() throws IllegalStateException;

    /**
     * uses the Model method to start a surgery operation.
     * 
     * @param patient
     *    the Patient to operate
     * @param room
     *    the room in which the Patient will be operated
     * @throws IllegalStateException e
     */
    void startOperation(Patient patient, OperatingRoom room) throws IllegalStateException;

    /**
     * uses the Model method to end a surgery operation.
     * 
     * @param room
     *    the room in which the operation will be ended
     * @throws IllegalStateException e
     */
    void endOperation(OperatingRoom room) throws IllegalStateException;

    /**
     * uses the Model method to return the List of all Operating Rooms.
     * 
     * @return list of operating rooms
     * @throws IllegalStateException e
     */
    List<OperatingRoom> getOperatingRooms() throws IllegalStateException;

    /**
     * uses the Model method to return the List of all free Operating Rooms.
     * 
     * @return list of operating rooms
     * @throws IllegalStateException e
     */
    List<OperatingRoom> getfreeOperatingRooms() throws IllegalStateException;

    /**
     * uses the Model method to update a Patient's log.
     * 
     * @param patient
     *    the Patient whose log will be updated
     * @param log
     *    the message to add to the log
     */
    void updateLog(Patient patient, String log) throws IllegalStateException;

    /**
     * Save the current hospital.
     * 
     * @param file
     *    the file on which to save the hospital
     * @throws IOException e
     * @throws FileNotFoundException e
     */
    void save(String file) throws FileNotFoundException, IOException;

    /**
     * load a hospital.
     * 
     * @param file
     *    the file from which to load the hospital
     * @throws IOException e
     * @throws FileNotFoundException e
     * @throws ClassNotFoundException e
     */
    void load(String file) throws FileNotFoundException, IOException, ClassNotFoundException;

}