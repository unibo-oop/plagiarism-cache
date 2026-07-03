package model;

import java.time.LocalDate;
import utilities.Room;
import utilities.Bed;
import utilities.Department;

public interface Hospitalization {

	/**
	 * 
	 * @return recovery's code.
	 */
	String getCode();
	/**
	 * 
	 * @return recovery's start date.
	 */
	LocalDate getInitDate();
	/**
	 * @return hospitalization's end date.
	 */
	LocalDate getEndDate();
	/**
	 * Change discharge date.
	 * @param date.
	 */
	void setEndDate(final LocalDate date);
	/**
	 * change recovery's date.
	 * @param state.
	 *
	 */
	void setState(final String state);
	/**
	 * 
	 * @return recovery's state.
	 */
	String getState();
	/**
	 * @return the patient recovered.
	 */
	Person getPatient();
	/**
	 * @return the department.
	 */
	Department getDepartment();
	/**
	 * @return the room.
	 */
	Room getRoom();
	/**
	 * @return the bed where the patient is.
	 */
	Bed getBed();
	/**
	 * @return the cause of hospitalization.
	 */
	String getCause();
	/**
	 * @return the doctor who order the hospitalization.
	 */
	Worker getStaff();
	/**
	 * add some information to hospitalization.
	 * @param note
	 */
	void setNote(final String note);
	/**
	 * return recoveries information.
	 * @return some notes.
	 */
	String getNote();



}
