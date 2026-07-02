package it.unibo.javacrush.model.api;

import java.util.Set;

import it.unibo.javacrush.common.Position;

/**
 * Represents a manager for finding and removing matches on the game board.
 */
public interface MatchManager {

    /**
     * Finds all matches at the given position on the board.
     * 
     * @param board the board to check for matches
     * @param pos the position to check for matches
     * @return the match found at the given position, which could be empty if no match is found.
     */
    Match findMatchesAt(Board board, Position pos);

    /**
     * Finds all matches on the board.
     * 
     * @param board the board to check for matches
     * @return a set of all matches found on the board
     */
    Set<Match> findAllMatches(Board board);

    /**
     * Removes the cells involved in the given match from the board.
     * 
     * @param board the board to remove the cells from
     * @param match the match whose cells are to be removed
     */
    void removeMatch(Board board, Match match);
}

