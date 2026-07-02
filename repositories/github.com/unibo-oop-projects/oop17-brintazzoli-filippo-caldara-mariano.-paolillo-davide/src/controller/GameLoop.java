package controller;

/**
 * Game loop interface.
 */
public interface GameLoop {

    /**
     * Method that allows to pause the loop.
     */
    void pauseLoop();

    /**
     * Method that allows to resume the loop.
     */
    void resumeLoop();

    /**
     * Method that allows to stop the loop.
     */
    void stopLoop();

}
