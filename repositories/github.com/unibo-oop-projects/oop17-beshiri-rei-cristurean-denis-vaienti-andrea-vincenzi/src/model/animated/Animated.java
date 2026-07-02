package model.animated;

import java.util.Collection;

import model.GameObject;

/**
 * Class that represent animated entities in the game.
 *
 */
public interface Animated extends GameObject {

    /**
     * Getter for velocity.
     * 
     * @return Return velocity of the specific object.
     */
    double getVel();

    /**
     * Setter for velocity.
     * 
     * @param vel
     *            Set the velocity of a specific object.
     */
    void setVel(double vel);

    /**
     * Update method, used to perform movement of entity.
     * 
     * @param dt
     *            DeltaTime to update position depending of time.
     * 
     */
    void update(double dt);

    /**
     * Method used to shot a collection of bullets.
     * 
     * @return collection of bullet shoted.
     */
    Collection<Bullet> shot();
}
