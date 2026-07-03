package model.interfaces;

import java.util.List;
import java.util.Map;

import model.enumerations.ExerciseType;
import model.enumerations.Status;

public interface Workout {

	/**
	 * Add new exercise in a day
	 * @param giorno
	 * @param exercise
	 */

	public Status setExerciseInDay(String giorno, Exercise exercise);

	/**
	 * @param giorno
	 * @return List of all the exercises in a day
	 */

	public List<Exercise> getAllExercisesByDay(String giorno);

	/**
	 * Delete an Exercise in a day
	 * @param giorno
	 * @param exercise
	 */

	public Status deleteExercise(String giorno, Exercise exercise);

	/**
	 * @return List of All exercises
	 */

	public List<Exercise> getAllExercises();

	/**
	 * @param type of the exercises to be filter
	 * @return a list of All exercise of a type
	 */

	public List<Exercise> getAllExerciseByType(ExerciseType type);
	
	/**
	 * @return Map of List<Exercise> relative to one day
	 */
	
	public Map<String, List<Exercise>> getExerciseMap();



}
