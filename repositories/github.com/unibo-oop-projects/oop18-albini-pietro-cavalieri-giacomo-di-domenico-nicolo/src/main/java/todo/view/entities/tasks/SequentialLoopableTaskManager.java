package todo.view.entities.tasks;

/**
 * This interface represents a sequential loopable task manager. It acts the
 * same way as a {@link todo.view.entities.tasks.LoopableTaskManager}, but this
 * interface implementations must use a sequential task policy (e.g. queue).
 */
public interface SequentialLoopableTaskManager extends LoopableTaskManager {
    /**
     * Switch to the specified task, aborting the current one, causing it to execute
     * its {@link todo.view.entities.tasks.LoopableTask#onDestroy()} method first.
     * If the task does not exist, it gets added as the next to be run.
     *
     * @param task to be run
     */
    void switchToTask(LoopableTask task);

    /**
     * Abort the current task, causing it to execute its
     * {@link todo.view.entities.tasks.LoopableTask#onDestroy()} method first, then
     * switch to the next task to be run.
     */
    void abortCurrentTask();
}
