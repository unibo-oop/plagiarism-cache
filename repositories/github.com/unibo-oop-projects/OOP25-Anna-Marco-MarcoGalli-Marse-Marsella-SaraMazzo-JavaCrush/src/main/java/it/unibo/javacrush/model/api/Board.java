package it.unibo.javacrush.model.api;

import java.util.Map;
import java.util.Optional;

import it.unibo.javacrush.common.Position;

/**
 * Interface representing a game board.
 * This interface defines the contract for a game board,
 * which is composed of cells and is responsible for managing the game state.
 */
public interface Board {

    /**
     * Returns the number of rows in the board.
     * 
     * @return the number of rows in the board.
     */
    int getRows();

    /**
     * Returns the number of columns in the board.
     * 
     * @return the number of columns in the board.
     */
    int getCols();

    /**
     * Returns the cell at the specified position.
     * 
     * @param pos the position of the cell to retrieve.
     * @return the cell at the specified position.
     */
    Optional<Cell> getCellAt(Position pos);

    /**
     * Returns a Map containing all the cells in the board, mapped to their respective positions.
     * 
     * @return the Map with all the cells mapped to all their positions.
     */
    Map<Position, Optional<Cell>> getGrid();

    /**
     * Sets the cell at the specified position.
     * 
     * @param pos the position of the cell to set.
     * @param cell the cell to set at the specified position.
     */
    void setCell(Position pos, Optional<Cell> cell);

    /**
     * Swaps the cells at the specified positions.
     * 
     * @param pos1 the first position to swap.
     * @param pos2 the second position to swap.
     */
    void swapCells(Position pos1, Position pos2);

    /**
     * Removes the cell at the specified position.
     * 
     * @param pos the position of the cell to remove.
     */
    void removeCell(Position pos);
}
