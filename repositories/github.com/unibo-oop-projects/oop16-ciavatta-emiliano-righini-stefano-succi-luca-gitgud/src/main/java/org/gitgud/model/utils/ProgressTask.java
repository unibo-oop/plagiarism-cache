package org.gitgud.model.utils;

/**
 * General structure of an heavy task executed by git.
 */
public interface ProgressTask {

    /**
     * @param jgitMessage
     *            the jgit library message associated with the current task
     * @return the ProgressTask
     */
    ProgressTask getByJgitMessage(String jgitMessage);

    /**
     * @return the task key of the task in the application resource bundle
     */
    String getTaskKey();

    /**
     * @return the default task used when no task is found
     */
    ProgressTask getUnknownTask();

    /**
     * @return true if the task has a determinate progress
     */
    boolean hasEndTask();

}
