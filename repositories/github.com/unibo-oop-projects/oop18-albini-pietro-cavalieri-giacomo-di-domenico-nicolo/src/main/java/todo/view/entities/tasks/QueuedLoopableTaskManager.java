package todo.view.entities.tasks;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;

/**
 * This is a loopable task manager with a queued task policy. That means that
 * only one task is run each frame, i.e. the first not-yet-done task inserted.
 * When that task is done, the next one gets run.
 */
public final class QueuedLoopableTaskManager extends BaseLoopableTaskManager<Deque<LoopableTask>>
        implements SequentialLoopableTaskManager {

    /**
     * Create a new queued loopable task manager.
     */
    public QueuedLoopableTaskManager() {
        super(new LinkedList<>());
    }

    @Override
    public void tick(final float deltaTime) {
        // Get the task at the top of the queue
        final Optional<LoopableTask> toBeRun = getCurrentTask();
        if (toBeRun.isPresent()) {
            toBeRun.get().onTick(deltaTime);
            // Check if the task is done: if so, remove it
            if (toBeRun.get().isDone()) {
                toBeRun.get().onDestroy();
                super.getContainer().poll();
            }
        }
    }

    @Override
    public void switchToTask(final LoopableTask task) {
        // Ensure the task is not null
        Objects.requireNonNull(task);
        // Remove the currently running task
        abortCurrentTask();
        // Force the insertion/move of the specified task to the head
        super.getContainer().remove(task);
        super.getContainer().addFirst(task);
        task.onInit();
    }

    @Override
    public void abortCurrentTask() {
        // Remove the currently running task
        final Optional<LoopableTask> currentTask = getCurrentTask();
        if (currentTask.isPresent()) {
            currentTask.get().onDestroy();
            super.getContainer().remove(currentTask.get());
        }
    }

    private Optional<LoopableTask> getCurrentTask() {
        return Optional.ofNullable(getContainer().peek());
    }
}
