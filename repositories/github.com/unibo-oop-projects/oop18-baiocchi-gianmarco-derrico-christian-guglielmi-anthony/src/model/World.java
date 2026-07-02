package model;

import java.awt.Graphics2D;
import java.util.Optional;
import java.util.Set;

import utilities.Position;

/**
 * 
 */
public interface World {

    /**
     * This method updates world's state.
     * @param elapseTime : passed time between two frames
     */
    void update(long elapseTime);

    /**
     * Render all objects' sprites.
     * @param g : component that draw the sprite
     */
    void render(Graphics2D g);

    /**
     * Get the positions of free tiles.
     * @return free tiles' positions
     */
    Set<Position> getFreePosition();

    /**
     * Get all objects that are in game.
     * @return all game object.
     */
    Set<GameObject> getAllGameObjects();

    /**
     * Get all blocks.
     * @return a set of all blocks
     */
    Set<Block> getBlocks();

    /**
     * Get all the enemies in game.
     * @return a set of all enemies.
     */
    Set<DynamicObject> getEnemies();

    /**
     * Get the player.
     * @return the player
     */
    DynamicObject getPlayer();

    /**
     * Get the door.
     * @return the door
     */
    Door getDoor();

    /**
     * Get the key.
     * @return the key
     */
    Optional<PickableObject> getKey();

    /**
     * Remove a game object from the world.
     * @param object : the object to remove
     */
    void removeObject(GameObject object);
}
