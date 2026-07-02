package model.physics.collider;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
*
* Represent an immovable object, a barrier. It is a polyline that can not be
* crossed.
*
* @author Nicola Zamagni
*
*/
public interface Polycollider2D {

   /**
    *
    * Returns if the polycollider is intersected by the rectilinear path of an
    * object.
    *
    * @param pathStartPoint
    *            coordinates of the start point of the path of the object
    * @param pathEndPoint
    *            coordinates of the end point of the path of the object
    * @param velocityAtPathEndPoint
    *            the velocity at the end point of the path of the object
    * @return if the polycollider is intersected by the segment q1-q2
    */
   boolean isIntersected(Vector2D pathStartPoint, Vector2D pathEndPoint, Vector2D velocityAtPathEndPoint);

   /**
    *
    * Returns the distance of the polycollider from an object.
    *
    * @param position
    *            the position of the object
    * @param velocity
    *            the velocity of the object
    * @return the distance of the polycollider from an object
    */
   double getDistanceFromPoint(Vector2D position, Vector2D velocity);

   /**
    *
    * Returns the normal vector of the collider where the object hit the
    * polycollider.
    *
    * @param position
    *            the position of the object at the collision time
    * @param positionToFix
    *            the position of the object to be fixed to account for the
    *            collision
    * @param velocity
    *            the velocity of the object at the collision time
    * @param velocityToFix
    *            the velocity of the object to be fixed to account for the
    *            collision
    * @return the normal vector of the collider where the object hit the
    *         polycollider
    */
   Vector2D getNormal(Vector2D position, Vector2D positionToFix, Vector2D velocity, Vector2D velocityToFix);

}
