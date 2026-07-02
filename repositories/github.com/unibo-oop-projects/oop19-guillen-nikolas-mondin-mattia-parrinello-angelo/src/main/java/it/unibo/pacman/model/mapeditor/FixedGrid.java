package it.unibo.pacman.model.mapeditor;

import java.util.List;

import it.unibo.pacman.model.mapeditor.FixedGridImpl.GridEntity;
import it.unibo.pacman.model.utilities.EntityType;

/**
 * Implement a grid where some entities are fixed in the map and cannot be modified.
 *
 */
public interface FixedGrid extends Grid {

    /**
     * Add a new fixed GridEntity at the given position.
     * 
     * @param x    is the coordinate of the columns
     * @param y    is the coordinate of the rows
     * @param type is the entity being added at the given position
     */
    void setFixedEntity(int x, int y, EntityType type);

    /**
     * Check if the entity at the given position is fixed.
     * 
     * @param x is the coordinate of the columns
     * @param y is the coordinate of the rows
     * @return true if the position is fixed, false if it is not
     */
    boolean isPositionFixed(int x, int y);

    /**
     * Fill empty positions with pills.
     */
    void fillWithPills();

    /**
     * 
     * @return the map
     */
    List<List<GridEntity>> getMap();

}
