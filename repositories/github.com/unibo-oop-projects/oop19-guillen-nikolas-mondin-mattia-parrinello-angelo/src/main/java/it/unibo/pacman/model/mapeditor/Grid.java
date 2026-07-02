package it.unibo.pacman.model.mapeditor;

import it.unibo.pacman.model.utilities.EntityType;

/**
 * Model the grid of a map.
 */
public interface Grid {

    /**
     * Set the entity at the given position of the grid.
     * 
     * @param x is the coordinate of the columns
     * @param y is the coordinate of the rows
     * @param type is the entity
     */
    void setEntity(int x, int y, EntityType type);

    /**
     * 
     * @param x is the coordinate of the columns
     * @param y is the coordinate of the rows
     * @return the entity at the given position of the grid
     */
    EntityType getEntity(int x, int y);

    /**
     * 
     * @return the rows of the grid
     */
    int getRows();

    /**
     * 
     * @return the columns of the grid
     */
    int getColumns();

}
