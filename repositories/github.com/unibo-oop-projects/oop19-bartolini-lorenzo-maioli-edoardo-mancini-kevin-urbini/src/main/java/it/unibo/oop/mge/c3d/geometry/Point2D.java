package it.unibo.oop.mge.c3d.geometry;

import java.util.function.Function;

/**
 * 
 * A point in 2D space.
 *
 */
public interface Point2D {

    /**
     * 
     * @param x the x component
     * @param y the y component
     * @return a new Point2D
     */
    static Point2D fromDoubles(final double x, final double y) {
        return new Point2DImpl(x, y);
    }

    /**
     * 
     * @return a new Point2D, with each component set as zero
     */
    static Point2D origin() {
        return fromDoubles(0, 0);
    }

    /**
     * 
     * @return the value of the X component
     */
    double getX();

    /**
     * 
     * @return the value of the Y component
     */
    double getY();

    /**
     * 
     * @param transformation the transformation to apply to each component
     * @return a new Point2D, with the transformation applied
     */
    Point2D transformed(Function<Double, Double> transformation);

}
