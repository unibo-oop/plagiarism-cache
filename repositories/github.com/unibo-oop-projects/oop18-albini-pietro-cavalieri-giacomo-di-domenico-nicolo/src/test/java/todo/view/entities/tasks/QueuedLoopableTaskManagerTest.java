package todo.view.entities.tasks;

import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;

import org.junit.Test;

import todo.view.entities.tasks.QueuedLoopableTaskManager;
import todo.view.entities.tasks.SequentialLoopableTaskManager;

public class QueuedLoopableTaskManagerTest extends BaseLoopableTaskManagerTest<SequentialLoopableTaskManager> {
    private static final int INITIAL_SWITCH_TASK_ID = 0;
    private static final int NEXT_SWITCH_TASK_ID = 1;
    private static final int ABORT_TASK_ID = 0;
    private static final int TASKS = 2;
    private static final int TICKS_PER_TASK = 4;

    public QueuedLoopableTaskManagerTest() {
        super(new QueuedLoopableTaskManager(), TASKS, TICKS_PER_TASK);
    }

    @Test
    public void testSwitchToTask() {
        final StringBuilder result = new StringBuilder();
        // Add a task to the manager
        super.getManager().add(super.getGeneratedTasksList().get(INITIAL_SWITCH_TASK_ID));
        appendWithID(result, INITIAL_SWITCH_TASK_ID, "I");
        // Simulate half the ticks, then switch to another task
        for (int i = 0; i < TICKS_PER_TASK / 2; i++) {
            super.getManager().tick(1 / 60f);
            appendWithID(result, INITIAL_SWITCH_TASK_ID, "T");
        }
        // Switch to another task
        appendWithID(result, INITIAL_SWITCH_TASK_ID, "D");
        super.getManager().switchToTask(super.getGeneratedTasksList().get(NEXT_SWITCH_TASK_ID));
        appendWithID(result, NEXT_SWITCH_TASK_ID, "I");
        // Simulate half the ticks
        for (int i = 0; i < TICKS_PER_TASK / 2; i++) {
            super.getManager().tick(1 / 60f);
            appendWithID(result, NEXT_SWITCH_TASK_ID, "T");
        }
        assertEquals(result.toString(), super.getOutputString());
    }

    @Test
    public void testAbortTaskWithAnotherQueuedUp() {
        final StringBuilder result = new StringBuilder();
        // Add all the tasks to the manager
        super.addAllTasksToManager();
        // Initialize all
        IntStream.range(0, super.getGeneratedTasksList().size()).forEach(i -> appendWithID(result, i, "I"));
        // Simulate half the ticks, then abort the current task
        for (int i = 0; i < TICKS_PER_TASK / 2; i++) {
            super.getManager().tick(1 / 60f);
            appendWithID(result, 0, "T");
        }
        super.getManager().abortCurrentTask();
        appendWithID(result, 0, "D");
        // Simulate half the ticks for the next task
        for (int i = 0; i < TICKS_PER_TASK / 2; i++) {
            super.getManager().tick(1 / 60f);
            appendWithID(result, 1, "T");
        }
        assertEquals(result.toString(), super.getOutputString());
        assertEquals(super.getGeneratedTasksList().size() - 1, super.getManager().getTasksCount());
    }

    @Test
    public void testAbortTaskWithEmptyQueue() {
        final StringBuilder result = new StringBuilder();
        // Add all the tasks to the manager
        super.getManager().add(super.getGeneratedTasksList().get(ABORT_TASK_ID));
        // Initialize
        appendWithID(result, ABORT_TASK_ID, "I");
        // Simulate half the ticks, then abort the current task
        for (int i = 0; i < TICKS_PER_TASK / 2; i++) {
            super.getManager().tick(1 / 60f);
            appendWithID(result, ABORT_TASK_ID, "T");
        }
        super.getManager().abortCurrentTask();
        appendWithID(result, ABORT_TASK_ID, "D");
        assertEquals(result.toString(), super.getOutputString());
        assertEquals(0, super.getManager().getTasksCount());
    }

    @Override
    public String getExpectedMultipleTasksOutput(final int tasksCount, final int loops) {
        final StringBuilder result = new StringBuilder();
        // Initialize all
        IntStream.range(0, tasksCount).forEach(i -> appendWithID(result, i, "I"));
        for (int i = 0; i < tasksCount; i++) {
            // Tick
            for (int j = 0; j < loops; j++) {
                appendWithID(result, i, "T");
            }
            // Destroy
            appendWithID(result, i, "D");
        }
        return result.toString();
    }

    private void appendWithID(final StringBuilder sb, final int id, final String text) {
        sb.append(String.format("%d:%s,", id, text));
    }
}
