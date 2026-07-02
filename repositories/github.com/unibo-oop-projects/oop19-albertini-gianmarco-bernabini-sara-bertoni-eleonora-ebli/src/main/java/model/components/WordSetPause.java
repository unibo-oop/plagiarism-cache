package model.components;

/**
 * 
 * Useful functionalities for runnable objects that pause based on a given value.
 *
 */
public interface WordSetPause {

    /**
     * The default sleep's time.
     */
    int IN_BETWEEN_PAUSE = 2000;

    /**
     * Sets whether the game is on pause between two sets.
     * 
     * @param pauseStatus
     *      the status of the pause
     */
    void setWordSetPause(boolean pauseStatus);

    /**
     * Puts a thread to sleep for a given time.
     * 
     * @param time
     *      time of sleep
     * @throws InterruptedException
     *      if the thread is interrupted during a sleep
     */
    default void sleepForX(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Checks if the words' set has ended and if it's true, it sleeps for two seconds.
     * 
     * @param isPaused
     *      true if the words' set has ended, false if it hasn't
     */
    default void checkWordSetPause(boolean isPaused) {
        if (isPaused) {
            this.sleepForX(IN_BETWEEN_PAUSE);
        }
    }
}
