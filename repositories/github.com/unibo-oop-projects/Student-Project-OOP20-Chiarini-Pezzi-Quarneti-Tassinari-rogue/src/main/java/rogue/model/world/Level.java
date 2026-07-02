package rogue.model.world;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import rogue.model.Entity;

/**
 * Level management.
 */
public interface Level {

    /**
     * @return the level's width (max x)
     */
    int getWidth();

    /**
     * @return the level's height (max y)
     */
    int getHeight();

    /**
     * @return a stream of all the tiles
     */
    Stream<Tile> getTileStream();


    /**
     * spawns an entity on a random tile.
     * @param e the entity to be spawned
     */
    void spawnEntity(Entity e);

    /**
     * spawns multiple entities on random tiles.
     * @param l the entity list
     */
    void spawnEntities(List<Entity> l);

    /**
     * move entities and perform round actions.
     * @param d the player's movement direction
     * @return if the player has entered a door
     */
    boolean moveEntities(Direction d);

    /**
     * @return all the entities with their corresponding tile
     */
    Map<Entity, Tile> getEntityMap();
}
