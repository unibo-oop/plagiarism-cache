package clashclass.ai.pathfinding;

import clashclass.commons.Vector2D;

/**
 * Represents a DistanceHeuristic implementation which uses the Euclidean Distance.
 * This heuristic represents the exact distance between two vectors in space.
 */
public class EuclideanDistanceHeuristicImpl implements DistanceHeuristic {
    /**
     * {@inheritDoc}
     */
    @Override
    public float calculateDistance(final Vector2D start, final Vector2D end) {
        return (float) start.distance(end);
    }
}
