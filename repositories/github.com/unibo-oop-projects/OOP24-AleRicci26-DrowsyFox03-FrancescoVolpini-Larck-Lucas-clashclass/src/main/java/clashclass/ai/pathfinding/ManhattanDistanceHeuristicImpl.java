package clashclass.ai.pathfinding;

import clashclass.commons.Vector2D;

/**
 * Represents a DistanceHeuristic implementation which uses the Manhattan Distance.
 * This heuristic gives an approximation of the distance between two vectors in space.
 * <a href="https://algodaily.com/lessons/what-is-the-manhattan-distance"/>
 */
public class ManhattanDistanceHeuristicImpl implements DistanceHeuristic {
    /**
     * {@inheritDoc}
     */
    @Override
    public float calculateDistance(final Vector2D start, final Vector2D end) {
        return (float) (Math.abs(end.x() - start.x()) + Math.abs(end.y() - start.y()));
    }
}
