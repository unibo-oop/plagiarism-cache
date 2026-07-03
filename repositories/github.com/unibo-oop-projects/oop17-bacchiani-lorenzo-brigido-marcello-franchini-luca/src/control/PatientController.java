package control;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;


import model.Person;
import model.Worker;
import utilities.Bed;
import model.ExamReservation;
import model.Hospitalization;

public interface PatientController {

     /**
	 * add a new patient. 
	 * @param name
	 * @param surname
	 * @param fiscalCode
	 * @param date
	 * @param place
	 * @param residency
	 */
	void addPatient(final String name, final String surname, final String fiscalCode, 
			final LocalDate date, final String place, final String residency, final String sex);
	/**
	 * add a new booking.
	 * @param date
	 * @param hour
	 * @param minute
	 * @param fiscalCodePaz
	 * @param examName
	 * @param fiscalCodeMed
	 */
	void addBook(final LocalDate date, final LocalTime time, final String fiscalCodePaz, final String examName, final Worker p);
	/**
	 * add a new recovery.
	 * @param cod
	 * @param fiscalCodePaz
	 * @param departName
	 * @param roomNumber_bedNumber
	 * @param cause
	 * @param fiscalCodePers
	 */
	void addHospitalization(final String cod, final String fiscalCodePaz, final String departName, 
			final String roomNumberbedNumber, final String cause, final Worker pers);
	/**
	 * 
	 * @return all patients.
	 */
	Set<Person> getAllPatients();
	/**
	 * 
	 * @return all recoveries.
	 */
	Set<Hospitalization> getAllRecoveries();
	/**
	 * 
	 * @return all exams.
	 */
	Set<ExamReservation> getExams();
	/**
	 * @param departName
	 * @return all free beds of a specific department.
	 */
	Set<Bed> freeBeds(String departName);
	/**
	 * set a patient.
	 * @param fiscalCode
	 */
	void setPatientForInf(final String fiscalCode);
	/**
	 * get a patient.
	 * @return the patient's look for.
	 */
	Person getPatientForInf();
	/**
	 * modify specified recovery's state.
	 * @param fiscalCode
	 * @param state
	 * @param note
	 */
	void modifyHospitalizationState(final String fiscalCode, final String state, final LocalDate date, final String note);



}
