package it.unibo.oop.manpac.model.entities;

import java.util.Objects;

import it.unibo.oop.manpac.model.Action;
import it.unibo.oop.manpac.model.Directions;

/**
 * Abstract class that generalizes the behavior of a mobile entity.
 */
public abstract class MobileEntityImpl implements MobileEntity {

    private Directions currentDirection;

    /**
     * Constructor of the MobileEntityImpl class.
     */
    public MobileEntityImpl() {
        this.currentDirection = Directions.STOP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action setDirection(final Directions direction) {
        if (this.currentDirection == Objects.requireNonNull(direction)) {
            return Action.NOTHING_HAPPENS;
        }
        this.currentDirection = direction;
        return this.changeAction(this.currentDirection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Directions getDirection() {
        return this.currentDirection;
    }

    /**
     * Method called for a change of direction.
     * 
     * @param direction The new directions
     * @return the action corresponding to the direction
     */
    protected abstract Action changeAction(Directions direction);

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        return prime + currentDirection.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MobileEntityImpl other = (MobileEntityImpl) obj;
        return currentDirection == other.currentDirection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "MobileEntityImpl [currentDirection=" + currentDirection + "]";
    }
}
