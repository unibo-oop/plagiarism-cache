package model.strategy;

import model.hitbox.CircleHitBox;
import model.hitbox.HitBox;

/**
 * Interface that represent root of movement types.
 */
public interface MovementStrategy {

    /**
     * Method to perform move.
     * 
     * @param dt
     *            Delta time.
     * @param vel
     *            Velocity.
     * @param h
     *            HitBox of entity that require movement.
     * @return HitBox in new position of entity.
     */
    HitBox move(double dt, double vel, CircleHitBox h);
}
