package control;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import model.Prescription;
import model.Worker;
import model.Workshift;


public interface PersonalController {
	/**
	 * add a new worker.
	 * @param name
	 * @param surname
	 * @param fiscalCode
	 * @param birthPlace
	 * @param residency
	 * @param sex
	 * @param role
	 * @param date
	 */
	void addWorker(final String name, final String surname, final String fiscalCode, final String birthPlace, 
			final String residency, final String sex, final String role, final LocalDate date);
	/**
	 * dismiss a worker.
	 * 
	 * @param fiscalCode
	 * @param date
	 * @param reason
	 */
	void deleteWorker(final String fiscalCode, final LocalDate date, final String reason);
	/**
	 * add a new prescription.
	 * @param medicineName
	 * @param fiscalCode
	 * @param cod
	 */
	void addPrescription(final String medicineName, final String fiscalCode, final String cod);
	/**
	 * add a new work hour for the specified.
	 * @param p
	 * @param r
	 * @param start
	 * @param end
	 */
	void addWorkshift(final Worker p, final String department, final LocalTime start, final LocalTime end);
	/**
	 * modify work hour for the specified person.
	 * @param fiscalCode
	 * @param r
	 * @param start
	 * @param end
	 */
	void modifyWorkshift(final String fiscalCode, final String department, final LocalTime start, final LocalTime end);
	/**
	 * 
	 * @return all workers.
	 */
	Set<Worker> getWorkers();
	/**
	 * 
	 * @return all workshifts.
	 */
	Set<Workshift> getWorkshifts();
	/**
	 * 
	 * @return the doctor just added.
	 */
	Worker getJustAddedWorker();
	/**
	 * 
	 * @return all prescriptions.
	 */
	Set<Prescription> getPrescriptions();



}
