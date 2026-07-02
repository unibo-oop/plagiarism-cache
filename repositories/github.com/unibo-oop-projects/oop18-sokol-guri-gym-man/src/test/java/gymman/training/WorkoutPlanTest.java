package gymman.training;

import static org.junit.Assert.*;

import org.junit.Test;

import gymman.training.model.ExerciseExecution;
import gymman.training.model.MetricWithSetsAndRepetitions;
import gymman.training.model.MetricWithTimer;

public class WorkoutPlanTest {

	@Test
	public void testMetricWithTimerExecutionMode() {
		MetricWithTimer timerMetric = ExerciseExecution.withTimer(10);

		assertEquals(10, timerMetric.getMinutes());
		timerMetric.setMinutes(55);
		assertEquals(55, timerMetric.getMinutes());

		MetricWithSetsAndRepetitions setsAndRep = ExerciseExecution.withSetsAndRepetitions(12, 15);
	}

}
