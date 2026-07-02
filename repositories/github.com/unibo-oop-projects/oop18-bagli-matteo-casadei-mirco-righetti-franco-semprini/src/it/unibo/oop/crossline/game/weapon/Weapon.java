package it.unibo.oop.crossline.game.weapon;

import com.badlogic.gdx.math.Vector2;

import it.unibo.oop.crossline.game.actor.Actor;
import it.unibo.oop.crossline.game.bullet.BulletBuilder;

/**
 * This interface represents a weapon used by an {@link it.unibo.oop.crossline.game.actor.Actor}.
 */
public interface Weapon {

    /**
     * Check if the weapon can shoot.
     * @return a value representing if the weapon can shoot
     */
    boolean canShoot();

    /**
     * Get the {@link it.unibo.oop.crossline.game.bullet.BulletFactory} attached to this weapon.
     * @return the bullet factory
     */
    BulletBuilder getBulletFactory();

    /**
     * Get the direction in which the weapon is aiming.
     * @return the aiming direction
     */
    Vector2 getDirection();

    /**
     * Set the weapon aiming direction.
     * @param direction the weapon aiming direction.
     */
    void setDirection(Vector2 direction);

    /**
     * Set the weapon owner.
     * @param owner the owner
     */
    void setOwner(Actor owner);

    /**
     * Shoot a bullet.
     */
    void shoot();

}
