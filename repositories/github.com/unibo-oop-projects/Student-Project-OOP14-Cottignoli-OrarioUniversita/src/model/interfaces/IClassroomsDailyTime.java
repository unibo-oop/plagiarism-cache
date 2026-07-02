package model.interfaces;

import java.util.Optional;
import java.util.Set;

import model.Classrooms;

/**
 * Interface that defines a daily academic timetable {@link IDailyTime} for each classroom in {@link Classrooms#values()}.
 * 
 * @author Lorenzo Cottignoli
 *
 */

public interface IClassroomsDailyTime extends java.io.Serializable {
	
	/**
	 * It adds a subject in the daily timetable of a specific classroom. It must be possible for a teacher to have two different classrooms
	 * at the same time for the same subject, but it will be impossible for the same teacher to have two different subject at the same time
	 * in two different classrooms.
	 * 
	 * @param sub Subject to be added.
	 * @param room Classroom in which the lesson takes place.
	 * @param hour Lesson starting time.
	 * @param n Number of consecutive hour in which the subject passed as a parameter starting from hour is taught.
	 * @throws IllegalArgumentException if room is null, [se parte o] all selected hours in classrooms different from room contain 
	 * 									a different subject sub but with the same teacher, or in the cases specified in the 
	 * 									following method {@link IDailyTime#add(ISubject, int, int)}.
	 */
	void add(ISubject sub, Classrooms room, int hour, int n);
	
	/**
	 * It removes from the daily timetable all the subjects in a specified classroom in the selected hours.
	 * 
	 * @param room Classroom in which lessons must be removed.
	 * @param hour Hour in which we start to remove subjects.
	 * @param n Number of consecutive hours in which we must remove subjects starting from hour. 
	 * @throws IllegalArgumentException if room is null or in the cases that are specified in the 
	 * 									following method {@link IDailyTime#remove(int, int)}.
	 */
	void remove(Classrooms room, int hour, int n);
	
	/**
	 * It gives back the course that takes place in a specific hour in a specific classroom.
	 * 
	 * @param room The classroom in which it is necessary to look for the subject to be given back.
	 * @param hour Hour in which it is necessary to look for the subject to be given back.
	 * @return The subject that will be hold at the time and in the classroom specified. It is used an Optional in order to 
	 * avoid to give back null {@link Optional} in case that there is no subject in the selected hour.
	 * @throws IllegalArgumentException if room is null or in the cases specified in the following method {@link IDailyTime#getSubject(int)}.
	 */
	Optional<ISubject> getSubject(Classrooms room, int hour);
	
	/**
	 * It gives back the daily timetable of a specific classroom.
	 * 
	 * @param room Classroom from which the daily timetable has to be copied.
	 * @return The daily timetable of the classroom room.
	 * @throws IllegalArgumentException if room is null.
	 */
	IDailyTime getDailyTime(Classrooms room);
	
	/**
	 * It creates a copy of itself, in order to respect the method {@link #equals(Object)}.
	 * 
	 * @return A copy of the object that call this method.
	 */
	IClassroomsDailyTime copy();
	
	/**
	 * It gives back a set containing in which classroom is the teacher of a specific hour.
	 * 
	 * @param teach Name of the teacher of which we want to know where he gives his lesson in a specific hour.
	 * @param hour Hour in which we have to look in order to find in which classroom he gives lesson.
	 * @return Set that contain classrooms or the empty set, in case that the teacher isn’t giving lesson in the specified 
	 * 		   hour or if teach is null.
	 */
	Set<Classrooms> whereTeaching(String teach, int hour);
	
	/**
	 * It gives back a set containing in which classroom a course takes place in a specific hour.
	 * 
	 * @param sub Subject of which we want to know where it takes place in a specific hour.
	 * @param hour Hour in which to look for in the different classrooms.
	 * @return Set containing classrooms or empty set in case lesson is not in the specified hour or if sub is null.
	 */
	Set<Classrooms> wherePerforming(ISubject sub, int hour);
}
