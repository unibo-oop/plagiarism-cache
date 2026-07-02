package todo.view.entities;

import todo.view.entities.tasks.LoopableTaskManager;

/**
 * This abstract class represents an entity which can accept tasks via a task
 * manager.
 *
 * @param <M> is the {@link LoopableTaskManager} interface that the entity will
 *            have
 */
public abstract class BaseTaskAcceptingEntity<M extends LoopableTaskManager> extends BaseEntity
        implements TaskAcceptingEntity<M> {

    protected BaseTaskAcceptingEntity() {
        super();
    }

    @Override
    public void update(final float deltaTime) {
        final LoopableTaskManager manager = getLoopableTaskManager();
        if (manager.getTasksCount() == 0) {
            fallbackUpdate(deltaTime);
        } else {
            manager.tick(deltaTime);
        }
    }

    @Override
    public void fallbackUpdate(final float deltaTime) {
        // Do nothing
    }
}
