package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Enumerator class in order to represent days in which lessons are hold at the university.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public enum Days {
	
	/**
	 * Monday.
	 */
	MONDAY("Monday"), 
	
	/**
	 * Tuesday.
	 */
	TUESDAY("Tuesday"), 
	
	/**
	 * Wednesday.
	 */
	WEDNESDEY("Wednesday"), 
	
	/**
	 * Thursday.
	 */
	THURSDAY("Thursday"), 
	
	/**
	 * Friday.
	 */
	FRIDAY("Friday");
	
	private final String name;
	
	private Days(final String s) {
		name = s;
	}
	
	/**
	 * 
	 * @return String containing the name of the day.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return Set containing values of the Array given back from the method {{@link #values()}.
	 */
	public static Set<Days> getDaysValues() {
		return new HashSet<>(Arrays.asList(Days.values()));
	}
}
