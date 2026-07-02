package zombieversity.model.entities.weapon;

import javafx.geometry.Point2D;
import zombieversity.model.entities.AbstractEntity;
import zombieversity.model.entities.EntityType;

/**
 * Abstract class to set up all the common features of an attack.
 */
public abstract class AbstractAttack extends AbstractEntity implements Attack {

    private final int damage;

    /**
     * @param from
     *          The point where the attack is generated.
     * @param damage
     *          The damage dealt to the enemies by this attack.
     * @param entityType
     *          The type of this attack.
     */
    public AbstractAttack(final Point2D from, final int damage, final EntityType entityType) {
        super(from, entityType);
        this.damage = damage;
    }

    /**
     * @return
     *          The trajectory followed by this attack.
     */
    protected abstract Movement getMovement();

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getDamage() {
        return this.damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Point2D getPosition() {
        return getMovement().getActualPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void update() {
        this.getMovement().update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final double getDirection() {
        return this.getMovement().getAngle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean hasEnded() {
        return this.getMovement().hasEnded();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Point2D getVelocity() {
        final double vel = this.getMovement().getVelocity();
        return new Point2D(vel * Math.cos(this.getDirection()), vel * Math.sin(this.getDirection()));
    }

    /**
     * Not useful for this {@link ActiveEntity}. It will have no effect.
     */
    @Override
    public final void setVelocity(final Point2D vel) {

    }

    /**
     * Not useful for this {@link ActiveEntity}. It will have no effect.
     */
    @Override
    public final void setDirection(final double angle) {

    }

}
