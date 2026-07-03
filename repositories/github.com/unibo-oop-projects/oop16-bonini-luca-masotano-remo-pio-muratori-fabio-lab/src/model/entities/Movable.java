package model.entities;

import model.hitbox.HitboxCircle;

/**
 * 
 * Represents all the movable entities.
 *
 */
public interface Movable extends Entity<HitboxCircle> {

    /**
     * 
     * @return The number of steps performed during the movement. Represents the
     *         speed of this entity.
     */
    double getSteps();

    /**
     * 
     * @return The damage made by this entity on collision with other entities.
     */
    double getCollisionDamage();

    /**
     * Perform the movement that change the position of this entity.
     * 
     * @param delta The delta of time
     */
    void move(double delta);

}
