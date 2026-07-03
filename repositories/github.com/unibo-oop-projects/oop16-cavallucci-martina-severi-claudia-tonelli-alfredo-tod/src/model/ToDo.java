package model;

import java.io.Serializable;
import java.util.List;

/**
 * This class represent the sser's to do.
 */

public interface ToDo extends Serializable {
    /**
     * Insert a task in to do.
     * 
     * @param task
     *            an instance of a task of the to do
     * @throws IllegalArgumentException
     *             if the task is already saved in to do
     */
    void addTask(Task task) throws IllegalArgumentException;

    /**
     * Delete a task from the to do.
     * 
     * @param task
     *            an instance of the task that should be deleted
     * @throws IllegalArgumentException
     *             if task not registered in to do
     */
    void removeTask(Task task) throws IllegalArgumentException;

    /**
     * Sets a task completed.
     * 
     * @param index
     *            index of the task in to do list
     * @throws IllegalArgumentException
     *             if there are problems in setting to set task completed
     */
    void setTaskComplete(int index) throws IllegalArgumentException;

    /**
     * Sets a Task not completed.
     * 
     * @param index
     *            index of the task in to do list
     * @throws IllegalArgumentException
     *             if there are problems in setting to set task not completed
     */
    void setTaskIncomplete(int index) throws IllegalArgumentException;

    /**
     * Gets a ordered list of all tasks of the to do.
     * 
     * @return List of tasks
     */
    List<Task> getTodo();

}
