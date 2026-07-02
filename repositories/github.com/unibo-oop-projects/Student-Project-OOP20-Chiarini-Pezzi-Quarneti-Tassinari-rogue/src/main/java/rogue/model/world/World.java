package rogue.model.world;

import java.util.Map;
import java.util.stream.Stream;

import rogue.model.Entity;
import rogue.model.creature.Player;

/**
 * the game world (level manager).
 */
public interface World {
    /**
     * @return the current level's width
     */
    int getWidth();

    /**
     * @return the current level's height
     */
    int getHeight();

    /**
     * @return all of the current level's tiles
     */
    Stream<Tile> getTiles();

    /**
     * @return all of the current level's entities with their corresponding tiles
     */
    Map<Entity, Tile> getEntityMap();

    /**
     * @param direction the player movement's direction
     * @return if the level is changed
     */
    boolean round(Direction direction);

    /**
     * @return the player
     */
    Player getPlayer();
}
