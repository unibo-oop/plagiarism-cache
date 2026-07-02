package it.unibo.aurea.model.api;

/**
 * Manage the time during the game.
 */
public interface GameClock {
    /**
     * @return how many cards were already presented to the player. the counter start from 0. 
     * 
     */
    int getCurrentTurn();

    /**
     * increase the number of card already shown to the player. and handles the end of the semester.
     */
    void nextTurn();

    /**
     * @return the current semester. the counter start from 0. 
     */
    int getCurrentSemester();

    /**
     * @return true if the conditions for the end of the game are True.
     */
    boolean isTimeFinished();
}
