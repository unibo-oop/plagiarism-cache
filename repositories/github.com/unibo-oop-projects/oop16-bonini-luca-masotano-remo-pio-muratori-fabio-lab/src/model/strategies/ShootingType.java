package model.strategies;

import java.io.Serializable;
import java.util.Collection;

import model.entities.Bullet;
import model.hitbox.Hitbox;

/**
 * 
 * Represents all the type of shoot created in the game.
 *
 */
public interface ShootingType extends Serializable {

    /**
     * Implements the algorithm which determines how many bullet are shot and
     * the way they are shot.
     * 
     * @param from
     *            The Hitbox where the bullet should start.
     * @param delta
     *            The delta of time.
     * @param fireRate
     *            The fireRate of the entity that use this strategy.
     * @param damage
     *            The damage made by the bullet.
     * @param range
     *            The range of the bullet.
     * @param steps
     *            The number of steps performed during the movement. Represents
     *            the speed of the bullet.
     * @return The bullet shot
     */
    Collection<Bullet> shoot(Hitbox from, double delta, double fireRate, double damage, double range, double steps);

}
