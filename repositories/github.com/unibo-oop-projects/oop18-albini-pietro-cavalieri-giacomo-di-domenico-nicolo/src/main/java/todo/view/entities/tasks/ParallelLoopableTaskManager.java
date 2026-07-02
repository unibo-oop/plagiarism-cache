package todo.view.entities.tasks;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This is a loopable task manager with a parallel task policy. Every task that
 * is in this manager will be run in sequence with all the others until they are
 * done.
 */
public final class ParallelLoopableTaskManager extends BaseLoopableTaskManager<List<LoopableTask>> {
    /**
     * Create a new parallel loopable task manager.
     */
    public ParallelLoopableTaskManager() {
        super(new LinkedList<>());
    }

    @Override
    public void tick(final float deltaTime) {
        // Using iterator so we can remove tasks on the go
        final Iterator<LoopableTask> iterator = getContainer().iterator();
        while (iterator.hasNext()) {
            final LoopableTask task = iterator.next();
            // Run the task
            task.onTick(deltaTime);
            if (task.isDone()) {
                // Task has done, remove it
                task.onDestroy();
                iterator.remove();
            }
        }
    }
}
