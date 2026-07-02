package it.dpg.minigames.punchygame.model;

/**
 * Interface for a score with combo system
 * @author Davide Picchiotti
 * */

public interface Score {

    /**
     * Increment the score based on the current multiplier
     * */
    void incrementScore();

    /**
     * @return the score
     * */
    int getPoints();

    /**
     * @return the score multiplier based on current combo
     * */
    int getMultiplier();

    /**
     * Reset the combo value used for score multiplier calculation
     * */
    void resetCombo();
}
