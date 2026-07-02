package model.interfaces;

import model.SubjectType;

/**
 * Interface that defines the standard operations of a cours.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public interface ISubject extends java.io.Serializable {
	
	/**
	 * 
	 * @return Name of the course.
	 */
	String getSubName();
	/**
	 * 
	 * @return Name of the teacher who gives this lesson.
	 */
	String getTeachName();

	/**
	 * 
	 * @return Course type.
	 */
	SubjectType getSubjectType();
}
