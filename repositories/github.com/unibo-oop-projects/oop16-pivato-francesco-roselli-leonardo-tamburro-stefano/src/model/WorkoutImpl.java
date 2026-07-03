package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.enumerations.ExerciseType;
import model.enumerations.Status;
import model.interfaces.Exercise;
import model.interfaces.Workout;

public class WorkoutImpl implements Workout, Serializable {
	

	private static final long serialVersionUID = -1958918494264276705L;
	private Map<String, List<Exercise>> workTable;
	
	public WorkoutImpl() {
		this.workTable = new HashMap<>();
	}
	
	private void prepareTable(String giorno) {
		this.workTable.merge(giorno, new ArrayList<>(), (x,y) -> x);
	}
	
	@Override
	public Status setExerciseInDay(String giorno, Exercise exercise) {
		this.prepareTable(giorno);
		if (this.workTable.get(giorno).contains(exercise)) {
			return Status.EXERCISE_ALREADY_EXIST;
		}
		this.workTable.get(giorno).add(exercise);
		return Status.ALL_RIGHT;
	}
	
	@Override
	public List<Exercise> getAllExercisesByDay(String giorno) {
		return this.workTable.get(giorno);
	}
	
	@Override
	public Status deleteExercise(String giorno, Exercise exercise) {
		if (!this.workTable.get(giorno).contains(exercise)) {
			return Status.EXERCISE_NOT_FOUND;
		}
		this.workTable.get(giorno).remove(exercise);
		return Status.ALL_RIGHT;
	}
	
	@Override
	public List<Exercise> getAllExercises() {
		List<Exercise> allExercises = new ArrayList<>();
		this.workTable.entrySet().forEach(e-> allExercises.addAll(e.getValue()));
		return allExercises;
	}
	
	@Override
	public List<Exercise> getAllExerciseByType(ExerciseType type) {
		List<Exercise> allExercisesByType = getAllExercises();
		return allExercisesByType.stream()
									.filter(x-> x.getType().equals(type) ? true : false)
									.collect(Collectors.toList());
	}
	
	@Override
	public Map<String, List<Exercise>> getExerciseMap() {
		return this.workTable;
	}

}
