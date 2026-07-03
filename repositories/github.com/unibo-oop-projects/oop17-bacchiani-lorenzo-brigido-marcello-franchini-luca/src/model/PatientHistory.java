package model;

import java.util.Set;

public interface PatientHistory {

	/**
	 * 
	 * @param p
	 *      add new Patient.
	 *
	 */
	void addPatient(final Person p);
	/**
	 * 
	 * @return all patient.
	 */
	Set<Person> getAllPatient();




}
