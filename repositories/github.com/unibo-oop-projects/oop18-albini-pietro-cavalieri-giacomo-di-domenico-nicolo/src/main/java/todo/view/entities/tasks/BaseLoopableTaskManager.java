package todo.view.entities.tasks;

import java.util.Collection;
import java.util.Objects;

import todo.utils.Checks;

/**
 * This abstract class represents the base skeleton for a loopable task manager,
 * that takes a generic collection of loopable tasks. The getter/setter methods
 * are already implemented; the other methods must be implemented by the class
 * that extends this one.
 *
 * This class also offers a protected {@link #getContainer()} method that allows
 * the subclasses to get the container that has been created as a constructor
 * parameter.
 *
 * @param <T> is the {@link Collection} of {@link LoopableTask} that will hold
 *            the tasks.
 */
public abstract class BaseLoopableTaskManager<T extends Collection<LoopableTask>> implements LoopableTaskManager {

    private final T container;

    /**
     * Create a new base loopable task manager with the given task container.
     *
     * @param container is a collection of tasks
     */
    public BaseLoopableTaskManager(final T container) {
        this.container = Objects.requireNonNull(container);
    }

    @Override
    public final void add(final LoopableTask task) {
        this.container.add(Objects.requireNonNull(task));
        task.onInit();
    }

    @Override
    public final void remove(final LoopableTask task) {
        Checks.require(this.container.contains(task), IllegalStateException.class,
                "Trying to remove a task that doesn't exist in this manager");
        Objects.requireNonNull(task).onDestroy();
        this.container.remove(task);
    }

    @Override
    public final void removeAll() {
        this.container.forEach(LoopableTask::onDestroy);
        this.container.clear();
    }

    @Override
    public final int getTasksCount() {
        return this.container.size();
    }

    /**
     * Get the container used for storing the tasks.
     *
     * @return the task container
     */
    protected final T getContainer() {
        return this.container;
    }
}
