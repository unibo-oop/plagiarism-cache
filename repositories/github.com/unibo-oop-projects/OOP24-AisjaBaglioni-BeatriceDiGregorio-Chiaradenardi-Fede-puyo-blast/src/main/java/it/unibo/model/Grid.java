package it.unibo.model;

import it.unibo.model.interfaces.GridInterface;
import it.unibo.model.interfaces.PuyoInterface;

/**
 * Manages the game grid and the positioning of Puyos.
 * The grid is represented as a 2D array of {@link PuyoInterface}.
 */
public class Grid implements GridInterface {
    /**
     * 2D array representing the grid.
     */
    private PuyoInterface[][] grid; 
    /**
     * Number of rows.
     */
    private final int rows; 
    /**
     * Number of columns.
     */
    private final int cols; 

    /**
     * Constructs a new grid with the specified number of rows and columns.
     *
     * @param rows The number of rows in the grid.
     * @param cols The number of columns in the grid.
     */
    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new PuyoInterface[rows][cols]; 
    }

    /**
     * Gets the number of rows in the grid.
     *
     * @return The number of rows.
     */
    @Override
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns in the grid.
     *
     * @return The number of columns.
     */
    @Override
    public int getCols() {
        return cols;
    }

    /**
     * Adds a Puyo to a specific position in the grid.
     *
     * @param puyo The {@link PuyoInterface} to be placed.
     * @param x    The x-coordinate (column index).
     * @param y    The y-coordinate (row index).
     * @return True if the Puyo was added successfully, false if the position is invalid or already occupied.
     */
    @Override
    public boolean addPuyo(PuyoInterface puyo, int x, int y) {
        if (isValidPosition(x, y) && grid[y][x] == null) {
            grid[y][x] = puyo;
            return true;
        }
        return false;
    }

    /**
     * Removes a Puyo from a specific position in the grid.
     *
     * @param x The x-coordinate (column index).
     * @param y The y-coordinate (row index).
     */
    @Override
    public void removePuyo(int x, int y) {
        if (isValidPosition(x, y)) {
            grid[y][x] = null;
        }
    }

    /**
     * Checks if a position in the grid is valid.
     *
     * @param x The x-coordinate (column index).
     * @param y The y-coordinate (row index).
     * @return True if the position is within bounds, false otherwise.
     */
    @Override
    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < cols && y >= 0 && y < rows;
    }

    /**
     * Gets the Puyo at a specific position in the grid.
     *
     * @param x The x-coordinate (column index).
     * @param y The y-coordinate (row index).
     * @return The {@link PuyoInterface} at the specified position, or null if empty.
     */
    @Override
    public PuyoInterface getPuyo(int x, int y) {
        if (isValidPosition(x, y)) {
            return grid[y][x];
        }
        return null;
    }

    /**
     * Checks if a column is completely filled with Puyos.
     *
     * @param x The x-coordinate (column index).
     * @return True if the column is full, false otherwise.
     */
    @Override
    public boolean isColumnFull(int x) {
        for (int y = 0; y < rows; y++) {
            if (grid[y][x] == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a row is completely filled with Puyos.
     *
     * @param y The y-coordinate (row index).
     * @return True if the row is full, false otherwise.
     */
    @Override
    public boolean isRowFull(int y) {
        for (int x = 0; x < cols; x++) {
            if (grid[y][x] == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the entire grid is full.
     *
     * @return True if the grid is completely filled with Puyos, false otherwise.
     */
    @Override
    public boolean isGridFull() {
        for (int x = 0; x < cols; x++) {
            if (!isColumnFull(x)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Clears the grid by setting all positions to null.
     */
    @Override
    public void clear() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                grid[y][x] = null;
            }
        }
    }
}
