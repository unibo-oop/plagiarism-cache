package model.entities;

import model.hitbox.HitboxImpl;
import model.hitbox.HitboxCircle;
import model.strategies.MovementStrategy;

/**
 * 
 * Represents the bullets shot in the game.
 *
 */
public class BulletImpl extends MovableEntity implements Bullet {

    /**
     * A unique serial version identifier
     * @see Serializable#serialVersionUID
     */
    private static final long serialVersionUID = 1466705058995317346L;
    private double range;
    private final MovementStrategy ms;

    /**
     * 
     * @param h
     *            The HitboxCircle of the Bullet.
     * @param steps
     *            The number of steps performed during the movement. Represents
     *            the speed of this entity.
     * @param damage
     *            The damage made by this entity on collisions with other entity.
     * @param ms
     *            The MovementStrategy followed by this enemy.
     * @param range
     *            The range of the Bullet.
     */
    public BulletImpl(final HitboxCircle h, final double steps, final double damage, final MovementStrategy ms,
            final double range) {
        super(h, steps, damage);
        this.range = range;
        this.ms = ms;
    }

    @Override
    protected HitboxImpl performMove(final double delta) {
        range -= super.getSteps() * delta;
        return ms.movement(super.getHitbox(), super.getSteps(), delta);
    }

    @Override
    public boolean isDead() {
        return range <= 0;
    }

    @Override
    public double getRange() {
        return this.range;
    }
}
