package controller;

/**
 * Defines all the operation for update the model and pass the information of model 
 * to the view for update the graphic.
 */
public interface GameLoop extends KeyShot, KeyMovement {

    /**
     * Start the game loop.
     */
    void start();

    /**
     * Stop the game loop.
     */
    void stop();

    /**
     * @return true if the game loop is running, false otherwise.
     */
    boolean isRunning();
}
