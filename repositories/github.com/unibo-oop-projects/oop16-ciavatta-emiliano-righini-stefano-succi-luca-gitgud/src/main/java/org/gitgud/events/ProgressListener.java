package org.gitgud.events;

/**
 * Used to update progress of heavy tasks.
 */
public interface ProgressListener {

    /**
     * @return true to cancel heavy task
     */
    boolean isCancelled();

    /**
     * @param progress
     *            the progress percentage
     */
    void onProgressUpdated(double progress);

    /**
     * @param taskKey
     *            the task key
     */
    void onTaskChanged(String taskKey);

}
