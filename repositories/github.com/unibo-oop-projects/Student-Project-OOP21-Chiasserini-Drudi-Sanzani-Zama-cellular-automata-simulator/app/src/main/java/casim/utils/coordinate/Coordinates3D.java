package casim.utils.coordinate;

import java.util.Objects;

import org.apache.commons.lang3.tuple.Triple;

/**
 * Represents a 3D Coordinate.
 *
 * @param <T> the type of the coordinate system.
 */
public class Coordinates3D<T extends Number> implements Coordinates<T> {

    private final Triple<T, T, T> coords;

    Coordinates3D(final T x, final T y, final T z) {
        this.coords = Triple.of(x, y, z);
    }

    /**
     * Get the X coordinate value.
     * @return the X coordinate value.
     */
    public T getX() {
        return this.coords.getLeft();
    }

    /**
     * Get the Y coordinate value.
     * @return the Y coordinate value.
     */
    public T getY() {
        return this.coords.getMiddle();
    }

    /**
     * Get the Z coordinate value.
     * @return the Z coordinate value.
     */
    public T getZ() {
        return this.coords.getRight();
    }

    @Override
    public String toString() {
        return "X: " + this.getX() + ", Y: " + this.getY() + ", Z: " + this.getZ();
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
        if (!(obj instanceof Coordinates3D)) {
            return false;
        }
        final Coordinates3D<?> other = (Coordinates3D<?>) obj;
        return Objects.equals(coords, other.coords);
    }

}
