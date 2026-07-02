package it.unibo.monoopoly.model.gameboard.api;

import java.util.List;

/**
 * Represent the factory pattern to create the cells.
 */
public interface CellFactory {

    /**
     * Create all the cells of the gameboard.
     * @return list of all the cells of the {@link GameBoard}
     */
    List<Cell> createCells();

}
