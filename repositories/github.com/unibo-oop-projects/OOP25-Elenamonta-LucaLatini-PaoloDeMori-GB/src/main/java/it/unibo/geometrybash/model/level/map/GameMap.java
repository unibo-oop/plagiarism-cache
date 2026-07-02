package it.unibo.geometrybash.model.level.map;

import java.util.List;
import java.util.Optional;

import it.unibo.geometrybash.model.level.Coordinate;
import it.unibo.geometrybash.model.level.map.exceptions.GameMapOperationException;

/**
 * Interface representing the grid or map of the game world.
 */
public interface GameMap {

    /**
     * Gets the cell at the specified coordinate.
     *
     * @param c the coordinate of the cell.
     * @return the cell at the given coordinate.
     * @throws GameMapOperationException if the cell is not present or coordinate is invalid.
     */
    Cell getPresentCell(Coordinate c) throws GameMapOperationException;

    /**
     * Gets all cells within a horizontal range of the X coordinates.
     *
     * @param startX the starting X coordinate.
     * @param endX the ending X coordinate.
     * @return a list of cells in the range.
     */
    List<Cell> getCellInRange(int startX, int endX);

    /**
     * Check if a coordinate exists within the map boundaries.
     *
     * @param c the coordinate to check.
     * @return true if the coordinate is valid, false otherwise.
     */
    boolean isCoordinateValid(Coordinate c);

    /**
     * Safely retrivies a cell at the given coordinate.
     *
     * @param c the coordinate
     * @return an Optional, it could contain the game object if it isn't empty.
     */
    Optional<Cell> getCell(Coordinate c);

    /**
     * Retrives all the cells in the map.
     *
     * @return a list of all the cells in the map.
     */
    List<Cell> getAllCells();

}
