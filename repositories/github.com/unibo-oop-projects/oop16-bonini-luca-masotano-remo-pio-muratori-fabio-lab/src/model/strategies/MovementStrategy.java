package model.strategies;

import java.io.Serializable;

import model.hitbox.HitboxImpl;
import model.hitbox.HitboxCircle;

/**
 * 
 * The logic of Movement. This interface represents a path or movement followed
 * by all the movable entities.
 *
 */
public interface MovementStrategy extends Serializable {

    /**
     *
     * @param h
     *            the coordinate Hitbox of the entity to be moved
     * @param steps
     *            The number of steps performed during the movement. Represents
     *            the speed of the entity that follow this MovementStrategy.
     * @param delta
     *            The delta of time.
     * @return A new Hitbox of the position computed.
     */
    HitboxImpl movement(HitboxCircle h, double steps, double delta);
}
