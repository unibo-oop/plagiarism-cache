package it.unibo.bmbman.model;

import java.util.List;
import it.unibo.bmbman.model.entities.Entity;
import it.unibo.bmbman.model.utilities.Position;
/**
 * Model the field game.
 *
 */
public interface Terrain {
    /**
     * Used to know all tiles in the field.
     * @return all the tiles in the field
     */
    List<Entity> getTiles();
    /**
     * Used to know all the free tiles.
     * @return all the free tiles in the field
     */
    List<Entity> getFreeTiles();
    /**
     * Used to know all the blocks in the field.
     * @return all the blocks in the field
     */
    List<Entity> getBlocks();
    /**
     * Used to have an entity in the field.
     * @param x the column 
     * @param y the row
     * @return the entity in column, row position 
     */
    Entity getEntity(int x, int y);
    /**
     * Used to have a free position in the field.
     * @return an always free position, never return the same position
     */
    Position getFreeRandomPosition();
    /**
     * Used to have a block position in the field.
     * @return a block position, never return the same position
     */
    Position getRandomBlockPosition();
}
