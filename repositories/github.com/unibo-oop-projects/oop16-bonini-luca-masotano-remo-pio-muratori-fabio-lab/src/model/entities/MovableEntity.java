package model.entities;

import model.hitbox.Hitbox;
import model.hitbox.HitboxCircle;

/**
 * 
 * Base abstract class that defines the method move for all subclasses.
 *
 */
public abstract class MovableEntity implements Movable {

    /**
     * A unique serial version identifier
     * 
     * @see Serializable#serialVersionUID
     */
    private static final long serialVersionUID = -1731480086869764267L;
    private final double steps;
    private final HitboxCircle h;
    private final double collisionDamage;

    /**
     * The constructor of the abstract class MovableEntity. Initialize all the variables.
     * 
     * @param h
     *            The HitboxCircle of this entity. Movable entities have only HitboxCircle.
     * @param steps
     *            The number of steps performed during the movement. Represents
     *            the speed of this entity.
     * @param collisionDamage
     *            The damage made by this entity on collision with other
     *            entities.
     */
    public MovableEntity(final HitboxCircle h, final double steps, final double collisionDamage) {
        this.h = h;
        this.steps = steps;
        this.collisionDamage = collisionDamage;
    }

    /**
     * Operations performed during the move method.
     * 
     * @param delta
     *            The delta of time.
     * @return The hitbox of the new positions of this entities.
     */
    protected abstract Hitbox performMove(double delta);

    @Override
    public void move(final double delta) {
        final Hitbox pos = performMove(delta);
        this.h.changePosition(pos.getX(), pos.getY());
    }

    @Override
    public double getSteps() {
        return steps;
    }

    @Override
    public double getCollisionDamage() {
        return collisionDamage;
    }

    @Override
    public HitboxCircle getHitbox() {
        return h;
    }
}
