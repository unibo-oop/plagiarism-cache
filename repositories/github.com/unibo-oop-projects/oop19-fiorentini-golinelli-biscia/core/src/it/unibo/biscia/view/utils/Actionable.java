package it.unibo.biscia.view.utils;

/**
 * Performing an Action implemented with {@link Runnable}.
 * 
 * @see Runnable
 *
 */
public interface Actionable {

    /**
     * The current action set.
     * 
     * @return The current action set.
     */
    Runnable getAction();

    /**
     * Execute the current action.
     */
    void exec();

    /**
     * Set the current action to {@code action}.
     * 
     * @param action the new action.
     */
    void setAction(Runnable action);
}
