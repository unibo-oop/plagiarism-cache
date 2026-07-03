package breakout.controller;

/**
 * Define the behaviour of a gameloop.
 *
 */
public interface GameLoop {

    /**
     * Start the loop.
     */
    void start();

    /**
     * Resume the loop.
     */
    void unPause();

    /**
     * Stop the loop.
     */
    void pause();

    /**
     * @return true if the loop is running. false otherwise.
     */
    boolean isPaused();

}
