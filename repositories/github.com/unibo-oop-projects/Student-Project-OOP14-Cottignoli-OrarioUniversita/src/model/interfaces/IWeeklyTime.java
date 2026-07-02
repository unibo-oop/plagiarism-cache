package model.interfaces;

import java.util.Optional;
import java.util.Set;

import model.Classrooms;
import model.Days;

/**
 * Interface that defines the complete timetable {@link IClassrooomsDailyTime} of an academic week, namely it represents
 * the complete timetable of a semester of the faculty of informatics science and technology in Cesena.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public interface IWeeklyTime extends java.io.Serializable {

	/**
	 * It adds a course in the timetable of a classroom at the selected time of a specified day.
	 * 
	 * @param sub Course to be added in the timetable.
	 * @param d Day in which the course must be added.
	 * @param room Classroom in which the course will be given.
	 * @param hour Course starting time.
	 * @param n Number of consecutive hours in which the course is given.
	 * @throws IllegalArgumentException if d is null or in the cases that are specified in the method
	 * 									{@link IClassroomsDailyTime#add(ISubject, Classrooms, int, int)}.

	 */
	void add(ISubject sub, Days d, Classrooms room, int hour, int n);
		
	
	/**
	 * It removes from the timetable every course in a classroom in the selected hour of a specified day.
	 * 
	 * @param d Day in which a course is deleted.
	 * @param room Classroom in which a course is deleted.
	 * @param hour Hour in which starting to delete.
	 * @param n Number of consecutive hours that must be deleted.
	 * @throws IllegalArgumentException if d is null or in the cases that are specified in the method 
	 * 									{@link IClassroomsDailyTime#remove(Classrooms, int, int)}.
	 */
	void remove(Days d, Classrooms room, int hour, int n);
	
	/**
	 * It gives back the course given in a specified hour in a classroom in a specified day.
	 * 
	 * @param d Day in which looking for the course to be given back.
	 * @param room Classroom in which looking for the course to be given back.
	 * @param hour Hour in which looking for the course to be given back.
	 * @return The course given at the time, in the classroom and in the day that are specified. It is used an Optional to avoid 
	 * 			giving back null {@link Optional} in case in which there are no courses in the selected hour.
	 * @throws IllegalArgumentException if d is null or in the cases that are specified in the following method 
	 * 									{@link IClassroomsDailyTime#getSubject(Classrooms, int)}.

	 */
	Optional<ISubject> getSubject(Days d, Classrooms room, int hour);
	
	/**
	 * It gives back a copy of the daily timetable of all the classrooms {@link IClassroomsDailyTime} of a specific day.
	 * 
	 * @param d Day of which we want to have the complete daily timetable.
	 * @return Complete daily timetable of all the classrooms.
	 * @throws IllegalArgumentException if d is null.
	 */
	IClassroomsDailyTime getClassroomDailyTime(Days d);
	
	/**
	 * It creates a copy of itself, in order to respect the method {@link #equals(Object)}.
	 * 
	 * @return A copy of the object that call this method.
	 */
	IWeeklyTime copy();
	
	/**
	* It gives back a set containing in what classrooms is the specified teacher in a specified hour of the specified day.
	 * 
	 * @param teach Name of the teacher of which we want to know where he gives lesson.
	 * @param d Day in which looking for.
	 * @param hour Hour in which looking for between all different hours.
	 * @return Set containing classrooms or empty set in the case that the course is not in the specified hour or if teach is null.
	 * @throws IllegalArgumentException if d is null.
	 */
	Set<Classrooms> whereTeaching(String teach, Days d, int hour);
	
	/**
	 * It gives back a set containing in what classrooms is the specified course in a specified hour of the specified day.
	 * 
	 * @param sub Subject of which we want to know where it is in a specified hour.
	 * @param d Day in which looking for.
	 * @param hour Hour in which looking for between all classrooms.
	 * @return Set containing classrooms or empty set in the case that the course is not in the specified hour or if teach is null.
	 * @throws IllegalArgumentException if d is null.
	 */
	Set<Classrooms> wherePerforming(ISubject sub, Days d, int hour);
}
