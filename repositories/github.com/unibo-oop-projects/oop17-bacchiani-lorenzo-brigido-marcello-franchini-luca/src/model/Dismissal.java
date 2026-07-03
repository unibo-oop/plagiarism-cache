package model;

import java.time.LocalDate;

public interface Dismissal {

	/**
	 * 
	 * @return
	 * 	the date of dismiss.
	 */
	LocalDate getDate();
	/**
	 * 
	 * @return
	 * 		the reason of dismiss.
	 */
	String getReason();
	/**
	 * 
	 * @return
	 * 		the person has been dismissed.
	 */
	Worker getStaff();
}
