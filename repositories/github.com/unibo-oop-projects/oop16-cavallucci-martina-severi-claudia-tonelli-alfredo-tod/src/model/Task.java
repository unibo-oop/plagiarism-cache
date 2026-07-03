package model;

import java.io.Serializable;

/**
 * This class represents a task of the to do.
 */

public interface Task extends Serializable {

    /**
     * Gets the description of the task.
     * 
     * @return task's description
     */
    String getDescription();

    /**
     * Gets the progress of the task.
     * 
     * @return task's progress
     */
    Boolean isCompleted();

    /**
     * Sets the progress of the task.
     * 
     * @param completed
     *            true if the task is completed, false otherwise
     */
    void setCompleted(Boolean completed);
}
