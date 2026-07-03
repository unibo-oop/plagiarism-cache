package oop.lit.util;

import java.io.Serializable;

/**
 * A bidimensional vector.
 */
public interface Vector2D extends Serializable {

    /**
     * @return the x coordinate of this vector.
     */
    double getX();

    /**
     * @return the x coordinate of this vector.
     */
    double getY();

    /**
     * @return the vector inverted.
     */
    Vector2D invert();

    /**
     * @param origin
     *            the new origin of the system.
     * @return the vector inverted.
     */
    Vector2D invertWithDifferentOrigin(Vector2D origin);

    /**
     * @param v
     *            the second vector.
     * @return a new vector, result of the addition between the argument and
     *         this vector.
     */
    Vector2D add(Vector2D v);

    /**
     * @param v
     *            the second vector.
     * @return a new vector, result of the subtraction between this vector and
     *         the argument.
     */
    Vector2D sub(Vector2D v);

    /**
     * @param scalar
     *            the scalar, must be greater than 0.
     * @return a new vector, result of scaling this by the given scalar.
     * @throws IllegalArgumentException
     *             if the scalar isn't greater than 0.
     */
    Vector2D scale(double scalar);

    /**
     * @param a
     *            first point.
     * @return the distance between the two points.
     */
    double getDistance(Vector2D a);

    /**
     * @param origin
     *            starting point.
     * @param destination
     *            final point.
     * @return true if clockwise, false the opposite.
     */
    Boolean isPositiveAngle(Vector2D origin, Vector2D destination);

    /**
     * @param origin
     *            the starting point.
     * @param destination
     *            the final point.
     * @return the angle between the the two segment (this point - origin, this
     *         point - destination).
     */
    double getAngle(Vector2D origin, Vector2D destination);

    /**
     * @param r
     *            origin point of the local reference system.
     * @param theta
     *            the angle of the rect.
     * @return the distance between a Vector2D and a rect.
     */
    double getPointRectDistance(Vector2D r, double theta);

    /**
     * 
     * @param point
     *            the point witch I want to know if it's near to this.
     * @return if the point is near.
     */
    boolean isNearby(Vector2D point);

    /**
     * 
     * @param point
     *            the point witch I want to know if it's near to this.
     * @return if the point is very near.
     */
    boolean isVeryNearby(Vector2D point);
}
