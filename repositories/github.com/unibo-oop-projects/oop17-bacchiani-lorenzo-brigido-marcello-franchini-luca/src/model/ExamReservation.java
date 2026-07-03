package model;

import java.time.LocalDate;
import java.time.LocalTime;

import utilities.Exam;

public interface ExamReservation {
	/**
	 * 
	 * @return the day of the exam.
	 */
	LocalDate getDate();
	/**
	 * 
	 * @return the hour of the exam.
	 */
	LocalTime getHour();
	/**
	 * 
	 * @return the patient who booked the exam.
	 */
	Person getPatient();
	/**
	 * @return the exam booked.
	 */
	Exam getExam();
	/**
	 * @return the doctor who executes the exam.
	 */
	Worker getStaff();

}
