package it.unibo.model.interfaces;

/**
 * Interface representing a grid for managing Puyos in the game.
 * Provides methods for adding, removing, and querying Puyos within the grid.
 */
public interface GridInterface {

    /**
     * Gets the number of rows in the grid.
     * 
     * @return The total number of rows.
     */
    int getRows();

    /**
     * Gets the number of columns in the grid.
     * 
     * @return The total number of columns.
     */
    int getCols();

    /**
     * Adds a Puyo to the grid at the specified position.
     * 
     * @param puyo The Puyo to be placed in the grid.
     * @param x The x-coordinate (column index).
     * @param y The y-coordinate (row index).
     * @return {@code true} if the Puyo was successfully added, {@code false} otherwise.
     */
    boolean addPuyo(PuyoInterface puyo, int x, int y);

    /**
     * Removes a Puyo from the grid at the specified position.
     * 
     * @param x The x-coordinate (column index).
     * @param y The y-coordinate (row index).
     */
    void removePuyo(int x, int y);

    /**
     * Checks if the given position is valid within the grid boundaries.
     * 
     * @param x The x-coordinate (column index).
     * @param y The y-coordinate (row index).
     * @return {@code true} if the position is valid, {@code false} otherwise.
     */
    boolean isValidPosition(int x, int y);

    /**
     * Retrieves the Puyo at the specified position in the grid.
     * 
     * @param x The x-coordinate (column index).
     * @param y The y-coordinate (row index).
     * @return The Puyo at the specified position, or {@code null} if empty.
     */
    PuyoInterface getPuyo(int x, int y);

    /**
     * Checks if a specific column in the grid is full.
     * 
     * @param x The x-coordinate (column index).
     * @return {@code true} if the column is full, {@code false} otherwise.
     */
    boolean isColumnFull(int x);

    /**
     * Checks if the entire grid is full.
     * 
     * @return {@code true} if the grid is completely filled, {@code false} otherwise.
     */
    boolean isGridFull();

    /**
     * Checks if a specific row in the grid is full.
     * 
     * @param y The y-coordinate (row index).
     * @return {@code true} if the row is full, {@code false} otherwise.
     */
    boolean isRowFull(int y);

    /**
     * Clears the entire grid, removing all Puyos.
     */
    void clear();
}
