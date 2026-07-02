package it.unibo.javacrush.model.api;

import java.util.Set;

import it.unibo.javacrush.common.Position;

/**
 * Represents the engine responsible for checking if two positions can be swapped on the board,
 * and for finding the matches resulting from the swap.
 */
public interface MoveEngine {

    /**
     * Check if two positions can be swapped on the board, meaning that the swap would result in a match.
     * 
     * @param board the board to check for the swap.
     * @param pos1 the first position to swap.
     * @param pos2 the second position to swap.
     * @return true if the positions can be swapped, false otherwise.
     */
    boolean canSwap(Board board, Position pos1, Position pos2);

    /**
     * Get the matches found during the last swap check.
     * 
     * @return the set of matches.
     */
    Set<Match> getMatches();

}
