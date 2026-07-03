package model;

import java.time.LocalDate;

import utilities.Medicine;

public interface Prescription {


	/**
	 * 
	 * @return
	 * 		prescription's code.
	 */
	String getCode();
	/**
	 * 
	 * @return
	 * 		the patient who the prescription has been made for.
	 */
	Person getPatient();
	/**
	 * 
	 * @return
	 *    the doctor who made the prescription;
	 */
	Worker getStaff();
	/**
	 * 
	 * @return
	 *    the medicine which has been prescripted.
	 */
	Medicine getMedicine();
	/**
	 * 
	 * @return
	 *     the date when the prescription has been released.
	 */
	LocalDate getDate();


}
