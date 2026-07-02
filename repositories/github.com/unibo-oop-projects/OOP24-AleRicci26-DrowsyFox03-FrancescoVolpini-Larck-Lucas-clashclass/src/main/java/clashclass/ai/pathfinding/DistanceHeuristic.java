package clashclass.ai.pathfinding;

import clashclass.commons.Vector2D;

/**
 * Represents a heuristic to calculate the distance between two vectors.
 */
@FunctionalInterface
public interface DistanceHeuristic {
    /**
     * Calculates the distance between two vectors.
     *
     * @param start the first vector
     * @param end the second vector
     *
     * @return the distance between two vectors
     */
    float calculateDistance(Vector2D start, Vector2D end);
}
