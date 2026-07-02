package model.interfaces;

import java.util.Optional;

/**
 * Interface that defines the daily academic timetable and the operations applicable to it.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public interface IDailyTime extends java.io.Serializable {

	/**
	 * Int constant that represents the number of hours in a specified academic day.
	 */
	int HOURS = 9;
	
	/**
	 * Int constant that represents the starting time of academic courses.
	 */
	int FIRST_HOUR = 9;		
	
	/**
	 * Aggiunge una materia nell'arco di tempo indicato.
	 * 
	  * It adds a subject in the specified time frame.
	 * 
	 * @param sub Subject to be inserted on.
	 * @param hour Lesson starting time.
	 * @param n Number of consecutive hours in which the subject passed as parameter starting from hour is taught.
	 * @throws IllegalArgumentException if a certain number of hours or all specified hours are already occupied by other courses
	 * 									or if hour and n have contradictory values. hour must be bigger or the same
	 *									of {@link #FIRST_HOUR} and less than {@link #FIRST_HOUR} + {@link #HOURS}, 
	 * 									n must be bigger than 0 and the sum hour + n must be between {@link #FIRST_HOUR} 
	 * 									and {@link #FIRST_HOUR} + {@link #HOURS}.

	 */
	void add(ISubject sub, int hour, int n);
	
	/**
	 * It removes all courses in the specified time frame, in case of lack of courses it doesn't throw any exception.
	 * 
	 * @param hour Hour from which starting to remove subjects.
	 * @param n Number of consecutive hours in which removing subjects starting from hour. 
	 * @throws IllegalArgumentException if hour and n have contradictory values. hour must be bigger or the same
	 *									of {@link #FIRST_HOUR} and less than {@link #FIRST_HOUR} + {@link #HOURS}, 
	 * 									n must be bigger than 0 and the sum hour + n must be between {@link #FIRST_HOUR} 
	 * 									and {@link #FIRST_HOUR} + {@link #HOURS}.
	 */
	void remove(int hour, int n);
	
	/**
	 * It gives back the course in a specified hour.
	 * 
	 * @param hour Hour in which looking for the subject to be given back.
	 * @return The course given in the specified hour. It is used an Optional in order to avoid giving back null {@link Optional} 
	 * 		   in case that there is no course in the specified hour.
	 * @throws IllegalArgumentException if hour and n have contradictory values. hour must be bigger or the same
	 * 									of {@link #FIRST_HOUR} and less than {@link #FIRST_HOUR} + {@link #HOURS}.

	 */
	Optional<ISubject> getSubject(int hour);
	
	/**
	 * It creates a copy of itself, in order to respect the method {@link #equals(Object)}.
	 * 
	 * @return A copy of the object that call this method.
	 */
	IDailyTime copy();
}
