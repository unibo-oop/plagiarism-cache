package utils;

/**
 * Used to counts how many time a certain event occurs.
 */
public interface Counter {

    /**
     * @return The number of times the event occurred
     */
    int getValue();

    /**
     * The event occurred again.
     */
    void increment();

}
