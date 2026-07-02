package it.unibo.oop.mge.c3d.geometry;

import java.util.function.Function;

/**
 * 
 * A point in 3D space.
 *
 */
public interface Point3D {
    /**
     * 
     * @param x the x component
     * @param y the y component
     * @param z the z component
     * @return a new Point3D
     */
    static Point3D fromDoubles(final double x, final double y, final double z) {
        return new Point3DImpl(x, y, z);
    }

    /**
     * 
     * @return a new Point3D, with each component set as zero
     */
    static Point3D origin() {
        return new Point3DImpl(0, 0, 0);
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
     * @return the value of the Z component
     */
    double getZ();

    /**
     * 
     * @param angleXY the angle in the axis XY to apply to each component
     * @param angleYZ the angle in the axis YZ to apply to each component
     * @return a new Point3D, with the rotation applied
     */
    Point3D rotated(double angleXY, double angleYZ);

    /**
     * 
     * @param transformation the transformation to apply to each component
     * @return a new Point3D, with the transformation applied
     */
    Point3D transformed(Function<Double, Double> transformation);

    /**
     * 
     * @param x the translation in the x axis
     * @param y the translation in the y axis
     * @param z the translation in the z axis
     * @return a new Point3D, with the transformation applied
     */
    Point3D translated(double x, double y, double z);

    /**
     * 
     * @param vector the vector to translate the point with
     * @return a new Point3D, with the transformation applied
     */
    Point3D translated(Point3D vector);

}
