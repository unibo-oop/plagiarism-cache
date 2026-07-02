package models;

import java.util.Map;
import java.util.Optional;

/**
 * The AiEnemy interface establishes contracts to develop new types of Ai for the Enemies.
 * This follows Strategy Pattern to give the program more extensibility and removing redundant usage of code.
 */
public interface AiEnemy {

    /**
     * Moves the enemy in a new position.
     * 
     * @param board the game map
     * @param playerPosition the current position of the player
     * @param position the current position of the enemy
     * @return the new position for the enemy
     */
    Point2D move(Map<Point2D, Optional<Entity>> board, Point2D playerPosition, Point2D position);

}
