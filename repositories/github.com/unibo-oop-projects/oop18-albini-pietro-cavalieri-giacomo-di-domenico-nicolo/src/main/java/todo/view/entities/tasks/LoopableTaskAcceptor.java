package todo.view.entities.tasks;

/**
 * This interface, if implemented, allows an entity to have a
 * {@link todo.view.entities.tasks.LoopableTaskManager}.
 *
 * @param <M> the type manager to be used for the entity
 */
@FunctionalInterface
public interface LoopableTaskAcceptor<M extends LoopableTaskManager> {
    /**
     * Get an instance of a loopable task manager.
     *
     * @return the loopable task manager
     */
    M getLoopableTaskManager();
}
