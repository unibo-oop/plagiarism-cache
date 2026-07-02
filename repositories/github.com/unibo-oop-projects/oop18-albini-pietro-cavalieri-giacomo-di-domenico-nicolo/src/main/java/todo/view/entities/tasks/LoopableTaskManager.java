package todo.view.entities.tasks;

/**
 * This interface represents a loopable task manager. Its implementations can
 * decide the task update policy (e.g. sequential or parallel) and are
 * responsible for calling correctly the various methods in the
 * {@link todo.view.entities.tasks.LoopableTask} interface. In case of a
 * sequential task policy (e.g. task queue), implement the
 * {@link todo.view.entities.tasks.SequentialLoopableTaskManager} instead.
 */
public interface LoopableTaskManager {
    /**
     * Add a new loopable task to the manager. The task's onInit() method will be
     * called.
     *
     * @param task to be added
     */
    void add(LoopableTask task);

    /**
     * Remove the specified loopable task from the manager. The task's onDestroy()
     * method will be called.
     *
     * @param task to be removed
     */
    void remove(LoopableTask task);

    /**
     * Remove all the loopable tasks currently running in the manager. The tasks'
     * onDestroy() methods will be called.
     */
    void removeAll();

    /**
     * Get how many tasks are running and/or queued for run.
     *
     * @return the number of tasks
     */
    int getTasksCount();

    /**
     * Process the tasks according to the implementing class policies. The
     * onTick(double) method will be called.
     *
     * @param deltaTime represents the time in seconds since the last frame
     */
    void tick(float deltaTime);
}
