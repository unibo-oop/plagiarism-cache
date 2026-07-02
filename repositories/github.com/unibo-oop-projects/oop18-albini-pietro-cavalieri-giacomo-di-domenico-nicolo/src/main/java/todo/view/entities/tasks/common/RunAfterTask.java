package todo.view.entities.tasks.common;

import java.util.Objects;

import todo.view.entities.tasks.LoopableTask;

public class RunAfterTask implements LoopableTask {
    private final Runnable runnable;
    private final LoopableTask task;

    public RunAfterTask(final LoopableTask task, final Runnable runnable) {
        this.task = Objects.requireNonNull(task);
        this.runnable = Objects.requireNonNull(runnable);
    }

    @Override
    public void onInit() {
        this.task.onInit();
    }

    @Override
    public void onTick(final float deltaTime) {
        this.task.onTick(deltaTime);
        if (this.task.isDone()) {
            this.runnable.run();
        }
    }

    @Override
    public void onDestroy() {
        this.task.onDestroy();
    }

    @Override
    public boolean isDone() {
        return this.task.isDone();
    }
}
