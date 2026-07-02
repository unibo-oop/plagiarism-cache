package it.dpg.minigames.punchygame.model;

/**
 * Interface for a descending timer used in PunchyMinigame
 * @author Davide Picchiotti
 * @see it.dpg.minigames.punchygame.PunchyMinigame
 * */

public interface Timer {

    /**
     * Decrease timer by a certain amount
     * @param elapsed amount to decrease
     * */
    void timerDecrease(final float elapsed);

    /**
     * Increase the timer by a fixed amount
     * */
    void timerIncrease();

    /**
     * @return time left on the timer
     * */
    float getTimeLeft();

    /**
     * @return max timer time
     * */
    float getMaxTime();
}
