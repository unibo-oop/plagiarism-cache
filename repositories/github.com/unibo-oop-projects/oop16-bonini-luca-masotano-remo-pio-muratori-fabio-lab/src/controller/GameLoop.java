package controller;

/**
 * Represents the game loop which requests the model updates and passes the
 * wrapped model's objects to the view in order to be rendered.
 */
public interface GameLoop {

    /**
     * Starts the game loop.
     */
    void start();

    /**
     * Stops the game loop.
     */
    void stop();

    /**
     * Check if the GameLoop is running.
     * 
     * @return if the GameLoop is running returns true otherwise false
     */
    boolean isRunning();

}
