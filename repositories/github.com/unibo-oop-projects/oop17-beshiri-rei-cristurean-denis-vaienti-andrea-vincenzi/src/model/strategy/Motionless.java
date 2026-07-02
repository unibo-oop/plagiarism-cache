package model.strategy;

import model.hitbox.CircleHitBox;
import model.hitbox.HitBox;

/**
 * Class that represent static movement.
 *
 */
public class Motionless implements MovementStrategy {

    /**
     * Entity without movement.
     */
    @Override
    public HitBox move(final double dt, final double vel, final CircleHitBox h) { 
        return h;
    }

}
