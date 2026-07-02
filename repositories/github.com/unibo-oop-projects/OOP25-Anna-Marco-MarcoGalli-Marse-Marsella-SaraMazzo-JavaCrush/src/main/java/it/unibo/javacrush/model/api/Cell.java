package it.unibo.javacrush.model.api;

import it.unibo.javacrush.common.CellType;

/**
 * Interface representing a cell in the game board.
 * This interface defines the contract for a cell, which is a fundamental component of the game board.
 */
@FunctionalInterface
public interface Cell {

    /**
     * Get the type of the cell.
     * 
     * @return the type of the cell
     */
    CellType getType();

}
