package model.physics.collider;

import java.util.Optional;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 *
 * Represent an immovable object, a barrier. It is a line that can not be
 * crossed.
 *
 * @author Nicola Zamagni
 *
 */
public interface Collider2D {
    /**
     *
     * Returns the coordinates of the first point of the collider.
     *
     * @return the coordinates of the first point of the collider
     */
    Vector2D getFirstPoint();

    /**
     *
     * Returns the coordinates of the second point of the collider.
     *
     * @return the coordinates of the second point of the collider
     */
    Vector2D getSecondPoint();

    /**
     *
     * Returns the normal vector of the collider.
     *
     * @return normal vector of the collider
     */
    Vector2D getNormal();

    /**
     * * Returns if the collider is intersected by the rectilinear path of an
     * object.
     *
     * @param pathStartPoint
     *            coordinates of the start point of the path of the object
     * @param pathEndPoint
     *            coordinates of the end point of the path of the object
     * @param velocityAtPathEndPoint
     *            the velocity at the end point of the path of the object
     * @return if the collider is intersected by the path of the object
     */
    boolean isIntersected(Vector2D pathStartPoint, Vector2D pathEndPoint, Vector2D velocityAtPathEndPoint);

    /**
     *
     * Returns the point of intersection of the rectilinear path of an object with
     * the collider.
     *
     * @param pathStartPoint
     *            coordinates of the start point of the path of the object
     * @param pathEndPoint
     *            coordinates of the end point of the path of the object
     * @param velocityAtPathEndPoint
     *            the velocity at the end point of the path of the object
     * @return the point of intersection (if exist) of the path of an object with
     *         the collider
     */
    Optional<Vector2D> getIntersectionPoint(Vector2D pathStartPoint, Vector2D pathEndPoint,
            Vector2D velocityAtPathEndPoint);

    /**
     *
     * Returns the (signed) distance of the collider from an object.
     *
     * @param position
     *            the position of the object
     * @return the (signed) distance of the collider from an object.
     */
    double getDistanceFromPoint(Vector2D position);

    /**
     *
     * Returns the difference between, the sum of the object distances from the
     * endpoints of the collider, and the length of the collider.
     *
     * @param position
     *            the position of the object
     * @return the difference between, the sum of the object distances from the
     *         endpoints of the collider, and the length of the collider
     */
    double getTriangleInequality(Vector2D position);

}
