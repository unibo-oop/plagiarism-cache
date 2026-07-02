package it.unibo.the100dayswar.model.map.impl;

import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.cell.impl.BonusCellImpl;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.map.api.GameMapBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
/**
 * The implementation of the gameBuilder.
 */
public class GameMapBuilderImpl implements GameMapBuilder {
    private static final long serialVersionUID = 1L;

    private final int width;
    private final int height;
    private final Cell[][] grid;
    private final Random random = new Random();

    /**
     * The constructor of the class.
     * @param width is the width of the map.
     * @param height is the height of the map.
     */
    public GameMapBuilderImpl(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.grid = new CellImpl[width][height];
    }

    /**
     * {@inheritDoc}
     * @return the initial map with all the cell setted as buildable.
     */
    @Override
    public GameMapBuilder initializeBuildableCells() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new CellImpl(new PositionImpl(x, y), true, false);
            }
        }
        return this;
    }

    /**
     * {@inheritDoc}
     * @return a game map with the spawn's cells.
     */
    @Override
    public GameMapBuilder addSpawnCells() {
        final int spawn1 = random.nextInt(width);
        final int spawn2 = random.nextInt(width);
        grid[0][spawn1] = new CellImpl(new PositionImpl(0, spawn1), true, true);
        grid[height - 1][spawn2] = new CellImpl(new PositionImpl(height - 1, spawn2), true, true);
        return this;
    }

    /**
     * {@inheritDoc}
     * @return the map with the unbuildable cells.
     */
    @Override
    public GameMapBuilder addObstacles(final int numberOfObstacles) {
        int obstaclesAdded = 0;
        while (obstaclesAdded < numberOfObstacles) {
            final int x = random.nextInt(width);
            final int y = random.nextInt(height);

            if (grid[x][y].isSpawn()) { 
                continue;
            }
           final Cell tempObstacle = new CellImpl(new PositionImpl(x, y), false, false);
           final Cell originalCell = grid[x][y];
            grid[x][y] = tempObstacle;

            if (isPathAvailable()) {
                obstaclesAdded++;
            } else {
                grid[x][y] = originalCell;
            }

        }
        return this;
    }

     /**
     * {@inheritDoc}
     * @return the map 
     */
    @Override
    public GameMapBuilder addBonusCell(final int numberOfBonusCell) {
         int bonusCellAdded = 0;

        while (bonusCellAdded < numberOfBonusCell) {
            final int x = random.nextInt(width);
            final int y = random.nextInt(height);

            if (!grid[x][y].isSpawn() && grid[x][y].isFree()) { 
                grid[x][y] = new BonusCellImpl(new CellImpl(new PositionImpl(x, y), true, false));
                bonusCellAdded++;
            }
        }
        return this;
    }

    /**
     * {@inheritDoc}
     * @return the map 
     */
    @Override
    public GameMap build() {
        return new GameMapImpl(width, height, grid);
    }
    /**
     * method to get the adiacent cell's at given position.
     * @param position is the position analyzed.
     * @return the list with the "neighbors" of position.
     */
    private List<Position> getNeighbors(final Position position) {
       final List<Position> neighbors = new ArrayList<>();
       final int x = position.getX();
       final int y = position.getY();

        if (x > 0) {
            neighbors.add(new PositionImpl(x - 1, y)); 
        }
        if (x < width - 1) {
            neighbors.add(new PositionImpl(x + 1, y)); 
        }
        if (y > 0) {
            neighbors.add(new PositionImpl(x, y - 1)); 
        }
        if (y < height - 1) {
            neighbors.add(new PositionImpl(x, y + 1)); 
        }
        return neighbors;
    }

    /**
     * BFS between 2 cells.
     * @param start the start's cell.
     * @param goal the goal's cell.
     * @return true if the path exist.
     */
    private boolean breadthFirstSearch(final Position start, final Position goal) {
       final boolean[][] visited = new boolean[height][width];
       final Queue<Position> queue = new LinkedList<>();
        queue.add(start);
        visited[start.getY()][start.getX()] = true;

        while (!queue.isEmpty()) {
           final Position current = queue.poll();

            if (current.equals(goal)) {
                return true;
            }

            for (final Position neighbor : getNeighbors(current)) {
                if (!visited[neighbor.getY()][neighbor.getX()]
                    && grid[neighbor.getY()][neighbor.getX()].isBuildable()) {
                        visited[neighbor.getY()][neighbor.getX()] = true;
                        queue.add(neighbor);
                    }
            }
        }
        return false;
    }

    /**
     * Method to verify if exist a path between the 2 spawn cells, using pathfinding(BFS).
     * @return true if the path exist.
     */
    private boolean isPathAvailable() {

        Position spawn1 = null;
        Position spawn2 = null;

        for (int i = 0; i < width; i++) {
            if (grid[0][i].isSpawn()) {
                spawn1 = grid[0][i].getPosition();
            }
            if (grid[height - 1][i].isSpawn()) {
                spawn2 = grid[height - 1][i].getPosition();
            }
        }
        if (spawn1 == null || spawn2 == null) {
            return false; 
        }
        return breadthFirstSearch(spawn1, spawn2);
    }
}
