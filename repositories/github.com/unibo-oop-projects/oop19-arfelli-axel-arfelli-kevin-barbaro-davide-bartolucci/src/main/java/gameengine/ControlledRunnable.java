package gameengine;

public interface ControlledRunnable extends Runnable {

    /**
     * Check if the execution is ended.
     * @return A boolean rapresenting the state
     */
    boolean isEnded();

    /**
     * Pause the exceution.
     */
    void pause();

    /**
     * Resume the execution.
     */
    void resume();

    /**
     * Check if the execution is paused.
     * @return A boolean rapresenting the state
     */
    boolean isPaused();

    /**
     * Stops the execution.
     */
    void stop();

}
