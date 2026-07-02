package model;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Enumerator class that represents all the different types concerning courses. Every typology is associated 
 * to a specific colour in order to use it in the View to get better the readability for the user.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public enum SubjectType {
	/**
	 * Exams of the first year of the bachelor’s degree.
	 */
	LT1("1° anno laurea triennale", Color.CYAN),
	
	/**
	 * Exams of the second year of the bachelor’s degree.
	 */
	LT2("2° anno laurea triennale", Color.YELLOW),
	
	/**
	 * Exams of the third year of the bachelor’s degree.
	 */
	LT3("3° anno laurea triennale", Color.LIGHT_GRAY),
	
	/**
	 * Exams of the first year of the master’s degree.
	 */
	LM1("1° anno laurea magistrale", Color.GREEN),
	
	/**
	 * Exams of the second year of the master’s degree.
	 */
	LM2("2° anno laurea magistrale", Color.MAGENTA),
	
	/**
	  * Optional exams of the bachelor’s degree.
	 */
	LTO("Opzionali laurea triennale", Color.ORANGE),
	
	/**
	 * Optional exams of the master’s degree.
	 */
	LMO("Opzionali laurea magistrale", Color.PINK),
	
	/**
	 * Exams of the third year of the bachelor’s degree for all students that have chosen 
	 * the specialization in science.
	 */
	PST("3° anno percorso scienze laurea triennale", Color.RED);
	
	private final String descr;
	private final Color color;
	
	private SubjectType(final String s, final Color c) {
		descr = s;
		color = c;
	}
	
	/**
	 * 
	 * @return Complete description of the type.
	 */
	public String getDescription() {
		return descr;
	}
	
	/**
	 * 
	 * @return The color associated to every type.
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * 
	 * @return Un Set che contiene i valori dell'Array restituito dal metodo {{@link #values()}.
	 */
	public static Set<SubjectType> getSubjectTypeValues() {
		return new HashSet<>(Arrays.asList(SubjectType.values()));
	}
}
