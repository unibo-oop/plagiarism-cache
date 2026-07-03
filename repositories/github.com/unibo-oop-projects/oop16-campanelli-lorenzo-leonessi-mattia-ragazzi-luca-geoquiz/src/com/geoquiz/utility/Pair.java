package com.geoquiz.utility;

import java.io.Serializable;

/**
 * A standard generic Pair<X,Y>.
 * 
 * @param <X>
 *              first element of pair.
 * @param <Y>
 *              second element of pair.
 */
public class Pair<X extends Serializable, Y extends Serializable> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6610839650665030894L;
    private final X x;
    private final Y y;

    /**
     * @param x
     *            element x of pair.
     * @param y
     *            element y of pair.
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * @return the element x of pair.
     */
    public X getX() {
        return x;
    }

    /**
     * @return the element y of pair.
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
