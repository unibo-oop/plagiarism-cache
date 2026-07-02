package model.entity;

import utilities.math.Point2D;

/**
 * A RotatingEntity implementation.
 */
public class RotatingEntityImpl extends CollidableEntityImpl implements RotatingEntity {

    private static final double DEFAULT_ANGLE = 0;

    private double radiantAngle;

    public RotatingEntityImpl() {
        super();
        this.radiantAngle = DEFAULT_ANGLE;
    }
    public RotatingEntityImpl(final Point2D position, final double radius) {
        this(position, radius, DEFAULT_ANGLE);
    }
    public RotatingEntityImpl(final Point2D position, final double radius, final double radiantAngle) {
        super(position, radius);
        this.radiantAngle = radiantAngle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getRotation() {
        return this.radiantAngle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetRotation(final double newAngle) {
        this.radiantAngle = newAngle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rotateAnticlockwise(final double angle) {
        this.radiantAngle = (this.radiantAngle + angle) % (2 * Math.PI);
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
        temp = Double.doubleToLongBits(radiantAngle);
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
        final RotatingEntityImpl other = (RotatingEntityImpl) obj;
        return Double.doubleToLongBits(radiantAngle) == Double.doubleToLongBits(other.radiantAngle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RotatingEntityImpl [ID=" + this.getID() + ", position=" + getPosition()
               + ", radius=" + this.getRadialHitbox() + ", radiantAngle=" + radiantAngle + "]";
    }
    /*-----------------------------------------------------------------*/
}
