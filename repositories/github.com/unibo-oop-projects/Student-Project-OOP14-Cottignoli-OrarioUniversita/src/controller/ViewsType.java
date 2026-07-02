package controller;

/**
 * * Enumerator class for all type of view of the model.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public enum ViewsType {

	/**
	 * Variable for complete view of the academic timetable.
	 */
	TOT("Total"),
	
	/**
	 * Variable for view of the academic timetable depending on the teacher.
	 */
	TEACH("Teacher"),
	
	/**
	 * Variable for view of the academic timetable depending on the subject.
	 */
	SUB("Subject"),
	
	/**
	 * Variable for view of the academic timetable depending on the classroom.
	 */
	ROOM("Classroom"),
	
	/**
	 * Variable for view of the academic timetable depending on the day.
	 */
	DAY("Day"),
	/**
	 * Variable for view of the academic timetable according to the type of subject.
	 */
	SUB_TYPE("Subject Type");
	
	private final String description;
	
	private ViewsType(final String desc) {
		description = desc;
	}
	
	/**
	 * @return The complete description of the type of view.
	 */
	public String getDescription() {
		return description;
	}
}
