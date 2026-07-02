package snakerunner.commons;

/**
 * A generic class representing a point in 2D space with coordinates of type X and Y.
 * 
 * @param <X> The type of the X-coordinate.
 * @param <Y> The type of the Y-coordinate.
 */
public class Point2D<X, Y> {
    private X x;
    private Y y;

    /**
     * Constructs a new Point2D with the specified X and Y coordinates.
     * 
     * @param x The X-coordinate.
     * @param y The Y-coordinate.
     */
    public Point2D(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the X-coordinate of this point.
     * 
     * @return The X-coordinate.
     */
    public X getX() {
        return x;
    }

    /**
     * Gets the Y-coordinate of this point.
     * 
     * @return The Y-coordinate.
     */
    public Y getY() {
        return y;
    }

    /**
     * Sets the X-coordinate of this point.
     * 
     * @param x The new X-coordinate.
     */
    public void setX(final X x) {
        this.x = x;
    }

    /**
     * Sets the Y-coordinate of this point.
     * 
     * @param y The new Y-coordinate.
     */
    public void setY(final Y y) {
        this.y = y;
    }

    /**
     * Sets the X and Y coordinates of this point.
     * 
     * @param newX The new X-coordinate.
     * @param newY The new Y-coordinate.
     */
    public void set(final X newX, final Y newY) {
        this.x = newX;
        this.y = newY;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * 
     * @param obj The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @SuppressWarnings("unchecked")
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

        final Point2D<X, Y> other = (Point2D<X, Y>) obj;
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
     * Returns a hash code value for the object.
     * 
     * @return A hash code value for this object.
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
     * Returns a string representation of this point.
     * 
     * @return A string representation of this point.
     */
    @Override
    public String toString() {
        return "Point2D [x=" + x + ", y=" + y + "]";
    }

    /**
     * Creates a copy of this point.
     * 
     * @return A new Point2D object with the same coordinates as this point.
     */
    public Point2D<X, Y> copy() {
        return new Point2D<>(this.getX(), this.getY());
    }

}
