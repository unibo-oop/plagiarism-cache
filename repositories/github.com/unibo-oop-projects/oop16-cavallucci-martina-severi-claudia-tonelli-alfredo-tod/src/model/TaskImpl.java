package model;


/**
 * This class represents a task of the to do.
 */

public class TaskImpl implements Task {

    private static final long serialVersionUID = 7394463841452745083L;
    private final String description;
    private Boolean completed;

    /**
     * Builds a task of the to do.
     * 
     * @param description
     *            task's description
     */
    public TaskImpl(final String description) {
        this.description = description;
        this.completed = false;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Boolean isCompleted() {
        return this.completed;
    }

    @Override
    public void setCompleted(final Boolean completed) {
        this.completed = completed;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TaskImpl other = (TaskImpl) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TaskImpl [description=" + description + ", performed=" + completed + "]";
    }
}
