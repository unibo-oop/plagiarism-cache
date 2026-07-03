package laterunner.model.world;

import laterunner.model.vehicle.Obstacle;

/**
 * The interface in which is defined the game's state.
 *
 */
public interface GameState {

    /**
     * Return the GameState's world.
     * 
     * @return
     *          the GameState's world
     */
    World getWorld();

    /**
     * Decrease the score by the malus of o.
     * 
     * @param o
     *          the Obstacle hit 
     */
    void decScore(final Obstacle o);

    /**
     * Decrease the score by the malus of the border.
     * 
     */
    void decScorebyBorder();

    /**
     * Increase score by i.
     * 
     * @param i
     *          the value to increase the score
     */
    void incScore(final int i);

    /**
     * Get the level's score.
     * 
     * @return
     *          the score
     */
    int getScore();

    /**
     * Update the world by elapsed.
     * 
     * @param elapsed
     *          the time elapsed
     */
    void update(final int elapsed);

    /**
     * Check if the level is finished.
     * 
     * @return
     *              true is the level is finished
     */
    boolean isLevelFinished();

    /**
     *  Return true is mood survival is finished.
     * 
     * @return
     *          true is mood survival is finished
     */
    boolean isEndSurvival();

    /**
     * Set the mood survival at endSurvival.
     * 
     * @param sEndSurvival
     *          the parameter to set survival
     */
    void setEndSurvival(final boolean sEndSurvival);

    /**
     * Update the User statistics.
     */
    void updateStats();

}