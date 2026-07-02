package model.strategy;

import model.hitbox.CircleHitBox;
import model.hitbox.HitBox;

/**
 * Class that represent the bullet movement.
 *
 */
public class BulletMovement implements MovementStrategy {

    private final double angle;

    /**
     * Constructor for this class.
     * 
     * @param angle
     *            Movement's angle in degrees.
     */
    public BulletMovement(final double angle) {
        this.angle = angle;
    }

    /**
     * Perform movement of the bullet, using angle.
     */
    @Override
    public HitBox move(final double dt, final double vel, final CircleHitBox h) {
        final double deltaY = dt * vel * Math.sin(Math.toRadians(angle));
        final double deltaX = dt * vel * Math.cos(Math.toRadians(angle));
        return new CircleHitBox(h.getX() + deltaX, h.getY() + deltaY, h.getRadius());
    }

}
