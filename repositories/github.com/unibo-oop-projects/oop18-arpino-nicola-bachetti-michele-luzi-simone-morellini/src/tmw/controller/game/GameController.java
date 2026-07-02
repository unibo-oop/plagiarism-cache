package tmw.controller.game;

/**
 * Interface that handle the gameLoop.
 *
 */
public interface GameController extends Runnable {

    /**
     * Start the gamelopp.
     */
    void start();

    /**
     * stop the gameloop.
     */
    void stop();

    /**
     * method that permit to wait the current time to render the following frame.
     * 
     * @param current current time at the beginning of current frame
     */
    void waitForNextFrame(long current);
}
