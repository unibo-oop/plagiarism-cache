package todo.view.entities.tasks;

import java.util.stream.IntStream;

import todo.view.entities.tasks.LoopableTaskManager;
import todo.view.entities.tasks.ParallelLoopableTaskManager;

public class ParallelLoopableTaskManagerTest extends BaseLoopableTaskManagerTest<LoopableTaskManager> {
    private static final int TASKS = 2;
    private static final int TICKS_PER_TASK = 4;

    public ParallelLoopableTaskManagerTest() {
        super(new ParallelLoopableTaskManager(), TASKS, TICKS_PER_TASK);
    }

    @Override
    public String getExpectedMultipleTasksOutput(final int tasksCount, final int loops) {
        final StringBuilder result = new StringBuilder();
        // Initialize all
        IntStream.range(0, tasksCount).forEach(i -> appendWithID(result, i, "I"));
        // Tick all
        for (int i = 0; i < loops; i++) {
            for (int j = 0; j < tasksCount; j++) {
                appendWithID(result, j, "T");
                if (i == loops - 1) {
                    // Destroy if it's the last tick
                    appendWithID(result, j, "D");
                }
            }
        }
        return result.toString();
    }

    private void appendWithID(final StringBuilder sb, final int id, final String text) {
        sb.append(String.format("%d:%s,", id, text));
    }
}
