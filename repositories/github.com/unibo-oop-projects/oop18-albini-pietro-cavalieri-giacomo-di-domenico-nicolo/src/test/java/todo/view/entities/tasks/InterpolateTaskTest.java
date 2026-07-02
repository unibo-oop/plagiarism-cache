package todo.view.entities.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

import todo.view.entities.tasks.LoopableTaskManager;
import todo.view.entities.tasks.ParallelLoopableTaskManager;
import todo.view.entities.tasks.common.FloatInterpolateTask;
import todo.view.entities.tasks.common.Vector2InterpolateTask;

public class InterpolateTaskTest {

    private LoopableTaskManager manager;

    @Before
    public void prepareManager() {
        this.manager = new ParallelLoopableTaskManager();
    }

    @Test
    public void testFloatInterpolateTask() {
        final List<Float> interpolatedValues = new LinkedList<>();
        this.manager.add(new FloatInterpolateTask(1, 10, f -> interpolatedValues.add(f), 1f, Interpolation.linear));

        // Simulate one second, where every frame update takes 1/10th of a second
        while (this.manager.getTasksCount() > 0) {
            this.manager.tick(1 / 10f);
        }

        final List<Float> expected = Arrays.asList(1.9f, 2.8f, 3.7f, 4.6f, 5.5f, 6.4f, 7.3f, 8.2f, 9.1f, 10.0f);
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), interpolatedValues.get(i), 0.001f);
        }
    }

    @Test
    public void testVector2InterpolateTask() {
        final List<Vector2> interpolatedValues = new LinkedList<>();
        this.manager.add(new Vector2InterpolateTask(new Vector2(1f, 1f), new Vector2(10f, 5f),
                v -> interpolatedValues.add(v), 1f, Interpolation.linear));

        // Simulate one second, where every frame takes 1/5th of a second
        while (this.manager.getTasksCount() > 0) {
            this.manager.tick(1 / 5f);
        }

        final List<Vector2> expected = Arrays.asList(new Vector2(2.8f, 1.8f), new Vector2(4.6f, 2.6f),
                new Vector2(6.4f, 3.4f), new Vector2(8.2f, 4.2f), new Vector2(10f, 5f));

        for (int i = 0; i < expected.size(); i++) {
            assertTrue(expected.get(i).epsilonEquals(interpolatedValues.get(i), 0.001f));
        }
    }
}
