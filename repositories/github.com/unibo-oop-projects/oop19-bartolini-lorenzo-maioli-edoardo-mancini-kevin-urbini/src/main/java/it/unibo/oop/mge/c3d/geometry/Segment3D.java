package it.unibo.oop.mge.c3d.geometry;

import java.awt.Color;
import java.util.function.Function;

/**
 * 
 * A segment in 3D space, drawn between two points and having a color.
 *
 */
public interface Segment3D {
    /**
     * 
     * @param a the first point of the new segment
     * @param b the second point of the new segment
     * @return a new Segment3D, with the color black
     */
    static Segment3D fromPoints(final Point3D a, final Point3D b) {
        return fromPoints(a, b, Color.BLACK);
    }

    /**
     * 
     * @param a     the first point of the new segment
     * @param b     the second point of the new segment
     * @param color the color of the new segment
     * @return a new Segment3D, with the provided color
     */
    static Segment3D fromPoints(final Point3D a, final Point3D b, Color color) {
        return new Segment3DImpl(a, b, color);
    }

    /**
     * 
     * @return the first point
     */
    Point3D getA();

    /**
     * 
     * @return the second point
     */
    Point3D getB();

    /**
     * 
     * @return the color
     */
    Color getColor();

    /**
     * 
     * @param angleXY the angle on the XY axis
     * @param angleYZ the angle in the YZ axis
     * @return a new Segment3D, rotated
     */
    Segment3D rotated(double angleXY, double angleYZ);

    /**
     * 
     * @param transformation the transformation to apply to each point
     * @return a new Segment3D, with the transformation applied
     */
    Segment3D transformed(Function<Double, Double> transformation);

    /**
     * 
     * @param x the translation on the x axis
     * @param y the translation on the y axis
     * @param z the translation on the z axis
     * @return a new segment, translated
     */
    Segment3D translated(double x, double y, double z);

    /**
     * 
     * @param vector the vector to apply to the segment
     * @return a new segment, translated
     */
    Segment3D translated(Point3D vector);

}
