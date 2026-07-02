package model.utilities;

import java.io.Serializable;
import java.util.Objects;

/**
 * A class which represents the positions of the various entities in this game.
 */
public class Position implements Serializable {

    private static final long serialVersionUID = 6652064500933057752L;
    private final double x;
    private final double y;

    /**
     * Keeps positions.
     * 
     * @param x value of x-axis
     * @param y value of y-axis
     */
    public Position(final double x, final double y) {
        this.x = Objects.requireNonNull(x);
        this.y = Objects.requireNonNull(y);
    }

    /**
     * 
     * @return x-axis value.
     */
    public final double getX() {
        return x;
    }

    /**
     * 
     * @return y-axis value.
     */
    public final double getY() {
        return y;
    }

    /**
     * sum between two vectors.
     * @param v the vector to be added
     * @return vector sum of the two positions
     */
    public Position sum(final DirVector v) {
        return new Position(this.x + v.getX(), this.y + v.getY());
    }

    /**
     * {@inheritDoc}
     */
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
        final Position other = (Position) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
            return false;
        } else if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        return true;
    }
    @Override
    public final String toString() {
        return "Position [x=" + x + ", y=" + y + "]";
    }
}
