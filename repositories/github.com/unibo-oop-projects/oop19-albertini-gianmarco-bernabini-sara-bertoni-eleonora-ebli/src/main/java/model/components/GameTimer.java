package model.components;

/**
 * 
 * Useful functionalities to build a timer.
 *
 */
public interface GameTimer extends Comparable<GameTimer>, WordSetPause, Runnable {

    /**
     * Returns the current hour.
     * 
     * @return the current value
     */
    int getHours();

    /**
     * Returns the current minute.
     * 
     * @return the current value
     */
    int getMinutes();

    /**
     * Returns the current second.
     * 
     * @return the current value
     */
    int getSeconds();

    /**
     * Returns the current tenth of second.
     * 
     * @return the current value
     */
    int getTenths();

    /**
     * Resets the timer.
     */
    void reset();
}
