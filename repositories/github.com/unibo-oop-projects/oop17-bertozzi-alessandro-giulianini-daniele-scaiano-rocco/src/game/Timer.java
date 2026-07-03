package game;

/**
 * A class used to count time synchronously with the gameLoop.
 */
public interface Timer {

    /**
     * This method tells the timer a time unit as elapsed.
     */
    void tick();

    /**
     * @return whether this timer has reached is time goal
     */
    boolean isEnded();

    /**
     * @return the time left for this timer
     */
    int getTimeLeft();
}
