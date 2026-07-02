package it.unibo.the100dayswar.model.pathfinder.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.the100dayswar.model.cell.api.Cell;

/**
 * A pathfinding implementation using Breadth-First Search (BFS).
 */
public class BfsPathFinder {

    private final List<Cell> allCells;

    /**
     * Constructor for BfsPathFinder.
     *
     * @param allCells the list of all cells in the map
     */
    public BfsPathFinder(final Set<Cell> allCells) {
        this.allCells = new ArrayList<>(allCells);
    }

    /**
     * Finds the shortest path between a start cell and a destination cell.
     *
     * @param start the starting cell
     * @param destination the destination cell
     * @return a list of cells representing the path, or an empty list if no path exists
     */
    public List<Cell> findPath(final Cell start, final Cell destination) {
        if (start.equals(destination)) {
            return Collections.singletonList(start);
        }
        final Map<Cell, Cell> cameFrom = new HashMap<>();
        final Queue<Cell> queue = new LinkedList<>();
        final Set<Cell> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            final Cell current = queue.poll();

            if (current.equals(destination)) {
                return reconstructPath(cameFrom, start, destination);
            }
            for (final Cell neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor) && neighbor.isFree()) {
                    visited.add(neighbor);
                    cameFrom.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
        return Collections.emptyList(); // No path found
    }

    /**
     * Reconstructs the path from the start cell to the destination cell.
     *
     * @param cameFrom a map tracking the previous cell for each visited cell
     * @param start the starting cell
     * @param destination the destination cell
     * @return the list of cells representing the path
     */
    private List<Cell> reconstructPath(final Map<Cell, Cell> cameFrom, final Cell start, final Cell destination) {
        final List<Cell> path = new ArrayList<>();
        Cell current = destination;

        while (!current.equals(start)) {
            path.add(current);
            current = cameFrom.get(current);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }

    /**
     * Gets the neighbors of a cell.
     *
     * @param cell the cell for which to get neighbors
     * @return a list of adjacent cells that are free
     */
    private List<Cell> getNeighbors(final Cell cell) {
        return allCells.stream()
                .filter(c -> isNeighbor(cell, c) && c.isFree())
                .collect(Collectors.toList());
    }

    /**
     * Checks if two cells are neighbors.
     *
     * @param cell1 the first cell
     * @param cell2 the second cell
     * @return true if the cells are adjacent, false otherwise
     */
    private boolean isNeighbor(final Cell cell1, final Cell cell2) {
        final int dx = Math.abs(cell1.getPosition().getX() - cell2.getPosition().getX());
        final int dy = Math.abs(cell1.getPosition().getY() - cell2.getPosition().getY());
        return (dx + dy) == 1;
    }
}
