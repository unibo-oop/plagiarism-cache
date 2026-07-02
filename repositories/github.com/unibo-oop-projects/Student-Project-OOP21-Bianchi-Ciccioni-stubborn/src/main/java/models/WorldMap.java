package models;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The WorldMap Interface give contracts in order to
 * create any kind of implementations for a WorldMap for the game.
 */

public interface WorldMap {

    /**
     * Move player inside the worldMap.
     * 
     * @param movement The direction of the movement based given value
     */
    void movePlayer(MOVEMENT movement);

    /**
     * Get the entire worldMap, including its entities.
     * 
     * @return The entire worldMap, including its entities
     */
    Map<Point2D, Optional<Entity>> getBoard();

    /**
     * Get the current position of the player.
     * 
     * @return The current position of the player
     */
    Point2D getPlayerPos();

    /**
     * Get all positions occupied by Entities (excluding player) and which type of entities occupy them.
     * 
     * @return List of pairs that describe each position occupied by Entities (excluding player)
     * and which type of entity occupies it
     */
    List<Pair<Point2D, Class<? extends Entity>>> getEntitiesPos();
}
