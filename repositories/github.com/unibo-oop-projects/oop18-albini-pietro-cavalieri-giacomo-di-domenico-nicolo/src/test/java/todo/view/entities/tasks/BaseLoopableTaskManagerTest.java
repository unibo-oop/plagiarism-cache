package todo.view.entities.tasks;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

public abstract class BaseLoopableTaskManagerTest<T extends LoopableTaskManager> {
    private final T manager;
    private final int tasksCount;
    private final int loops;
    private StringBuilder resultBuilder;
    private List<LoopableTask> tasks;

    public BaseLoopableTaskManagerTest(final T manager, final int tasksCount, final int loops) {
        this.manager = Objects.requireNonNull(manager);
        if (tasksCount <= 0 || loops <= 0) {
            throw new IllegalArgumentException("Tasks count and loops must be positive");
        }
        this.tasksCount = tasksCount;
        this.loops = loops;
    }

    @Before
    public void setUp() {
        this.resultBuilder = new StringBuilder();
        this.tasks = IntStream.range(0, this.tasksCount)
                              .mapToObj(i -> makeStringBuilderTask(i, this.loops))
                              .collect(Collectors.toList());
    }

    @Test
    public void testInsertion() {
        addAllTasksToManager();
        // The manager must have all of them
        assertEquals(this.tasks.size(), this.manager.getTasksCount());
    }

    @Test
    public void testDeletion() {
        addAllTasksToManager();
        // Remove just one
        this.manager.remove(this.tasks.get(0));
        assertEquals(this.tasks.size() - 1, this.manager.getTasksCount());
    }

    @Test
    public void testSingleTask() {
        // Run the first task until it's over
        this.manager.add(this.tasks.get(0));
        while (this.manager.getTasksCount() > 0) {
            this.manager.tick(1 / 60f);
        }
        // The output produced by the tasks must match the one produced by the template
        // method
        assertEquals(getExpectedSingleTaskOutput(0, this.loops, true), this.resultBuilder.toString());
        // The manager must have no tasks
        assertEquals(0, this.manager.getTasksCount());
    }

    @Test
    public void testPartialSingleTask() {
        this.manager.add(this.tasks.get(0));
        // Simulate half the ticks
        IntStream.range(0, this.loops / 2).forEach(i -> this.manager.tick(1 / 60f));
        // The output produced by the tasks must match the one produced by the template
        // method with no destruction of the task
        assertEquals(getExpectedSingleTaskOutput(0, this.loops / 2, false), this.resultBuilder.toString());
        // The manager must have the task still in its container
        assertEquals(1, this.manager.getTasksCount());
    }

    @Test
    public void testMultipleTasks() {
        addAllTasksToManager();
        // Run them until they are done
        while (this.manager.getTasksCount() > 0) {
            this.manager.tick(1 / 60f);
        }
        assertEquals(getExpectedMultipleTasksOutput(this.tasksCount, this.loops), this.resultBuilder.toString());
    }

    /**
     * Get the expected output for a single task execution. The syntax for the
     * events is: ID:Action, where ID is the ID of the task (0 to the number
     * specified in the constructor) and Action is I
     * ({@link todo.view.entities.tasks.LoopableTask#onInit()} called), T
     * ({@link todo.view.entities.tasks.LoopableTask#onTick()} called), D
     * ({@link todo.view.entities.tasks.LoopableTask#onDestroy()} called).
     *
     * @param id of the task
     * @param loops is the number of ticks the task will make before exiting
     * @param isDone is true if the task gets terminated normally
     * @return the expected string
     */
    public String getExpectedSingleTaskOutput(final int id, final int loops, final boolean isDone) {
        final StringBuilder result = new StringBuilder();
        // Initialize
        appendWithID(result, id, "I");
        // Tick
        IntStream.range(0, loops).forEach(i -> appendWithID(result, id, "T"));
        // Destroy
        if (isDone) {
            appendWithID(result, id, "D");
        }
        return result.toString();
    }

    /**
     * Get the expected output for multiple task executions. The syntax for the
     * events is: ID:Action, where ID is the ID of the task (0 to the number
     * specified in the constructor) and Action is I
     * ({@link todo.view.entities.tasks.LoopableTask#onInit()} called), T
     * ({@link todo.view.entities.tasks.LoopableTask#onTick()} called), D
     * ({@link todo.view.entities.tasks.LoopableTask#onDestroy()} called).
     *
     * @param tasksCount is the number of tasks being tested. The IDs of the tasks
     *            are in the range 0..tasksCount, and there are no skipped tests.
     * @param loops is the number of ticks the task will make before exiting
     * @return the expected string
     */
    public abstract String getExpectedMultipleTasksOutput(int tasksCount, int loops);

    protected T getManager() {
        return this.manager;
    }

    protected String getOutputString() {
        return this.resultBuilder.toString();
    }

    protected List<LoopableTask> getGeneratedTasksList() {
        return Collections.unmodifiableList(this.tasks);
    }

    protected void addAllTasksToManager() {
        this.tasks.forEach(this.manager::add);
    }

    private LoopableTask makeStringBuilderTask(final int id, final int loops) {
        return new LoopableTask() {
            private final int taskID = id;
            private int count;

            @Override
            public void onInit() {
                appendWithID(BaseLoopableTaskManagerTest.this.resultBuilder, this.taskID, "I");
            }

            @Override
            public void onTick(final float deltaTime) {
                appendWithID(BaseLoopableTaskManagerTest.this.resultBuilder, this.taskID, "T");
                this.count++;
            }

            @Override
            public void onDestroy() {
                appendWithID(BaseLoopableTaskManagerTest.this.resultBuilder, this.taskID, "D");
            }

            @Override
            public boolean isDone() {
                return this.count >= loops;
            }
        };
    }

    private void appendWithID(final StringBuilder sb, final int id, final String text) {
        sb.append(String.format("%d:%s,", id, text));
    }
}
