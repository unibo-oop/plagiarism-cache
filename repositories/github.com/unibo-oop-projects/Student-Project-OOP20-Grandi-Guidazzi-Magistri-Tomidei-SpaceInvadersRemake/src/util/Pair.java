package util;

import java.util.Objects;

/**
 * A standard generic Pair<X,Y>, with severals functions well implemented.
 * 
 * @param <X> first {@link Class} type
 * @param <Y> second {@link Class} type
 */
public class Pair<X, Y> {

    private X x;
    private Y y;

    /**
     * Creates the pair.
     * 
     * @param x first element
     * @param y second element
     */
    public Pair(final X x, final Y y) {
        this.x = Objects.requireNonNull(x);
        this.y = Objects.requireNonNull(y);

    }

    /**
     * @return the x
     */
    public X getX() {
        return this.x;
    }

    /**
     * @return the y
     */
    public Y getY() {
        return this.y;
    }

    /**
     * Set new value for x.
     * 
     * @param x the new value
     * @return the old value
     */
    public X setX(final X x) {
        final X prev = this.x;
        this.x = x;
        return prev;
    }

    /**
     * Set new value for y.
     * 
     * @param y the new value
     * @return the old value
     */
    public Y setY(final Y y) {
        final Y prev = this.y;
        this.y = y;
        return prev;
    }

    /**
     * Set both values for x and y.
     * 
     * @param x new value for x
     * @param y new value for y
     * @return a new Pair with old values
     */
    public Pair<X, Y> setBoth(final X x, final Y y) {
        final Pair<X, Y> prev = new Pair<>(this.x, this.y);
        this.x = x;
        this.y = y;
        return prev;
    }

    /**
     * Set the values of the pair equals of the other Pair.
     * 
     * @param other Pair from witch taking the values.
     * @return a new Pair with old values
     */
    public Pair<X, Y> setByPair(final Pair<X, Y> other) {
        final Pair<X, Y> prev = new Pair<>(this.x, this.y);
        this.x = other.getX();
        this.y = other.getY();
        return prev;
    }

    /**
     * Clone the Pair.
     * 
     * @return the cloned Pair
     */
    public Pair<X, Y> clone() {
        return new Pair<>(this.x, this.y);
    }

    /**
     * Creates a new Pair with swapped components.
     * 
     * @return the swapped new Pair
     */
    public Pair<Y, X> getSwapped() {
        return new Pair<>(this.y, this.x);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Pair [x = " + this.x + ", y = " + this.y + "]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.x == null) ? 0 : this.x.hashCode());
        result = prime * result + ((this.y == null) ? 0 : this.y.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
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
        final Pair<?, ?> other = (Pair<?, ?>) obj;
        if (this.x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!this.x.equals(other.x)) {
            return false;
        }
        if (this.y == null) {
            if (other.y != null) {
                return false;
            }
        } else if (!this.y.equals(other.y)) {
            return false;
        }
        return true;
    }

}
