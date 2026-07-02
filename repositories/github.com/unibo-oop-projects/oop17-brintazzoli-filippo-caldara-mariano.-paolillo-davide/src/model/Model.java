package model;

import model.input.Input;
import model.object.Tank;
import model.utility.Pair;

/**
 * Model interface contain all elements that game needs.
 * 
 * <p>Create player, and enemy, and give possibility to return them.
 */
public interface Model {
    /**
     * Create a {@link Tank} controlled by player.
     * 
     * @param position
     *            of Tank
     * @param lifes
     *            nr of lifes
     */
    void configPlayerTank(Pair<Double, Double> position, int lifes);

    /**
     * Create a {@link Tank} controlled by computer.
     * 
     * @param position
     *            of Tank
     * @param lifes
     *            nr of lifes
     * @param speed
     *            speed of movement
     * @param projectileSpeed
     *            speed of projectile
     */
    void configEnemyTank(Pair<Double, Double> position, int lifes, double speed, double projectileSpeed);

    /**
     * Getter for player.
     * 
     * @return player's Tank
     */
    Tank getPlayer();
    /**
     * Getter for enemy.
     * 
     * @return enemie's Tank
     */
    Tank getEnemy();

    /**
     * Getter for bounds of World.
     * 
     * @return bounds First = width, Second = height
     * @see Pair
     */
    Pair<Double, Double> getBounds();

    /**
     * Getter for tank input Player.
     * 
     * @return {@link TankInput} of player.
     */
    Input getPlayerInput();

    /**
     * Getter for tank input Enemy.
     * 
     * @return {@link TankInput} of enemy.
     */
    Input getEnemyInput();

}