package it.unibo.oop.crossline.game.bullet;

import com.badlogic.gdx.math.Vector2;

import it.unibo.oop.crossline.game.actor.Actor;

/**
 * This interface represent a bullet builder.
 */
public interface BulletBuilder {

    /**
     * Build the bullet with the setted parameters.
     * @return the built bullet
     */
    Bullet build();

    /**
     * Set the bullet damage.
     * @param damage the bullet damage
     * @return the bullet builder
     */
    BulletBuilder setDamage(float damage);

    /**
     * Set the bullet direction.
     * @param direction the bullet direction
     * @return the bullet builder
     */
    BulletBuilder setDirection(Vector2 direction);

    /**
     * Set the bullet owner.
     * @param owner the bullet owner
     * @return the bullet builder
     */
    BulletBuilder setOwner(Actor owner);

    /**
     * Set the bullet position.
     * @param position the bullet position
     * @return the bullet builder
     */
    BulletBuilder setPosition(Vector2 position);

    /**
     * Set the bullet size.
     * @param size the bullet size
     * @return the bullet builder
     */
    BulletBuilder setSize(float size);

    /**
     * Set the bullet speed.
     * @param speed the bullet speed
     * @return the bullet builder
     */
    BulletBuilder setSpeed(float speed);

}
