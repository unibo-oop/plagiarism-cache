package zombieversity.model.entities;

import javafx.geometry.Point2D;
import zombieversity.model.entities.utils.Life;
import zombieversity.model.entities.utils.LifeImpl;

/**
 * 
 * Models a living entity that is an entity with one or more lives and capable
 * of moving.
 *
 */
public abstract class AbstractActiveLivingEntity extends AbstractEntity implements ActiveLivingEntity {

    private Point2D vel;
    private final Life lifeManager;
    private double direction;

    /**
     * Construct an {@link AbstractActiveLivingEntity}.
     * 
     * @param vel   the velocity.
     * @param maxHp the max life.
     * @param point the position in the game world.
     * @param type  which type of entity is.
     */
    public AbstractActiveLivingEntity(final Point2D vel, final int maxHp, final Point2D point, final EntityType type) {
        super(point, type);
        this.vel = vel;
        this.lifeManager = new LifeImpl(maxHp);
        this.direction = 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setDirection(final double direction) {
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final double getDirection() {
        return this.direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setVelocity(final Point2D vel) {
        this.vel = vel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Point2D getVelocity() {
        return this.vel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Life getLifeManager() {
        return this.lifeManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.setPosition(this.getPosition().add(this.vel));
    }
}
