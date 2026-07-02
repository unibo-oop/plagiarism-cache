package todo.view.entities.tasks;

/**
 * This interface is the base skeleton for a loopable task, which is a piece of
 * code that runs every frame until it's done.
 */
public interface LoopableTask {
    /**
     * This method gets called when the task is added to the task manager.
     */
    void onInit();

    /**
     * This method gets called every frame update.
     *
     * @param deltaTime represents the time in seconds since the last frame
     */
    void onTick(float deltaTime);

    /**
     * This method gets called when the task ends or is removed from the
     * {@link todo.view.entities.tasks.LoopableTaskManager}.
     */
    void onDestroy();

    /**
     * This method gets called right before the onTick method by the manager.
     *
     * @return true if the task has ended (the manager will then remove it),
     *         otherwise false
     */
    boolean isDone();
}
