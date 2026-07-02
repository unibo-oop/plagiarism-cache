package bubbleshooter.controller.engine;

/**
 * Interface of the {@link GameLoop} of the Game.
 * It's used to render the {@link view} and update the {@link Model}.
 */
public interface GameLoop {

    /**
     * The method called by {@link Controller} to start the Engine.
     */
    void startLoop();

    /**
     * Method to stop the {@link GameLoop}.
     */
    void stopLoop();

    /**
     * Method to pause the {@link GameLoop}.
     */
    void pauseLoop();

    /**
     * Method to resume the {@link GameLoop} from the pause state.
     */
    void resumeLoop();

    /**
     * @return if the {@link GameLoop} is paused or not.
     */
    boolean isPaused();

    /**
     * @return if the {@link GameLoop} is running or not.
     */
    boolean isStopped();
}
