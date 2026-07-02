package talisman.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a pair of non-homogeneous items.
 * 
 * @author Alberto Arduini
 *
 * @param <X> type of the first item
 * @param <Y> type of the second item
 */
public class Pair<X, Y> implements Serializable {
    private static final long serialVersionUID = 352634681550592861L;

    private final X x;
    private final Y y;

    /**
     * Creates a new pair.
     * 
     * @param x the first item
     * @param y the second item
     */
    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the first item.
     * 
     * @return the item
     */
    public final X getX() {
        return x;
    }

    /**
     * Gets the second item.
     * 
     * @return the item
     */
    public final Y getY() {
        return y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
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
        final Pair<?, ?> other = (Pair<?, ?>) obj;
        return Objects.equals(x, other.x) && Objects.equals(y, other.y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }
}
