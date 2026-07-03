package model.Implementations;

import java.io.Serializable;

/**
 * A standard generic Pair<X,Y>, with getters, hashCode, equals, and toString well implemented.
 * Courtesy of Mirko Viroli.
 * @param <X> The first type of variable
 * @param <Y> The second type of variable
 */
public class Pair<X, Y> implements Serializable {

    private static final long serialVersionUID = -4572592818687931281L;
    private final X x;
    private final Y y;

    /**
     * Constructor.
     * @param x First param
     * @param y Second param
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x.
     * @return The x
     */
    public X getX() {
        return x;
    }

    /**
     * Returns the y.
     * @return The y
     */
    public Y getY() {
        return y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    @SuppressWarnings("rawtypes")
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
        final Pair other = (Pair) obj;
        if (x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!x.equals(other.x)) {
            return false;
        }
        if (y == null) {
            if (other.y != null) {
                return false;
            }
        } else if (!y.equals(other.y)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }



}
