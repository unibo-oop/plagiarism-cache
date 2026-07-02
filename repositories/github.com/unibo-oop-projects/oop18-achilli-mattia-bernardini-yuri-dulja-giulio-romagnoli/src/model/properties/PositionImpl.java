package model.properties;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Class that represent the entity's position in the game.
 */
public class PositionImpl implements Position {

    private final double x;
    private final double y;

    /**
     * Constructor that passes this entity's position parameters to the super-class.
     * 
     * @param x
     *            This entity's position axis X
     * 
     * @param y
     *            This entity's position axis Y
     */
    public PositionImpl(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @param position
     *            to clone
     */
    public PositionImpl(final Position position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    @Override
    public final Position sum(final Velocity v) {
        return new PositionImpl(x + v.getX(), y + v.getY());
    }

    @Override
    public final Velocity sub(final Position p) {
        return new VelocityImpl(x - p.getX(), y - p.getY());
    }

    @Override
    public final double getX() {
        return this.x;
    }

    @Override
    public final double getY() {
        return this.y;
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder().append(this.x).append(this.y).toHashCode();
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PositionImpl other = (PositionImpl) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        return Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
    }

    /**
     * @return the description of the position
     */
    @Override
    public String toString() {
        return "Position[x: " + this.x + ",y: " + this.y + "]";
    }

}
