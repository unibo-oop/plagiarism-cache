package model;

import java.io.Serializable;

/**
 * 
 * @param <X>
 * @param <Y>
 */
public abstract class Pair<X, Y> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 80503258853067887L;
    private volatile X x;
    private volatile Y y;

    /**
     * 
     * @param first
     *            is the first element
     */
    public Pair(final X first) {
        this.x = first;
    }

    /**
     * 
     * @return first element
     */
    public X getFirst() {
        return this.x;
    }

    /**
     * 
     * @return second element
     */
    public Y getSecond() {
        return this.y;
    }

    /**
     * 
     * @param newY
     *            is the new second element
     */
    public void setSecond(final Y newY) {
        this.y = newY;
    }

    /**
     * 
     * @param compareX
     *            is the element to compare at the first element of this pair
     * @return true if the element is equql to this first element.
     */
    public boolean contains(final X compareX) {
        return this.x.equals(compareX);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        return result;
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
        @SuppressWarnings("unchecked")
        Pair<X, Y> other = (Pair<X, Y>) obj;
        if (x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!x.equals(other.x)) {
            return false;
        }
        return true;
    }

}
