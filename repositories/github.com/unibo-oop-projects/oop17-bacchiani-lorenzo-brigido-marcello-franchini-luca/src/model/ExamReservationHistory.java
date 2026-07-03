package model;

import java.util.Set;


public interface ExamReservationHistory {
	/**
	 * 
	 * @param p
	 *      add new exam's booking.
	 */
	void addBooking(final ExamReservation p) throws IllegalArgumentException;
	/**
	 * 
	 * @return all booking.
	 */
	Set<ExamReservation> getAllBooking();
	/**
	 * Adds the booking in the file(data backup).
	 */
	void insertInFile();
}
