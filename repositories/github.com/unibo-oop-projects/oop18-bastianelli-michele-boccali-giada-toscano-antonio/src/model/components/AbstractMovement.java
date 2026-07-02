package model.components;

import model.entities.EntityModel;

/**
 * Allows the entity to move.
 */
public abstract class AbstractMovement extends AbstractComponent implements Movement {

    private float speed;

    public AbstractMovement(final EntityModel owner, final float speed) {
        super(owner);
        this.speed = speed;
    }

    /**
     * @return the characteristic speed of the entity.
     */
    protected final float getSpeed() {
        return speed;
    }

    /**
     * @param speed the custom speed
     */
    protected final void setSpeed(final float speed) {
        this.speed = speed;
    }

}
