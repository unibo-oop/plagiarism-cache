package game;

import java.awt.Rectangle;

import utilities.Pair;

/**
 * The interface for every object present in the game.
 */
public interface GameObject {

    /**
     * The method that updates the status of the object.
     */
    void update();

    /**
     * Notifies this entity that it's colliding with another entity.
     * @param entity the entity this entity is colliding with
     */
    void collide(GameObject entity);

    /**
     * @return the hitbox of the object
     */
    Rectangle getHitbox();
 
    /**
     * @return whether this object is dead
     */
    boolean isDead();

    /**
     * @return the current velocity
     */
    Pair<Integer, Integer> getVelocity();
 
    /**
     * @return the current position of this object
     */
    Pair<Integer, Integer> getPosition();

    /**
     * @return the id of the object
     */
    ID getID();

    /**
     * Sets the hitbox of this object.
     * @param hitbox the new hitbox of the object
     */
    void setHitbox(Rectangle hitbox);

    /**
     * Sets this object as dead.
     */
    void setDead();

    /**
     * Sets the position of this object.
     * @param position the new position of the object
     */
    void setPosition(Pair<Integer, Integer> position);
}
