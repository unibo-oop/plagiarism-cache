package model.hitbox;

import java.io.Serializable;

/**
 * Defines the basic shape used to check collisions between entities. It's represented
 * with two coordinates.
 *
 */
public class HitboxImpl implements Serializable, Hitbox {

    /**
     * A unique serial version identifier
     * 
     * @see Serializable#serialVersionUID
     */
    private static final long serialVersionUID = 4280161680607338579L;
    private double x;
    private double y;

    /**
     * Constructs a new instance of the class Hitbox.
     * 
     * @param x
     *            The x coordinate.
     * @param y
     *            The y coordinate.
     */
    public HitboxImpl(final double x, final double y) {
        changePosition(x, y);

    }

    @Override
    public void changePosition(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return new String("(" + this.x + "," + this.y + ")");
    }

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
        final Hitbox other = (Hitbox) obj;
        return Double.doubleToLongBits(y) == Double.doubleToLongBits(other.getY())
                && Double.doubleToLongBits(x) == Double.doubleToLongBits(other.getX());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
