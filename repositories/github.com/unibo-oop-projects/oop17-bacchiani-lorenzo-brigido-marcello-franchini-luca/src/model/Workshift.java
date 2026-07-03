package model;

import java.time.LocalTime;

import utilities.Department;


public interface Workshift {
	/**
	 * 
	 * @return department where the emplyee works.
	 */
	Department getDepartment();
	/**
	 *@return starting time.
	 */
	LocalTime getStartingTime();
	/**
	 *@return end time.
	 */
	LocalTime getEndTime();
	/**
	 * 
	 * @return
	 * 		specified workhour's state.
	 */
	String getState();
	/**
	 * change the state of the specified work hour.
	 */
	void changeState();
	/**
	 * 
	 * @return who works in this specific workhour.
	 */
	Worker getWorker();



}
