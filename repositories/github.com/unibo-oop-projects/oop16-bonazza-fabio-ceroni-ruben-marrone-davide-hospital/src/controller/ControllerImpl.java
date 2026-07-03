package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import model.*;

/**
 *  Controller implementation.
 */
public final class ControllerImpl implements Controller {

    private static final ControllerImpl SINGLETON = new ControllerImpl();
    private Model hosp = new Hospital();

    private ControllerImpl() {
    }

    public static ControllerImpl getController() {
        return SINGLETON;
    }

    @Override
    public void createPatient(final String name, final String surname, final String codFis,
        final String sex, final String disease, final int age, final String priority) 
            throws IllegalArgumentException, IllegalStateException {
        final Patient patient = new PatientImpl.Builder()
            .name(name)
            .surname(surname)
            .codfis(codFis)
            .sex(sex)
            .disease(disease)
            .age(age)
            .priority(priority)
            .build();

        hosp.addPatient(patient);
    }

    @Override
    public void dismissPatient(final Patient patient) throws IllegalStateException {
        hosp.removePatient(patient);
    }

    @Override
    public void createDoctor(final String name, final String surname, final String codFis,
        final int age, final String sex, final String specialization) throws IllegalArgumentException, IllegalStateException {
        final Doctor doctor = new DoctorImpl.Builder()
            .name(name)
            .surname(surname)
            .codfis(codFis)
            .sex(sex)
            .specializ(specialization)
            .age(age)
            .build();

        hosp.addDoctor(doctor);
    }

    @Override
    public void fireDoctor(final Doctor doctor) throws IllegalStateException {
        hosp.removeDoctor(doctor);
    }

    @Override
    public void createWard(final String name, final int numRooms) throws IllegalStateException {
        final Ward ward = new WardImpl(name, numRooms);
        hosp.addWard(ward);
    }

    @Override
    public void createOperatingRoom(final String name) throws IllegalStateException {
        final OperatingRoom room = new OperatingRoomImpl(name);
        hosp.addOperatingRoom(room);
    }

    @Override
    public void deleteOperatingRoom(final OperatingRoom room) throws IllegalStateException {
        hosp.removeOperatingRoom(room);
    }

    @Override
    public void assignPatient(final Patient patient, final Doctor doctor, final Ward ward) throws IllegalStateException {
        hosp.treatPatient(patient, doctor, ward);
    }

    @Override
    public List<Patient> getPatients() throws IllegalStateException {
        return hosp.getAllPatients();
    }

    @Override
    public List<Patient> getWaitingPatients() throws IllegalStateException {
        return hosp.getWaitingPatients();
    }

    @Override
    public List<Doctor> getDoctors() throws IllegalStateException {
        return hosp.getAllDoctors();
    }

    @Override
    public List<Ward> getWards() throws IllegalStateException {
        return hosp.getAllWards();
    }

    @Override
    public void startOperation(final Patient patient, final OperatingRoom room) throws IllegalStateException {
        hosp.startSurgery(patient, room);
    }

    @Override
    public void endOperation(final OperatingRoom room) throws IllegalStateException {
        hosp.endSurgery(room);
    }
    @Override
    public List<OperatingRoom> getOperatingRooms() throws IllegalStateException {
        return hosp.getAllOperatingRooms();
    }

    @Override
    public List<OperatingRoom> getfreeOperatingRooms() throws IllegalStateException {
        return hosp.getFreeOperatingRooms();
    }

    @Override
    public void updateLog(final Patient patient, final String log) throws IllegalStateException {
        hosp.updatePatientLog(patient, log);
    }

    @Override
    public void save(final String file) throws FileNotFoundException, IOException {
        final ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
        hosp.serializeModel(output);
    }

    @Override
    public void load(final String file) 
        throws FileNotFoundException, IOException, ClassNotFoundException {
        final ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
        hosp = hosp.deserializeModel(input);
    }

}
