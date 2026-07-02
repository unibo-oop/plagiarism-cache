package it.unibo.arkanoid.model;

/**
 * The interface with the method for modeling the core of the game.
 *
 */
public interface Model {

    /**
     * Number of Life/attempts to win the game.
     * 
     * @return the value of remaining life for the player.
     */
    int getPlayerLife();

    /**
     * The current score.
     * 
     * @return the value of current score.
     */
    int getScore();

    /**
     * The current {@link Level}.
     * 
     * @return the current level in the game.
     */
    Level getCurrentLevel();

    /**
     * Update all the state for all {@link Subject} in the game's world telling them that's
     * it's past deltaTime.
     * 
     * @param deltaTime
     *            time elapsed.
     */
    void updateAll(double deltaTime);

    /**
     * @return the gameScreenWidth
     */
    double getGameWorldWidth();

    /**
     * @return the gameScreenHeight
     */
    double getGameWorldHeight();

    /**
     * It's useful to move {@link Paddle} in the Game Field.
     * 
     * @param coordinate
     *            The x coordinate of the mouse.
     */
    void setPaddlePosition(double coordinate);

    /**
     * this method is used to set a new level.
     * 
     * @param level
     *            a level
     */
    void setLevel(Level level);

    /**
     * 
     * @return
     *          a {@link LevelBuilder}.
     */
    LevelBuilder getLevelBuilder();

    /**
     * 
     * Set the position when the player loses a life.
     */
    void resetPosition();

}
