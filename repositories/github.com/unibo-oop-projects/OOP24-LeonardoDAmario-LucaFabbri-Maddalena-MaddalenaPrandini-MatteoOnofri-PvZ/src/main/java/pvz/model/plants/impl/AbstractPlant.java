package pvz.model.plants.impl;

import pvz.model.collisions.impl.HitBoxFactory.HitBoxType;
import pvz.model.game.api.EntitiesManager;
import pvz.model.entities.impl.AbstractEntity;
import pvz.model.plants.api.Plant;
import pvz.utilities.Position;

/**
 * Abstract base class for all plant types in the game.
 * Implements common behavior such as life tracking and hitbox setup.
 */
public abstract class AbstractPlant extends AbstractEntity implements Plant {

    private int damage;

    /**
     * Constructs an AbstractPlant with the specified position and default plant hitbox.
     *
     * @param position The initial position of the plant.
     */
    public AbstractPlant(final Position position) {
        super(position, HitBoxType.PLANT);
    }

    /**
     * @inheritDoc
     */
    @Override
    public abstract void update(long deltaTime, EntitiesManager entitiesManager);

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getLife() {
        return this.getMaxLife() - this.damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void decreaseLife(final int damage) {
        this.damage = this.damage + damage;
    }

    /**
     * Gets the maximum life value for the specific type of plant.
     *
     * @return The maximum life points of the plant.
     */
    protected abstract int getMaxLife();
}
