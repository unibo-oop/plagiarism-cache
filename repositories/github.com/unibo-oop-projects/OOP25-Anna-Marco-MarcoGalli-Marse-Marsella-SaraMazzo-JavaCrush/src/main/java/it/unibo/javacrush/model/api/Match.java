package it.unibo.javacrush.model.api;

import java.util.Set;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;

/**
 * Represents a match of cells in the game.
 * A match consists of a set of positions (at least three) that all contain the same type of cell,
 * and a cell type that defines the type of cells.
 */
public interface Match {

    /**
     * Return the type of the Match.
     * 
     * @return the type of this Match.
     */
    CellType getType();

    /**
     * Return the Set of the Position that forms the Match.
     * 
     * @return the Set of Position of the Match.
     */
    Set<Position> getPositions();

    /**
     * Return the size of the Match.
     * 
     * @return the size of the Set of Position.
     */
    int getSize();

    /**
     * Return whether the Match is empty or not.
     * 
     * @return true if the Match is empy, false otherwise.
     */
    boolean isEmpty();

}
