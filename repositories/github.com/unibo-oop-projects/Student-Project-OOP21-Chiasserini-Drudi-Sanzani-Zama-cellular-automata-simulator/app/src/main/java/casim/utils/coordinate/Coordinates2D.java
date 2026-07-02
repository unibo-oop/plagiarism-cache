package casim.utils.coordinate;


import org.apache.commons.lang3.tuple.Pair;

/**
 * Represents a 2D Coordinate.
 *
 * @param <T> the type of the coordinate system.
 */
public class Coordinates2D<T extends Number> implements Coordinates<T> {

    private final Pair<T, T> coords;

    Coordinates2D(final T x, final T y) {
        this.coords = Pair.of(x, y);
    }

    /**
     * Get the X coordinate value.
     * 
     * @return the X coordinate value.
     */
    public T getX() {
        return this.coords.getLeft();
    }

    /**
     * Get the Y coordinate value.
     * 
     * @return the Y coordinate value.
     */
    public T getY() {
        return this.coords.getRight();
    }

    @Override
    public String toString() {
        return "X: " + this.getX() + ", Y: " + this.getY();
    }


    @Override
    public int hashCode() {
        return this.coords.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Coordinates2D)) {
            return false;
        }
        final Coordinates2D<?> other = (Coordinates2D<?>) obj;
        return this.coords.equals(other.coords);
    }

}
