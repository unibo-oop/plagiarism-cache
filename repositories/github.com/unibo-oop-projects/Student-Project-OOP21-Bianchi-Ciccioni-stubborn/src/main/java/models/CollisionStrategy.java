package models;

import java.util.Map;
import java.util.Optional;

/**
 * The CollisionStrategy Interface give contracts in order to
 * create any kind of implementations for a strategy to check if there are collisions between different
 * entities in the game map or with the boundaries of the game map.
 * This follows Strategy Pattern to give the program more extensibility and removing redundant usage of code
 *
 */
public interface CollisionStrategy {

    /**
     * Checks if it is possible for an entity, based on the current game map, if it
     * can move to the new given position.
     * 
     * @param board The board map of the game
     * @param newPos The new position that the entity is trying to reach
     * @param width The width boundary of the map
     * @param height The height boundary of the map
     * @return Whether it is possible to move to the new position
     */
    boolean checkCollisions(Map<Point2D, Optional<Entity>> board, Point2D newPos, int width, int height);

}
