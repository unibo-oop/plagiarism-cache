package it.unibo.oop.manpac.utils;

/**
 * Interface for a counter.
 */
public interface Counter {

    /**
     * Getter for the actual number in the counter.
     * @return the actual number in the counter
     */
    int getRemaining();

    /**
     * Getter for the starting number of this counter.
     * @return The starting number of this counter
     */
    int getTotal();

    /**
     * Decrease the number in the Counter by 1.
     * @return true if the number in the counter is above 0
     */
    boolean decreaseCount();

    /**
     * Increase the number in the Counter by 1.
     */
    void increaseCount();

    /**
     * Set the remaining number to its max.
     */
    void resetCount();

}
