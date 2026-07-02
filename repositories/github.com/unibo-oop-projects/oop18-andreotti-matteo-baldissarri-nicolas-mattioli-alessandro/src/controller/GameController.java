package controller;

/**
 * 
 * Interface to control InGame threads.
 *
 */
public interface GameController {

    /**
     * Method to start/resume game.
     */
    void start();

    /**
     * Method to pause game.
     */
    void pause();

    /**
     * Method to stop game.
     */
    void stop();
}
