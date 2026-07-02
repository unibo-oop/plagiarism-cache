package it.unibo.oop.manpac.model;

import it.unibo.oop.manpac.model.score.Points;
import it.unibo.oop.manpac.model.score.ScorePoints;

/**
 * 
 * Interface for the MODEL in the MVC pattern. Represents the public part for
 * the controller.
 *
 */
public interface ModelForController {

    /**
     * Manage Pacman's collisions.
     * 
     * @param entity the entity which collided with pacman
     *
     * @return what happened in that collision
     */
    Action pacmanCollision(Entity entity);

    /**
     * Manage Phantoms' collisions.
     * 
     * @param name   the name of the phantom which collided with entity
     * @param entity the entity which collided with the phantom
     *
     * @return what happened in that collision
     */
    Action phantomCollision(Entity name, Entity entity);

    /**
     * Manage Pacman's changes of direction via input.
     * 
     * @param direction the direction received via input
     *
     * @return what happened after the change of direction
     */
    Action inputDirection(Directions direction);

    /**
     * Getter for the high score.
     *
     * @return the high score
     */
    Points<Integer> getHighScore();

    /**
     * Getter for the actual game score.
     *
     * @return the actual score
     */
    Points<Integer> getCurrentScore();

    /**
     * Reset the current score.
     */
    void resetCurrentScore();

    /**
     * Getter for the ScorePoints.
     *
     * @return the ScorePoints of the game
     */
    ScorePoints<Integer> getScorePoints();

    /**
     * Reset the high and current score.
     *
     */
    void resetScorePoints();

    /**
     * Setter for the high score (for example from file).
     *
     * @param highscore The new high score
     */
    void setHighScore(Points<Integer> highscore);

    /**
     * Getter for the lives left to Pac-Man.
     *
     * @return the the lives left to the player
     */
    int getLives();

    /**
     * Set the number of the pills.
     * 
     * @param numberPills The number of the pills
     * @throws IllegalStateException Launch this exception if this method is called
     *                               up when the game is started (Pac-Man has
     *                               already eaten pills); call the game reset first
     */
    void setPills(int numberPills) throws IllegalStateException;

    /**
     * Reset the all game (except for high score).
     */
    void resetGame();

    /**
     * Reset the play: pills, phantom, Pac-Man position (don't reset all score and
     * Pac-Man lives).
     */
    void resetAndContinue();

    /**
     * To remove the state of fear from phantom.
     */
    void stopPower();

}
