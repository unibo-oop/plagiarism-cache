package model.physics.collider;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 *
 * Represent an immovable object, a barrier. It is a polyline that can not be
 * crossed.
 *
 * Instances of this class are guaranteed to be immutable.
 *
 * @author Nicola Zamagni
 *
 */
public final class Polycollider2DImpl implements Polycollider2D {

    private static final double EPSILON = 10.0 * Double.MIN_VALUE;

    private final List<Collider2DImpl> collider2DList;
    private final boolean closed;

    /**
     *
     * Constructor.
     *
     * @param points
     *            sequence of the coordinates of the points of the polycollider
     * @throws IllegalArgumentException
     *             the sequence of the coordinates of the points of the polycollider
     *             must contains at least two points, and two consecutive points
     *             must be not coincident
     */
    public Polycollider2DImpl(final List<Vector2D> points) throws IllegalArgumentException {
        super();
        if (points.size() < 2
                || IntStream.range(0, points.size() - 1).anyMatch(i -> points.get(i).equals(points.get(i + 1)))) {
            throw new IllegalArgumentException();
        }
        collider2DList = IntStream.range(0, points.size() - 1)
                .mapToObj(i -> new Collider2DImpl(points.get(i), points.get(i + 1))).collect(Collectors.toList());
        closed = collider2DList.get(0).getFirstPoint()
                .equals(collider2DList.get(collider2DList.size() - 1).getSecondPoint());
    }

    @Override
    public boolean isIntersected(final Vector2D q1, final Vector2D q2, final Vector2D v2) {
        return collider2DList.stream().anyMatch(c -> c.isIntersected(q1, q2, v2));
    }

    private Collider2D getCollider(final Vector2D position, final Vector2D velocity) {
        final Collider2D nearer = collider2DList.stream()
                .min((c1, c2) -> Double.compare(c1.getTriangleInequality(position), c2.getTriangleInequality(position)))
                .get();
        return collider2DList.stream().filter(c -> c.equals(nearer)).min(
                (c1, c2) -> Double.compare(velocity.dotProduct(c1.getNormal()), velocity.dotProduct(c2.getNormal())))
                .get();
    }

    @Override
    public double getDistanceFromPoint(final Vector2D position, final Vector2D velocity) {
        return getCollider(position, velocity).getDistanceFromPoint(position);
    }

    @Override
    public Vector2D getNormal(final Vector2D position, final Vector2D positionToFix, final Vector2D velocity,
            final Vector2D velocityToFix) {

        final Collider2D collider2D = getCollider(position, velocity);
        final int index = collider2DList.indexOf(collider2D);

        if (collider2D.isIntersected(position, positionToFix, velocityToFix)) {
            /* the hitpoint is on the start point of a collider */
            if (Math.abs(collider2D.getIntersectionPoint(position, positionToFix, velocityToFix).get()
                    .distance(collider2D.getFirstPoint())) < EPSILON
                    && (!collider2DList.get(0).equals(collider2D) || closed)) {

                final int prevIndex = (index - 1) % collider2DList.size() < 0
                        ? (index - 1) % collider2DList.size() + collider2DList.size()
                        : (index - 1) % collider2DList.size();
                return collider2DList.get(prevIndex).getNormal()
                        .add(collider2DList.get(index % collider2DList.size()).getNormal()).normalize();
            }
            /* the hitpoint is on the end point of a collider */
            if (Math.abs(collider2D.getIntersectionPoint(position, positionToFix, velocityToFix).get()
                    .distance(collider2D.getSecondPoint())) < EPSILON
                    && (!collider2DList.get(collider2DList.size() - 1).equals(collider2D) || closed)) {

                final int nextIndex = (index + 1) % collider2DList.size();
                return collider2DList.get(index % collider2DList.size()).getNormal()
                        .add(collider2DList.get(nextIndex).getNormal()).normalize();
            }
        }

        return collider2D.getNormal();
    }

}
