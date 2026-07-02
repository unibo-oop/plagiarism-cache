package it.dpg.minigames.punchygame.model;

import java.util.List;

/**
 * Interface representing game world (model) for PunchyMinigame
 * @author Davide Picchiotti
 * @see it.dpg.minigames.punchygame.PunchyMinigame
 * */

public interface World {

    /**
     * Check if a sack was hit
     * @param direction the direction to check
     * @return if the sack was hit
     * @see Direction
     * */
    boolean checkSackHit(final Direction direction);

    /**
     * @return a List of the boxing sacks, represented by their position from the player perspective
     * @see Direction
     * */
    List<Direction> getSacks();

    /**
     * @return the score
     * */
    int getScore();

    /**
     * @return the score multiplier (combo)
     * */
    int getScoreMultiplier();

    /**
     * @return remaining time on the timer
     * */
    float getTimer();

    /**
     * Update the timer value, based on time elapsed
     * @param elapsed time elapsed from last update
     * */
    void updateTimer(final float elapsed);

    /**
     * @return boxer direction
     * @see Direction
     * */
    Direction getBoxerDirection();

    /**
     * @return if the game is over or not
     * */
    boolean isGameOver();
}
