package javagotchi.model.minigame;

/**
 * 
 * @author marica
 *
 */
public interface Time extends java.io.Serializable {

    /**
     * Starts the timer.
     */
    void start();

    /**
     * Gets Seconds.
     * 
     * @return seconds passed
     */
    int getSeconds();

    /**
     * Stops timer.
     */
    void stopTimer();

    /**
     * Creates a new timer with the seconds of a previous timer.
     * 
     * @return timer with the seconds of a previous timer
     */
    Time restart();

    /**
     * Indicates if passed second is in in range [0, TIME_START].
     * 
     * @return true if the seconds is in range [0, TIME_START]
     */
    boolean isTimeGame();

    /**
     * Add a Bonus of 3 seconds.
     */
    void addTime();

    /**
     * Gets the start seconds of the game.
     * 
     * @return start seconds of the game.
     */
    int getStartTime();

    /**
     * 
     * Indicates if passed second is the start time.
     * 
     * @return true if it is the start time.
     */
    boolean isStartTime();

}
