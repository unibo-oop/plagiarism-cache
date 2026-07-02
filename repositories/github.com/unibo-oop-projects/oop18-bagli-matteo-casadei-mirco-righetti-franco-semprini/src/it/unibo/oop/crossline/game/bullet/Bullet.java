package it.unibo.oop.crossline.game.bullet;

import com.badlogic.gdx.math.Vector2;

import it.unibo.oop.crossline.game.actor.Actor;
import it.unibo.oop.crossline.game.attributes.Destructible;
import it.unibo.oop.crossline.game.attributes.Updatable;

/**
 * This interface represents a bullet, and object used to apply damage to {@link it.unibo.oop.crossline.game.attribute.Damageable}.
 */
public interface Bullet extends Destructible, Updatable {

    /**
     * Get the bullet damage.
     * @return the bullet damage
     */
    float getDamage();

    /**
     * Get the bullet direction.
     * @return the bullet direction
     */
    Vector2 getDirection();

    /**
     * Get the bullet owner.
     * @return the bullet owner
     */
    Actor getOwner();

    /**
     * Get the bullet speed.
     * @return the bullet speed
     */
    float getSpeed();

}
