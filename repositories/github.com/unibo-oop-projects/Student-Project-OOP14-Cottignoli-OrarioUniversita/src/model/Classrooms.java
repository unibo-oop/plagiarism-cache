package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Enumerator class used to represent available classroom inside the faculty of informatics science and 
 * technology in Cesena.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public enum Classrooms {
	
	/**
	 * Aula Magna.
	 */
	MAGNA("Aula magna"),
	
	/**
	 * Classroom A.
	 */
	A("Aula A"),
	
	/**
	 * Classroom B.
	 */
	B("Aula B"),
	
	/**
	 * Classroom C.
	 */
	C("Aula C"),
	
	/**
	 * Classroom D.
	 */
	D("Aula D"),
	
	/**
	 * Classroom E.
	 */
	E("Aula E"),
	
	/**
	 * Vela laboratory.
	 */
	VELA("Laboratorio vela"),
	
	/**
	 * Laboratory 2.
	 */
	LAB2("Laboratorio 2"),
	
	/**
	 * Laboratory 3.
	 */
	LAB3("Laboratorio 3"),
	
	/**
	 * Classroom G ground floor.
	 */
	GPT("Aula G PT"),
	
	/**
	 * Classroom G first floor.
	 */
	GP1("Aula G P1");
	
	private final String name;
	
	private Classrooms(final String s) {
		name = s;
	}
	
	/**
	 * 
	 * @return The complete name of the classroom.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return A Set that contain values of the Array given back from the method {{@link #values()}.
	 */
	public static Set<Classrooms> getClassroomsValues() {
		return new HashSet<>(Arrays.asList(Classrooms.values()));
	}
}
