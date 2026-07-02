package model.interfaces;

import java.util.Optional;
import java.util.Set;

import model.Classrooms;
import model.Days;
import model.SubjectType;

/**
 * Interface that defines the complete annual timetable and manage the list of every course of the faculty
 * of informatics science and technology in Cesena. It represents the Model in the architectural pattern MVC.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public interface IModel extends java.io.Serializable {
	
	/**
	 * Constant to specify the first semester.
	 */
	int FIRST_SEM = 0;
	
	/**
	 * Constant to specify the second semester.
	 */
	int SEC_SEM = 1;
	
	/**
	 * It adds a subject in the timetable in a classroom in the selected hour, in a specified day of the specified semester.
	 * 
	 * @param sub Course to be added in the timetable.
	 * @param sem Semester in which the course has to be added.
	 * @param d Day in which the course has to be added.
	 * @param room Classroom in which the course has to be added.
	 * @param hour Course starting time. 
	 * @param n Number of consecutive hours in which the course has to be added.
	 * @throws IllegalArgumentException if sem does not correspond to {@link #FIRST_SEM} nor to {@link #SEC_SEM} or in the cases
	 * 									that are specified in the method {@link IWeeklyTime#add(ISubject, Days, Classrooms, int, int)}.   
	 */
	void add(ISubject sub, int sem, Days d, Classrooms room, int hour, int n);
			
	/**
	 * It removes every course in the timetable of a classroom in the specified hours of a specified day in the specified semester.
	 * 
	 * @param sem Semester in which the course has to be deleted.
	 * @param d Day in which the course has to be deleted.
	 * @param room Classroom in which the course has to be deleted.
	 * @param hour Hour from which starting to deleting.
	 * @param n Number of consecutive hours to be deleted.
	 * @throws IllegalArgumentException if sem does not correspond to {@link #FIRST_SEM} nor to {@link #SEC_SEM} or in the cases 
	 * 									that are specified in the method {@link IWeeklyTime#remove(Days, Classrooms, int, int)}.
	 */
	void remove(int sem, Days d, Classrooms room, int hour, int n);
	
	/**
	 * It creates a new course and it adds this to the list of available courses, in the case in which this course is already on the list 
	 * it does not add anything.
	 * 
	 * @param sub Name of the course.
	 * @param teach Name of the teacher.
	 * @param type Type of course.
	 * @throws IllegalArgumentException if one of the parameters is null.
	 */
	void addSubject(String sub, String teach, SubjectType type);
	
	/**
	 * It removes a course from the list of available courses, in the case of lack of the course passed as a parameter it does not occur anything.
	 * 
	 * @param sub Course to be deleted from the list.
	 */
	void removeSubject(ISubject sub);
		
	/**
	 * It gives back the course given in a classroom in a specified day in a specified hour of the chosen semester. 
	 * 
	 * @param sem Semester in which it is necessary to look for the course to be given back.
	 * @param d Day in which it is necessary to look for the course to be given back.
	 * @param room Classroom in which it is necessary to look for the course to be given back.
	 * @param hour Hour in which it is necessary to look for the course to be given back.
	 * @return The course given in the specified classroom, in the specified day and in specified semester. It is used 
	 * 			an Optional in order to avoid giving back null {@link Optional} in case there is no course in the selected hour.
	 * @throws IllegalArgumentException if sem does not correspond to {@link #FIRST_SEM} nor to {@link #SEC_SEM} or in the cases 
	 * 									that are specified in the method {@link IWeeklyTime#getSubject(Days, Classrooms, int)}.

	 */
	Optional<ISubject> getSubject(int sem, Days d, Classrooms room, int hour);
	
	/**
	 * It gives back a copy of the list of available courses.
	 * 
	 * @return Set containing all courses.
	 */
	Set<ISubject> getSubjects();
	
	/**
	 * It allows to import the list of available courses.
	 * 
	 * @param set Set containing the new list of courses.
	 */
	void setSubjects(Set<ISubject> set);
	
	/**
	 * It gives back a set containing in what classrooms is the specified teacher in a specified hour of the given day in the chosen semester.
	 * 
	 * @param teach Name of the teacher who has the lesson of which we want to know where and at what time.
	 * @param sem Semester in which looking on.
	 * @param d Day in which looking on.
	 * @param hour Hour in which looking on between different classrooms.
	 * @return Set containing classrooms or empty set in the case that the course is not in the specified hour or if teach is null.
 	 * @throws IllegalArgumentException if sem does not correspond to {@link #FIRST_SEM} nor to {@link #SEC_SEM} or in the cases 
 	 * 									that are specified in the method {@link IWeeklyTime#whereTeaching(String, Days, int)}.
	 */
	Set<Classrooms> whereTeaching(String teach, int sem, Days d, int hour);
	
	/**
	 * It gives back a set containing in what classrooms is a course in a specified hour of the day and in the semester chosen.
	 * 
	 * @param sub Course of which we want to know where it is in a specified hour.
	 * @param sem Semester in which looking for.
	 * @param d Day in which looking for.
	 * @param hour Hour in which looking for between different classroom.
	 * @return Set containing classrooms or empty set in the case that the course is not in the specified hour or if teach is null.
  	 * @throws IllegalArgumentException if sem does not correspond to {@link #FIRST_SEM} nor to {@link #SEC_SEM} or in the cases 
 	 * 									that are specified in the method {@link IWeeklyTime#wherePerforming(ISubject, Days, int)}.
	 */
	Set<Classrooms> wherePerforming(ISubject sub, int sem, Days d, int hour);
	
	/**
	 * It gives back a set with all teachers’ names.
	 * 
	 * @return Set with teachers’ names.
	 */
	Set<String> getTeachers();
	
	/**
	 * Method to create a memento, namely to create an object I n which is described the current state of the structure.
	 * 
	 * @return the Memento object.
	 */
	IMemento createMemento();
	
	/**
	 * Method to set the model according to a previous state depending on what mem contains.
	 * 
	 * @param mem Previous state to which we want to set the model.
	 * @throws IllegalArgumentException if mem or {@link IMemento#getState()} are null.
	 */
	void setMemento(IMemento mem);
	
	/**
	 * * Interface that defines memento, namely an object that contains the state in which the structure is in a specified moment.
	 * It is necessary to realize the pattern Memento.
	 * 
	 * @author Lorenzo Cottignoli
	 *
	 */
	interface IMemento {
		
		/**
		 * Method to save the state of the structure inside this object.
		 * 
		 * @param state State to be saved.
		 * @throws IllegalArgumentException if state is null. 
		 */
		void setState(Object state);
		/**
		 * It gives back the state of the structure that is saved inside this object.
		 * 
	 	 * @return Saved state.
		 */
		Object getState();
	}
}
