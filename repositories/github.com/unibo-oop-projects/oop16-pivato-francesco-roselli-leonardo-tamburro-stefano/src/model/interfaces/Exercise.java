package model.interfaces;

import model.enumerations.*;

public interface Exercise {
	
	/**
	 * 
	 * return the type of exercise
	 */
	
	public ExerciseType getType();
	
	/**
	 * 
	 * return the name of the exercise
	 */
	
	public String getName();
	
	/**
	 * 
	 * return the set of the exercise
	 */
	
	public String getSet();
	
	/**
	 * 
	 * return the reps of the exercise
	 */

	public String getReps();
	
	/**
	 * 
	 * return the rest of the exercise
	 */

	public String getRest();
	
	/**
	 * 
	 * return the Weight of the exercise
	 */

	public String getWeight();
	
}
