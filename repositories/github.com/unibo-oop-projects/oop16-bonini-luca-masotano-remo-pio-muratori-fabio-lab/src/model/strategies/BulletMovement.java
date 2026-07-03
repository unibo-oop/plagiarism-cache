package model.strategies;

import model.hitbox.HitboxImpl;
import model.hitbox.HitboxCircle;

/**
 * 
 * Defines the Movement followed by a bullet.
 *
 */
public class BulletMovement implements MovementStrategy {

    /**
     * A unique serial version identifier
     * 
     * @see Serializable#serialVersionUID
     */
    private static final long serialVersionUID = -8343307789873174063L;
    private final double angle;

    /**
     * Constructs a new instance of BulletMovement.
     * 
     * @param angle
     *            The angle the movable entity follow.
     */
    public BulletMovement(final double angle) {
        this.angle = angle;
    }

    @Override
    public HitboxImpl movement(final HitboxCircle h, final double steps, final double delta) {
        final double stepsx = steps * delta * Math.sin(angle * Math.PI / 180);
        final double stepsy = -steps * delta * Math.cos(angle * Math.PI / 180);
        return new HitboxImpl(h.getX() + stepsx, h.getY() + stepsy);
    }

}
