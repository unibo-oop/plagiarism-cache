package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 */
public interface Model {
    /**
     * Adds a new patient to the queue.
     * 
     * @throws IllegalStateException e
     * @param p Patient to add.
     */
    void addPatient(Patient p) throws IllegalStateException;
    /**
     * Removes a Patient from the hospital.
     * @throws IllegalStateException e
     * @param p Patient to remove
     */
    void removePatient(Patient p) throws IllegalStateException;
    /**
     * Adds a new Doctor to the hospital.
     * @throws IllegalStateException e
     * @param d Doctor to add.
     */
    void addDoctor(Doctor d) throws IllegalStateException;
    /**
     * Removes a Doctor from the hospital.
     * @throws IllegalStateException e
     * @param d Doctor to remove
     * 
     */
    void removeDoctor(Doctor d) throws IllegalStateException;
    /**
     * adds a ward to the list.
     * @throws IllegalStateException e
     * @param w Ward
     */
    void addWard(Ward w) throws IllegalStateException;
    /**
     * removes a ward from the hospital.
     * @param w Ward to remove
     * @throws IllegalStateException e
     */
    void removeWard(Ward w) throws IllegalStateException;
    /**
     * Adds a new OperatingRoom to the hospital.
     * 
     * @param room OperatingRoom to add.
     * @throws IllegalStateException e
     */
    void addOperatingRoom(OperatingRoom room) throws IllegalStateException;
    /**
     * Remove an OperatingRoom from the hospital.
     * 
     * @param room OperatingRoom to remove.
     * @throws IllegalStateException e
     */
    void removeOperatingRoom(OperatingRoom room) throws IllegalStateException;
    /**
     * assigns a room and a doctor to a patient.
     * @throws IllegalStateException e
     * @param p patient
     * @param d doctor
     * @param w ward
     */
    void treatPatient(Patient p, Doctor d, Ward w) throws IllegalStateException;
    /**
     * returns a list of all patients in the hospital.
     * @throws IllegalStateException e
     * @return list of patients
     */
    List<Patient> getAllPatients() throws IllegalStateException;
    /**
     * returns a list of all Doctors in the hospital.
     * @throws IllegalStateException e
     * @return list of doctors
     */
    List<Doctor> getAllDoctors() throws IllegalStateException;
    /**
     * returns a list of all wards in the hospital.
     * @throws IllegalStateException e
     * @return list of wards
     */
    List<Ward> getAllWards() throws IllegalStateException;
    /**
     * assigns an operatingroom to a patient.
     * @param p patient
     * @param r operatingroom
     * @throws IllegalStateException e
     */
    void startSurgery(Patient p, OperatingRoom r) throws IllegalStateException;
    /**
     * removes a patient from and operatingroom.
     * @param r operatingroom
     * @throws IllegalStateException e
     */
    void endSurgery(OperatingRoom r) throws IllegalStateException;
    /** 
     * returns a list of all operating rooms.
     * @throws IllegalStateException e
     * @return list of operatingrooms
     */
    List<OperatingRoom> getAllOperatingRooms() throws IllegalStateException;
    /** 
     * returns a list of all free operating rooms.
     * @throws IllegalStateException e
     * @return list of free operatingrooms
     */
    List<OperatingRoom> getFreeOperatingRooms() throws IllegalStateException;

    /**
     * updates a patient's log.
     * @throws IllegalStateException e
     * @param p patient
     * @param log log
     */
    void updatePatientLog(Patient p, String log) throws IllegalStateException;
    /**
     * waitingroom getter.
     * @throws IllegalStateException e
     * @return list of patients in waitingroom
     */
    List<Patient> getWaitingPatients() throws IllegalStateException;
    /**
     * saves the state of the model to a file.
     * @param out stream where to serialize
     * @throws IOException e
     */
    void serializeModel(ObjectOutputStream out) throws IOException;
    /**
     * Loads the state of the model from a file.
     * @param in stream to get data from
     * @return a Hospital from the file
     * @throws ClassNotFoundException e
     * @throws IOException e
     */
    Hospital deserializeModel(ObjectInputStream in) throws ClassNotFoundException, IOException;

}