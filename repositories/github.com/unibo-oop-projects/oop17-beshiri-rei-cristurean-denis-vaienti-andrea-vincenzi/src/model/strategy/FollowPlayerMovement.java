package model.strategy;

import model.hitbox.CircleHitBox;
import model.hitbox.HitBox;
import model.utility.ModelUtility;

/**
 * Class that represent the movement of aimed bullet or enemy.
 * 
 */
public class FollowPlayerMovement implements MovementStrategy {

    /**
     * Move the entity to next step.
     */
    @Override
    public HitBox move(final double dt, final double vel, final CircleHitBox h) {
        final double angle = Math.toDegrees(Math.atan2((ModelUtility.getPlayerHitBox().getY() - h.getY()),
                ModelUtility.getPlayerHitBox().getX() - h.getX()));
        final double deltaX = dt * vel * Math.cos(Math.toRadians(angle));
        final double deltaY = dt * vel * Math.sin(Math.toRadians(angle));
        return new CircleHitBox(h.getX() + deltaX, h.getY() + deltaY, h.getRadius());
    }
}
