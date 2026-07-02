package controller.stagehandler;

/**
 * Models the concept of time in terms of turns.
 */
public class Time {

    private static final int MAX_FPS = 60;
    /** The time between one frame and the next one in milliseconds. */
    public static final long MS_TIME_FRAME = 1000 / MAX_FPS;
    private static int turnCounter;
    private final int turn;

    static {
        Time.turnCounter = 0;
    }

    public Time() {
        this.turn = Time.turnCounter;
    }

    /**
     * Returns the time elapsed between this time and the current time,
     * expressed as the number of turns that has passed since this time.
     * @return the time elapsed between this time and the current time.
     */
    public int getElapsedTime() {
        return Time.turnCounter - this.turn;
    }

    /**
     * Makes the time elapse, effectively increasing the turn counter by one.
     */
    public static void elapse() {
       Time.turnCounter++;
    }
    /**
     * Returns the current turn.
     * @return the current turn.
     */
    public static int getCurrentTurn() {
        return turnCounter;
    }

}
