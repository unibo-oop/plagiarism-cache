package todo.view.entities;

import todo.view.entities.tasks.LoopableTaskAcceptor;
import todo.view.entities.tasks.LoopableTaskManager;

/**
 * This interface represents an entity which can accept tasks via a task
 * manager.
 *
 * @param <M> is the {@link LoopableTaskManager} interface that the entity will
 *            have
 */
public interface TaskAcceptingEntity<M extends LoopableTaskManager> extends Entity, LoopableTaskAcceptor<M> {

    /**
     * This method is called each frame when a task accepting entity has no tasks in
     * its manager.
     *
     * @param deltaTime represents the time in seconds since the last frame
     */
    void fallbackUpdate(float deltaTime);
}
