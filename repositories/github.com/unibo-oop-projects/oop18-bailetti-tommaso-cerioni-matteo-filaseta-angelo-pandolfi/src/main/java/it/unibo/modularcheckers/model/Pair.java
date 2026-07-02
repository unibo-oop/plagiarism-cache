package it.unibo.modularcheckers.model;

import java.io.Serializable;

/**
 * Pair class.
 *
 * @param <X>
 * @param <Y>
 */
public class Pair<X, Y> implements Serializable {

    private static final long serialVersionUID = 5395359100790462088L;
    private X x;
    private Y y;

    /**
     * Pair default constructor.
     * @param x First element
     * @param y Second element
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * get pair x element.
     * @return x element
     */
    public X getX() {
        return x;
    }

    /**
     * get pair y element.
     * @return pair y element
     */
    public Y getY() {
        return y;
    }

    /**
     * @param x the x to set
     */
    public void setX(final X x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(final Y y) {
        this.y = y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
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
        if (!(obj instanceof Pair)) {
            return false;
        }

        final Pair<?, ?> other = (Pair<?, ?>) obj;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + x + ";" + y + "]";
    }
}
