package model;

import model.interfaces.ISubject;

/**
 * Implementation of the interface {@link ISubject}.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class Subject implements ISubject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4757721650038270835L;
	
	private final String subName;
	private final String teachName;  
	private final SubjectType subType;   
	
	/**
	 * It creates a course with passed parameters.
	 * 
	 * @param sub Name of the course.
	 * @param teach Name of the teacher that holds the course.
	 * @param type Type of the course.
	 * @throws IllegalArgumentException if one or more than one parameters are null.
	 */
	public Subject(final String sub, final String teach, final SubjectType type) {
		if (sub == null || teach == null || type == null) {
			throw new IllegalArgumentException("The values can't be null!");
		}
		subName = sub;
		teachName = teach;
		subType = type;
	}
	
	@Override
	public String getSubName() {
		return subName;
	}
	
	@Override
	public String getTeachName() {
		return teachName;
	}
	
	@Override
	public SubjectType getSubjectType() {
		return subType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subName == null) ? 0 : subName.hashCode());
		result = prime * result + ((subType == null) ? 0 : subType.hashCode());
		result = prime * result
				+ ((teachName == null) ? 0 : teachName.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Subject other = (Subject) obj;
		if (subName == null) {
			if (other.subName != null) {
				return false;
			}
		} else if (!subName.equals(other.subName)) {
			return false;
		}
		if (subType != other.subType) {
			return false;
		}
		if (teachName == null) {
			if (other.teachName != null) {
				return false;
			}
		} else if (!teachName.equals(other.teachName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return subName + " (" + subType + ") \n" + teachName;
	}
	
	
}
