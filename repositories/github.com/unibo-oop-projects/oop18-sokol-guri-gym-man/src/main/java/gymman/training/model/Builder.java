package gymman.training.model;

public interface Builder {

	Exercise initSets();
	Exercise initPeso();
	Exercise initRepetitions();
	Exercise initName();

	SimpleExercise getExercise();
}
