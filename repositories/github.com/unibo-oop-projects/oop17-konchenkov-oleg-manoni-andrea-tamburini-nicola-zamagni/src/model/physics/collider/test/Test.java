package model.physics.collider.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.physics.collider.Collider2DImpl;

/**
 *
 * Tests Collider2D.
 *
 * @author Nicola Zamagni
 *
 */
public class Test {

    private static final double EPSILON = 10e-12;

    /**
     *
     * the path intersect the collider.
     *
     */
    @org.junit.Test
    public void intersect() {

        final Vector2D colliderFirstPoint = new Vector2D(0.0, 0.0);
        final Vector2D colliderSecondPoint = new Vector2D(5.0, 5.0);
        final Vector2D pathStartPoint = new Vector2D(0.0, 5.0);
        final Vector2D pathEndPoint = new Vector2D(5.0, 0.0);
        final Vector2D velocityAtPathEndPoint = new Vector2D(0.0, -1.0);
        final Vector2D intersectionPoint = new Vector2D(2.5, 2.5);

        final Collider2DImpl collider = new Collider2DImpl(colliderFirstPoint, colliderSecondPoint);
        assertTrue(collider.isIntersected(pathStartPoint, pathEndPoint, velocityAtPathEndPoint));
        assertTrue(collider.getIntersectionPoint(pathStartPoint, pathEndPoint, velocityAtPathEndPoint).isPresent());
        assertEquals(collider.getIntersectionPoint(pathStartPoint, pathEndPoint, velocityAtPathEndPoint).get(),
                intersectionPoint);
    }

    /**
     *
     * the path do NOT intersect the collider.
     *
     */
    @org.junit.Test
    public void doNotIntersect() {

        final Vector2D colliderFirstPoint = new Vector2D(3.0, 0.0);
        final Vector2D colliderSecondPoint = new Vector2D(3.0, 4.0);
        final Vector2D pathStartPoint = new Vector2D(0.0, 5.0);
        final Vector2D pathEndPoint = new Vector2D(5.0, 5.0);
        final Vector2D velocityAtPathEndPoint = new Vector2D(0.0, -1.0);

        final Collider2DImpl collider = new Collider2DImpl(colliderFirstPoint, colliderSecondPoint);
        assertFalse(collider.isIntersected(pathStartPoint, pathEndPoint, velocityAtPathEndPoint));
        assertFalse(collider.getIntersectionPoint(pathStartPoint, pathEndPoint, velocityAtPathEndPoint).isPresent());
    }

    /**
     *
     * the path and the collider are collinear and overlapping.
     *
     */
    @org.junit.Test
    public void collinearAndOverlapping() {

        final Vector2D colliderFirstPoint = new Vector2D(0.0, 0.0);
        final Vector2D colliderSecondPoint = new Vector2D(2.0, 0.0);
        final Vector2D pathStartPoint = new Vector2D(1.0, 0.0);
        final Vector2D pathEndPoint = new Vector2D(3.0, 0.0);
        final Vector2D velocityAtPathEndPoint = new Vector2D(0.0, -1.0);
        final Vector2D intersectionPoint = new Vector2D(1.0, 0.0);

        final Collider2DImpl collider = new Collider2DImpl(colliderFirstPoint, colliderSecondPoint);
        assertTrue(collider.isIntersected(pathStartPoint, pathEndPoint, velocityAtPathEndPoint));
        assertTrue(collider.getIntersectionPoint(pathStartPoint, pathEndPoint, velocityAtPathEndPoint).isPresent());
        assertEquals(collider.getIntersectionPoint(pathStartPoint, pathEndPoint, velocityAtPathEndPoint).get(),
                intersectionPoint);

    }

    /**
     *
     * the path and the collider are collinear and with one point in common.
     *
     */
    @org.junit.Test
    public void collinearAndWithOnePointInCommon() {

        final Vector2D colliderFirstPoint = new Vector2D(0.0, 0.0);
        final Vector2D colliderSecondPoint = new Vector2D(2.0, 0.0);
        final Vector2D pathStartPoint = new Vector2D(2.0, 0.0);
        final Vector2D pathEndPoint = new Vector2D(4.0, 0.0);
        final Vector2D velocityAtPathEndPoint = new Vector2D(0.0, -1.0);
        final Vector2D intersectionPoint = new Vector2D(2.0, 0.0);

        final Collider2DImpl collider = new Collider2DImpl(colliderFirstPoint, colliderSecondPoint);
        assertTrue(collider.isIntersected(pathStartPoint, pathEndPoint, velocityAtPathEndPoint));
        assertTrue(collider.getIntersectionPoint(pathStartPoint, pathEndPoint, velocityAtPathEndPoint).isPresent());
        assertEquals(collider.getIntersectionPoint(pathStartPoint, pathEndPoint, velocityAtPathEndPoint).get(),
                intersectionPoint);

    }

    /**
     *
     * the path and the collider are collinear and NOT overlapping.
     *
     */
    @org.junit.Test
    public void collinearAndNotOverlapping() {

        final Vector2D colliderFirstPoint = new Vector2D(0.0, 0.0);
        final Vector2D colliderSecondPoint = new Vector2D(2.0, 0.0);
        final Vector2D pathStartPoint = new Vector2D(3.0, 0.0);
        final Vector2D pathEndPoint = new Vector2D(5.0, 0.0);
        final Vector2D velocityAtPathEndPoint = new Vector2D(0.0, -1.0);

        final Collider2DImpl collider = new Collider2DImpl(colliderFirstPoint, colliderSecondPoint);
        assertFalse(collider.isIntersected(pathStartPoint, pathEndPoint, velocityAtPathEndPoint));
        assertFalse(collider.getIntersectionPoint(pathStartPoint, pathEndPoint, velocityAtPathEndPoint).isPresent());
    }

    /**
     *
     * the path and the collider are with one end point in common.
     *
     */
    @org.junit.Test
    public void oneEndPointInCommon() {

        final Vector2D colliderFirstPoint = new Vector2D(0.0, 0.0);
        final Vector2D colliderSecondPoint = new Vector2D(2.0, 0.0);
        final Vector2D pathStartPoint = new Vector2D(0.0, 0.0);
        final Vector2D pathEndPoint = new Vector2D(3.0, 3.0);
        final Vector2D velocityAtPathEndPoint = new Vector2D(0.0, -1.0);
        final Vector2D intersectionPoint = new Vector2D(0.0, 0.0);

        final Collider2DImpl collider = new Collider2DImpl(colliderFirstPoint, colliderSecondPoint);
        assertTrue(collider.isIntersected(pathStartPoint, pathEndPoint, velocityAtPathEndPoint));
        assertTrue(collider.getIntersectionPoint(pathStartPoint, pathEndPoint, velocityAtPathEndPoint).isPresent());
        assertEquals(collider.getIntersectionPoint(pathStartPoint, pathEndPoint, velocityAtPathEndPoint).get(),
                intersectionPoint);
    }

    /**
     *
     * pathStartPoint and PathEndPoint are coincident, velocity is pointing away
     * from the collider.
     *
     */
    @org.junit.Test
    public void pathStartPointPathEndPointCoincidentVelocityAway() {

        final Vector2D colliderFirstPoint = new Vector2D(0.0, 0.0);
        final Vector2D colliderSecondPoint = new Vector2D(2.0, 0.0);
        final Vector2D pathStartPoint = new Vector2D(1.0, 0.0);
        final Vector2D pathEndPoint = new Vector2D(1.0, 0.0);
        final Vector2D velocityAtPathEndPoint = new Vector2D(0.0, -5.0);
        final Vector2D intersectionPoint = new Vector2D(1.0, 0.0);

        final Collider2DImpl collider = new Collider2DImpl(colliderFirstPoint, colliderSecondPoint);
        assertTrue(collider.isIntersected(pathStartPoint, pathEndPoint, velocityAtPathEndPoint));
        assertTrue(collider.getIntersectionPoint(pathStartPoint, pathEndPoint, velocityAtPathEndPoint).isPresent());
        assertEquals(collider.getIntersectionPoint(pathStartPoint, pathEndPoint, velocityAtPathEndPoint).get(),
                intersectionPoint);
    }

    /**
     *
     * pathStartPoint and PathEndPoint are coincident, velocity is pointing toward
     * the collider.
     *
     */
    @org.junit.Test
    public void pathStartPointPathEndPointCoincidentVelocityToward() {

        final Vector2D colliderFirstPoint = new Vector2D(0.0, 0.0);
        final Vector2D colliderSecondPoint = new Vector2D(2.0, 0.0);
        final Vector2D pathStartPoint = new Vector2D(1.0, 0.0);
        final Vector2D pathEndPoint = new Vector2D(1.0, 0.0);
        final Vector2D velocityAtPathEndPoint = new Vector2D(0.0, 5.0);

        final Collider2DImpl collider = new Collider2DImpl(colliderFirstPoint, colliderSecondPoint);
        assertFalse(collider.isIntersected(pathStartPoint, pathEndPoint, velocityAtPathEndPoint));
        assertFalse(collider.getIntersectionPoint(pathStartPoint, pathEndPoint, velocityAtPathEndPoint).isPresent());

    }

    /**
     *
     * Positive distance from point.
     *
     */
    @org.junit.Test
    public void positiveDistanceFromPoint() {

        final Vector2D colliderFirstPoint = new Vector2D(0, 0);
        final Vector2D colliderSecondPoint = new Vector2D(5, 5);
        final Vector2D point = new Vector2D(0, 5);
        final double distanceFromPoint = 5 * Math.sqrt(2) / 2;

        final Collider2DImpl collider = new Collider2DImpl(colliderFirstPoint, colliderSecondPoint);
        assertEquals(collider.getDistanceFromPoint(point), distanceFromPoint, EPSILON);

    }

    /**
     *
     * Zero distance from point.
     *
     */
    @org.junit.Test
    public void zeroDistanceFromPoint() {

        final Vector2D colliderFirstPoint = new Vector2D(0, 0);
        final Vector2D colliderSecondPoint = new Vector2D(5, 5);
        final Vector2D point = new Vector2D(2.5, 2.5);

        final Collider2DImpl collider = new Collider2DImpl(colliderFirstPoint, colliderSecondPoint);
        assertEquals(collider.getDistanceFromPoint(point), 0.0, EPSILON);

    }

    /**
     *
     * Negative distance from point.
     *
     */
    @org.junit.Test
    public void negativeDistanceFromPoint() {

        final Vector2D colliderFirstPoint = new Vector2D(0, 0);
        final Vector2D colliderSecondPoint = new Vector2D(5, 5);
        final Vector2D point = new Vector2D(5, 0);
        final double distanceFromPoint = -5 * Math.sqrt(2) / 2;

        final Collider2DImpl collider = new Collider2DImpl(colliderFirstPoint, colliderSecondPoint);
        assertEquals(collider.getDistanceFromPoint(point), distanceFromPoint, EPSILON);

    }

}
