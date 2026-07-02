package model.physics.collider;

import java.util.Optional;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 *
 * Represent an immovable object, a barrier. It is a line that can not be
 * crossed.
 *
 * Instances of this class are guaranteed to be immutable.
 *
 * @author Nicola Zamagni
 *
 */
public final class Collider2DImpl implements Collider2D {

    private static final double EPSILON = 10.0 * Double.MIN_VALUE;

    private final Vector2D firstPoint;
    private final Vector2D secondPoint;

    /**
     *
     * Constructor.
     *
     * @param firstPoint
     *            coordinates of the start point of the collider
     * @param secondPoint
     *            coordinates of the end point of the collider
     */
    public Collider2DImpl(final Vector2D firstPoint, final Vector2D secondPoint) {
        super();
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (firstPoint == null ? 0 : firstPoint.hashCode());
        result = prime * result + (secondPoint == null ? 0 : secondPoint.hashCode());
        return result;
    }

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
        final Collider2DImpl other = (Collider2DImpl) obj;
        if (firstPoint == null) {
            if (other.firstPoint != null) {
                return false;
            }
        } else if (!firstPoint.equals(other.firstPoint)) {
            return false;
        }
        if (secondPoint == null) {
            if (other.secondPoint != null) {
                return false;
            }
        } else if (!secondPoint.equals(other.secondPoint)) {
            return false;
        }
        return true;
    }

    @Override
    public Vector2D getFirstPoint() {
        return firstPoint;
    }

    @Override
    public Vector2D getSecondPoint() {
        return secondPoint;
    }

    @Override
    public Vector2D getNormal() {
        return new Vector2D(firstPoint.getY() - secondPoint.getY(), secondPoint.getX() - firstPoint.getX()).normalize();
    }

    /**
     *
     * Finds the orientation of an ordered triplet of points p0-p1-p2.
     *
     * @param p0
     * @param p1
     * @param p2
     * @return 0 collienar, 1 clockwise, -1 ounterclockwise
     */
    private int direction(final Vector2D p0, final Vector2D p1, final Vector2D p2) {
        final double val = (p1.getX() - p0.getX()) * (p2.getY() - p0.getY())
                - (p2.getX() - p0.getX()) * (p1.getY() - p0.getY());
        return Math.abs(val) < EPSILON ? 0 : val > 0 ? 1 : -1;
    }

    /**
     *
     * Checks if point p0 lies on segment p1-p2.
     *
     * @param p0
     *            a point
     * @param p1
     *            start point of the segment
     * @param p2
     *            end point of the segment
     * @return if point p0 lies on segment p1-p2
     */
    private boolean onSegment(final Vector2D p0, final Vector2D p1, final Vector2D p2) {
        return p0.getX() <= Math.max(p1.getX(), p2.getX()) && p0.getX() >= Math.min(p1.getX(), p2.getX())
                && p0.getY() <= Math.max(p1.getY(), p2.getY()) && p0.getY() >= Math.min(p1.getY(), p2.getY()) ? true
                        : false;
    }

    /*
     * Introduction to Algorithms
     * By Thomas H. Cormen, Charles E. Leiserson and Ronald L. Rivest
     */
    @Override
    public boolean isIntersected(final Vector2D pathStartPoint, final Vector2D pathEndPoint,
            final Vector2D velocityAtPathEndPoint) {
        /*
         * pathStartPoint lies on the collider, velocity is pointing away from collider
         */
        if (onSegment(pathStartPoint, firstPoint, secondPoint) && getDistanceFromPoint(pathEndPoint) >= 0
                && velocityAtPathEndPoint.dotProduct(getNormal()) >= 0) {
            return false;
        }
        /*
         * pathStartPoint and pathEndPoint are coincident and both lie on the collider,
         * velocity is pointing toward the collider
         */
        if (pathStartPoint.equals(pathEndPoint) && onSegment(pathStartPoint, firstPoint, secondPoint)
                && velocityAtPathEndPoint.dotProduct(getNormal()) < 0) {
            return true;
        }
        final int d1 = direction(secondPoint, firstPoint, pathStartPoint);
        final int d2 = direction(secondPoint, firstPoint, pathEndPoint);
        final int d3 = direction(pathEndPoint, pathStartPoint, firstPoint);
        final int d4 = direction(pathEndPoint, pathStartPoint, secondPoint);
        /* General case */
        if (d1 != d2 && d3 != d4) {
            return true;
        }
        /*
         * firstPoint, secondPoint and pathStartPoint are collinear and pathStartPoint
         * lies on the collider
         */
        if (d1 == 0 && onSegment(pathStartPoint, firstPoint, secondPoint)) {
            return true;
        }
        /*
         * firstPoint, secondPoint and pathEndPoint are collinear and pathEndPoint lies
         * on the collider
         */
        if (d2 == 0 && onSegment(pathEndPoint, firstPoint, secondPoint)) {
            return true;
        }
        /*
         * pathStartPoint, pathEndPoint and firstPoint are collinear and firstPoint lies
         * on the rectilinear path
         */
        if (d3 == 0 && onSegment(firstPoint, pathStartPoint, pathEndPoint)) {
            return true;
        }
        /*
         * pathStartPoint, pathEndPoint and secondPoint are collinear and secondPoint
         * lies on the rectilinear path
         */
        return d4 == 0 && onSegment(secondPoint, pathStartPoint, pathEndPoint);
    }

    @Override
    public Optional<Vector2D> getIntersectionPoint(final Vector2D pathStartPoint, final Vector2D pathEndPoint,
            final Vector2D velocityAtPathEndPoint) {
        /**
         * no intersection
         */
        if (!isIntersected(pathStartPoint, pathEndPoint, velocityAtPathEndPoint)) {
            return Optional.empty();
        }
        /*
         * pathStartPoint and pathEndtPoint are coincident and both lie on the collider
         */
        if (pathStartPoint.equals(pathEndPoint)) {
            return Optional.of(new Vector2D(pathStartPoint.getX(), pathStartPoint.getY()));
        }
        /*
         * pathStartPoint and firstPoint or pathStartPoint and secondPoint are
         * coincident
         */
        if (getFirstPoint().equals(pathStartPoint) || getSecondPoint().equals(pathStartPoint)) {
            return Optional.of(pathStartPoint);
        }
        /*
         * pathEndtPoint and firstPoint or pathEndtPoint and secondPoint are coincident
         */
        if (getFirstPoint().equals(pathEndPoint) || getSecondPoint().equals(pathEndPoint)) {
            return Optional.of(pathEndPoint);
        }
        final double d = (secondPoint.getX() - firstPoint.getX()) * (pathEndPoint.getY() - pathStartPoint.getY())
                - (pathEndPoint.getX() - pathStartPoint.getX()) * (secondPoint.getY() - firstPoint.getY());
        /*
         * the path and the collider are collinear and overlapping
         */
        if (d == 0) {
            return Optional.of(onSegment(pathStartPoint, firstPoint, secondPoint) ? pathStartPoint : pathEndPoint);
        }
        /*
         * General case
         */
        final double t = ((pathStartPoint.getX() - firstPoint.getX()) * (pathEndPoint.getY() - pathStartPoint.getY())
                - (pathEndPoint.getX() - pathStartPoint.getX()) * (pathStartPoint.getY() - firstPoint.getY())) / d;
        return Optional.of(secondPoint.subtract(firstPoint).scalarMultiply(t).add(firstPoint));
    }

    @Override
    public double getDistanceFromPoint(final Vector2D position) {
        return position.subtract(getFirstPoint()).dotProduct(getNormal());
    }

    @Override
    public double getTriangleInequality(final Vector2D position) {
        return position.distance(getFirstPoint()) + position.distance(getSecondPoint())
                - getFirstPoint().distance(getSecondPoint());
    }

}
