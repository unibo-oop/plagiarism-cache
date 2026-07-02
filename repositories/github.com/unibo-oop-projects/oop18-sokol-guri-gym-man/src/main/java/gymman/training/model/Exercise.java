package gymman.training.model;

public class Exercise implements SimpleExercise{

	private String name;
	private Category category;
	private ExerciseExecution executionMode;


	public Exercise(String nameE, Category categoryE, ExerciseExecution executionE) {
		this.name = nameE;
		this.category = categoryE;
		this.executionMode = executionE;
	}



	public String getName() {
		return name;
	}


	@Override
	public void setName(String name) {
		this.name = name;
	}


	public Category getCategory() {
		return category;
	}

	@Override
	public void setCategory(Category categoryE) {
		this.category = categoryE;
	}


	public ExerciseExecution getExecutionMode() {
		return executionMode;
	}

	@Override
	public void setExecutionMode(ExerciseExecution execution) {
		this.executionMode = execution;
	}

}
