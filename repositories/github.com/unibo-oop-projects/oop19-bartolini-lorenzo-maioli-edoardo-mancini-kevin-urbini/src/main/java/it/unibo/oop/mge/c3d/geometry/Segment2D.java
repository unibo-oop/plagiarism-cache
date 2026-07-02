package it.unibo.oop.mge.c3d.geometry;

import java.awt.Color;
import java.util.function.Function;

/**
 * 
 * A segment in 2D space, drawn between two points and having a color.
 *
 */
public interface Segment2D {

    /**
     * 
     * @param a the first point of the new segment
     * @param b the second point of the new segment
     * @return a new Segment2D, with the color black
     */
    static Segment2D fromPoints(final Point2D a, final Point2D b) {
        return fromPoints(a, b, Color.BLACK);
    }

    /**
     * 
     * @param a     the first point of the new segment
     * @param b     the second point of the new segment
     * @param color the color of the new segment
     * @return a new Segment2D, with the provided color
     */
    static Segment2D fromPoints(final Point2D a, final Point2D b, final Color color) {
        return new Segment2DImpl(a, b, color);
    }

    /**
     * 
     * @return the first point
     */
    Point2D getA();

    /**
     * 
     * @return the second point
     */
    Point2D getB();

    /**
     * 
     * @return the color
     */
    Color getColor();

    /**
     * 
     * @param transformation the transformation to apply to each point
     * @return a new Segment2D, with the transformation applied
     */
    Segment2D transformed(Function<Double, Double> transformation);

}
