package model.entity;

import utilities.math.Point2D;
import utilities.math.Point2DImpl;

/**
 * An Entity implementation.
 */
public class EntityImpl implements Entity {

    private static final Point2D DEFAULT_POSITION = new Point2DImpl(0, 0);

    private Point2D position;

    public EntityImpl() {
        this(DEFAULT_POSITION);
    }
    public EntityImpl(final Point2D position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityID getID() {
        return EntityID.DEFAULT_ENTITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetPosition(final Point2D newPosition) {
        this.position = newPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getPosition() {
        return this.position;
    }

    /*EQUALS & TOSTRING------------------------------------------------*/
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        return result;
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
        final EntityImpl other = (EntityImpl) obj;
        if (position == null) {
            if (other.position != null) {
                return false;
            }
        } else if (!position.equals(other.position)) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "EntityImpl [ID=" + this.getID() + ", position=" + position + "]";
    }
    /*-----------------------------------------------------------------*/
}
