package justanotherchessgame.view.game;

/**
 * Interface used to create and manage a timer.
 */
public interface TimerView {

    /**
     * Function used to initialize the timer.
     */
    void initializeTimer();

    /**
     * Function used to start the timer.
     */
    void startTimer();

    /**
     * Function used to stop the timer.
     */
    void stopTimer();

    /**
     * Function used to get the state of the timer: if it is running or not.
     * @return the timer state: true is running, false is not.
     */
    boolean isActive();
}
