package model.entity;

import utilities.math.Point2D;

/**
 * A CollidableEntity implementation.
 */
public class CollidableEntityImpl extends EntityImpl implements CollidableEntity {

    private static final double DEFAULT_RADIUS = 0;

    private double radius;

    public CollidableEntityImpl() {
        super();
        this.radius = DEFAULT_RADIUS;
    }
    public CollidableEntityImpl(final Point2D position) {
        this(position, DEFAULT_RADIUS);
    }
    public CollidableEntityImpl(final Point2D position, final double radius) {
        super(position);
        this.radius = radius;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getRadialHitbox() {
        return this.radius;
    }

    /**
     * Changes the radius of the circle occupied by this entity into a new radius.
     * @param newRadius : the new radius of the circle occupied by this entity.
     */
    public void setRadius(final double newRadius) {
        this.radius = newRadius;
    }

    /*EQUALS & TOSTRING------------------------------------------------*/
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(radius);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CollidableEntityImpl other = (CollidableEntityImpl) obj;
        return Double.doubleToLongBits(radius) == Double.doubleToLongBits(other.radius);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "CollidableEntityImpl [ID=" + this.getID() + ", position=" + getPosition()
               + ", radius=" + radius + "]";
    }
    /*-----------------------------------------------------------------*/

}
