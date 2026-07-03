package model;

import java.util.Collection;

import model.entities.Bullet;
import model.map.Map;
import model.utils.Direction;
import model.entities.Character;

/**
 * 
 * Represents the main class of the model. It has all the needed operation to
 * update the model.
 *
 */
public interface GameModel {

    /**
     * Perform all the operation needed to update the Model's entities.
     * 
     * @param pmove
     *            Represents the direction where the player have to move.
     * @param playerShoot
     *            Represents the direction where the player have to shoot.
     * @param delta
     *            The delta of time.
     */
    void update(Collection<Direction> pmove, Collection<Direction> playerShoot, double delta);

    /**
     * Get the instance of the player.
     * 
     * @return the player.
     */
    Character getPlayer();

    /**
     * Get a collection of bullets out of their range.
     * 
     * @return A collection of Bullet.
     */
    Collection<Bullet> getDeadBullets();

    /**
     * Clear all the bullets shot by entities.
     */
    void clearBullets();

    /**
     * Get the collection of bullet shot by the player.
     * 
     * @return A collection of Bullet.
     */
    Collection<Bullet> getPlayerBullets();

    /**
     * Get the collection of bullet shot by enemies.
     * 
     * @return A collection of Bullet.
     */
    Collection<Bullet> getEnemiesBullets();

    /**
     * Get the map of the game.
     * 
     * @return The game map.
     */
    Map getMap();

    /**
     * Return true if the player has changed room, false otherwise.
     * 
     * @return A boolean.
     */
    boolean roomChanged();

    /**
     * True if the game is over, false otherwise.
     * @return A boolean.
     */
    boolean isGameOver();

}