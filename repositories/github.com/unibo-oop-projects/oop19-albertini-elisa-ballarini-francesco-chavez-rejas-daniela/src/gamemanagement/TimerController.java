package gamemanagement;

/**
 * This class is the controller of the play timing. It defines the mechanics of
 * the game in relationship with the time. It creates a Swing Timer to manage
 * the flow of action and has the main methods to control it.
 */
public interface TimerController {

    /**
     * Initializes the timer and starts it.
     */
    void start();

    /**
     * Stops the timer.
     */
    void stop();

    /**
     * Restarts the timer.
     */
    void restart();

    /**
     * Sets the delay of the timer.
     * 
     * @param newSpeed represents the new delay of the timer.
     */
    void setSpeed(int newSpeed);
}
