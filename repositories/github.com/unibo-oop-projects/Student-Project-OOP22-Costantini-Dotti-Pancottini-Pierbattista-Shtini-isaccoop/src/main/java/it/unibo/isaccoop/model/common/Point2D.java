package it.unibo.isaccoop.model.common;

import java.util.Objects;

/**
 * Point2D class to model a 2d point into the space.
 * */
public class Point2D {

    private final double x;
    private final double y;

    /**
     * Constructor for {@link Point2D}.
     *
     * @param x x coordinate
     * @param y y coordinate
     * */
    public Point2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method to increase the position of the point through a vector.
     *
     * @param v vector object as {@link Vector2D}
     * @return updated {@link Point2D} object
     * */
    public Point2D sum(final Vector2D v) {
        return new Point2D(this.x + v.getX(), this.y + v.getY());
    }

    /**
     * Method to compute the difference between two {@link Point2D}.
     *
     * @param v {@link Point2D} to compare with this
     * @return difference between two points as a {@link Vector2D}
     * */
    public Vector2D sub(final Point2D v) {
        return new Vector2D(this.x - v.x, this.y - v.y);
    }

    /**
     * Get x on this {@link Point2D}.
     *
     * @return x
     * */
    public double getX() {
        return this.x;
    }

    /**
     * Get y on this {@link Point2D}.
     *
     * @return y
     * */
    public double getY() {
        return this.y;
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
        final Point2D other = (Point2D) obj;
        return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
                && Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
    }
}
