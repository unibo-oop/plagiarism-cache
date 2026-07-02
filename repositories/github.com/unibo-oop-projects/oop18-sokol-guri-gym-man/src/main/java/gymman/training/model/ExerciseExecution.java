package gymman.training.model;

public final class ExerciseExecution {

//	private final static String METRIC_WITH_TIMER = "metric_with_timer";
//	private final static String METRIC_WITH_SETS_AND_REPETITIONS = "metric_with_sets_and_repetitions";

	private final static double MAX_MINUTES_ALLOWED = 240.0;
	private final static double MIN_MINUTES_ALLOWED = 0.0;
	private final static int MIN_SETS = 0;
	private final static int MAX_SETS = 50;
	private final static int MAX_REPETITIONS = 50;
	private final static int MIN_REPETITIONS = 0;


	public static MetricWithTimer withTimer(double minutes) {
		if(minutes < MIN_MINUTES_ALLOWED || minutes > MAX_MINUTES_ALLOWED) {
			throw new IllegalArgumentException("Illegal minute value");
		}else {
			return new MetricWithTimer(minutes);
		}
	}

	public static MetricWithSetsAndRepetitions withSetsAndRepetitions(int nrSets, int nrRepetitions) {

		if(nrSets < MIN_SETS || nrSets > MAX_SETS || nrRepetitions < MIN_REPETITIONS || nrRepetitions > MAX_REPETITIONS) {
			throw new IllegalArgumentException("Illegal sets or repetitions value");
		}else {
			return new MetricWithSetsAndRepetitions(nrSets, nrRepetitions);
		}
	}

}
