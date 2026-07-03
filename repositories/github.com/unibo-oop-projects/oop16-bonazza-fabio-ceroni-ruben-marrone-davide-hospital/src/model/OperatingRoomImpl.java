package model;

import java.io.Serializable;

/**
 * Implementation of OperatingRoom.
 */
public class OperatingRoomImpl implements OperatingRoom, Serializable {

    private static final long serialVersionUID = 3859013547562707649L;
    private final String name;
    private Patient patient;
    private Doctor doctor;

    /**
     * Constructor for OperatingRoom.
     * 
     * @param name
     *    the name of the OperatingRoom
     * @throws IllegalArgumentException eRoom 
     */
    public OperatingRoomImpl(final String name) throws IllegalArgumentException {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
        this.patient = null;
        this.doctor = null;
    }

    @Override
    public void addPatient(final Patient patient) throws IllegalStateException {
        if (patient.isBeingOperated()) {
            throw new IllegalStateException("Patient is already being operated.");
        } else if (patient.getDoctor().isInSurgery()) {
            throw new IllegalStateException("Doctor is already operating.");
        } else if (this.patient != null) {
            throw new IllegalStateException("Room is not empty.");
        } else {
            patient.setOperated(true);
            patient.getDoctor().setSurgery(true);
            this.patient = patient;
            this.doctor = this.patient.getDoctor();
        }
    }

    @Override
    public void removePatient() throws IllegalStateException {
        if (this.patient == null) {
            throw new IllegalStateException("Room is empty.");
        } else {
            this.patient.setOperated(false);
            this.doctor.setSurgery(false);
            this.patient = null;
            this.doctor = null;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Patient getPatient() {
        return this.patient;
    }

    @Override
    public Doctor getDoctor() {
        return this.doctor;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
