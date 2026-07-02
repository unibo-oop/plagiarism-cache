package clashclass.battle.timer;

/**
 * A timer that can be started, stopped, and checked for completion.
 * This timer operates asynchronously in a separate thread.
 */
public interface Timer {

    /**
     * Starts the timer asynchronously in a separate thread.
     * The timer will increment every second until it reaches the time limit.
     * If the Timer is already running, it does nothing.
     */
    void start();

    /**
     * Stops the timer if it's currently running.
     * If the timer is running in a separate thread, this method interrupts the thread.
     */
    void stop();

    /**
     * Called when the timer reaches the time limit.
     * Stops the timer and notifies that the time is up.
     */
    void onFinished();

    /**
     * Returns the current elapsed time in seconds.
     *
     * @return elapsed time in seconds.
     */
    long getElapsedTime();
}
