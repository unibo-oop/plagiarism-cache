package it.unibo.the100dayswar.model.pathfinder.api;

import java.util.List;

import it.unibo.the100dayswar.model.cell.api.Cell;

/**
 * Interface that defines a path finder that can find
 * the shortest path from a cell to another.
 */
public interface PathFinder {
    /**
     * Finds the shortest path from a start cell to a goal cell.
     * 
     * @param start       The starting cell.
     * @param destination The goal cell.
     * @return A list of cells representing the path from start to goal,
     *         or an empty list if no path exists.
     */
    List<Cell> findPath(Cell start, Cell destination);
}
