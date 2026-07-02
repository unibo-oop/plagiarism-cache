package it.unibo.oop.mge.c3d.geometry;

import java.util.function.Function;

/**
 * 
 * Base implementation of Point2D.
 *
 */
public class Point2DImpl implements Point2D {
    private final double x;
    private final double y;

    // package protected
    Point2DImpl(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point2DImpl other = (Point2DImpl) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        return true;
    }

    @Override
    public final double getX() {
        return x;
    }

    @Override
    public final double getY() {
        return y;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public final String toString() {
        return "Point2DImpl [x=" + x + ", y=" + y + "]";
    }

    @Override
    public final Point2D transformed(final Function<Double, Double> transformation) {
        return new Point2DImpl(transformation.apply(this.x), transformation.apply(this.y));
    }
}
