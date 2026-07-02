package speed;

/**
 * This interface defines an object of type "Speed" meant to manage the quantity
 * of milliseconds the piece must wait before drop down. In the case of "instant
 * positioning" the object has a different implementation: it uses
 * Movement.dropDown() to move the piece until it can. This implementation
 * eliminates the lag that would be caused by a timer delay of 0 milliseconds.
 */
public interface Speed {

    /**
     * This static variables represent the delay of "accelerated speed".
     */
    int ACCELERATED_SPEED = 200;

    /**
     * Initializes currentPause with the speed associated to the current level.
     */
    void setSpeedToCurrentLevel();

    /**
     * Sets currentPause equal to ACCELERATED_SPEED.
     */
    void acceleratedSpeed();

    /**
     * Places the piece dropping down it until it can.
     */
    void istantPositioning();

    /**
     * @return currentPause, the quantity of milliseconds of the timer delay.
     */
    int getPause();
}
