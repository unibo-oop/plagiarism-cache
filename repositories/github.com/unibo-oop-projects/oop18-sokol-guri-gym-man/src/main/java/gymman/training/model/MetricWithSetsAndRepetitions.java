package gymman.training.model;

public class MetricWithSetsAndRepetitions implements Metric{

	private int sets;
	private int repetitions;

	MetricWithSetsAndRepetitions(int nrSets, int nrRepetitions){
		this.sets = nrSets;
		this.repetitions = nrRepetitions;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public int getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}

}
