package casim.utils;

/**
 * Models a task that return a void and may throw an exception.
 * 
 * @param <E> the type of the exception.
 */
public interface Task<E extends Exception> {
    /**
     * The function that the task executes.
     * @throws E the type of the exception that may be thrown.
     */
    void execute() throws E;
}
