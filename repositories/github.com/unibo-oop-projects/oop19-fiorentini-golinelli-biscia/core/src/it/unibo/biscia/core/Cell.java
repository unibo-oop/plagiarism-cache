package it.unibo.biscia.core;

/**
 * Is a single location on board.
 *
 */
public interface Cell {

    /**
     * Get the index of column of cell.
     * @return a number positive 
     */
    int getCol();

    /**
     * Get the index of row of cell.
     * @return a number positive 
     */
    int getRow();
}
