package org.gitgud.model.utils;

import org.eclipse.jgit.lib.ProgressMonitor;
import org.gitgud.events.ProgressListener;

/**
 * Adapt ProgressMonitor into ProgressListener.
 */
public class ProgressMonitorAdapter implements ProgressMonitor {

    private static final double PROGRESS_INCREMENT = 0.01;

    private final ProgressListener progressListener;
    private final ProgressTask defaultProgressTask;

    private ProgressTask currentTask;
    private int currentTotalWorkDone;
    private int currentTotalWork;
    private double currentProgress;

    /**
     * @param progressListener
     *            the application progress listener
     * @param defaultProgressTask
     *            the default task associated with the heavy operation
     */
    public ProgressMonitorAdapter(final ProgressListener progressListener, final ProgressTask defaultProgressTask) {
        this.progressListener = progressListener;
        this.defaultProgressTask = defaultProgressTask;
        currentTask = defaultProgressTask;
    }

    @Override
    public void beginTask(final String title, final int totalWork) {
        currentTask = defaultProgressTask.getByJgitMessage(title);
        progressListener.onTaskChanged(currentTask.getTaskKey());
        currentTotalWorkDone = 0;
        currentTotalWork = totalWork;
        currentProgress = 0.0;
    }

    @Override
    public void endTask() {
        currentTask = defaultProgressTask;
    }

    @Override
    public boolean isCancelled() {
        return progressListener.isCancelled();
    }

    @Override
    public void start(final int totalTasks) {
    }

    @Override
    public void update(final int completed) {
        if (currentTask.hasEndTask()) {
            currentTotalWorkDone += completed;
            final double progress = (double) currentTotalWorkDone / currentTotalWork;
            if (progress > currentProgress + PROGRESS_INCREMENT) {
                currentProgress = progress;
                progressListener.onProgressUpdated(progress);
            }
        }
    }

}
