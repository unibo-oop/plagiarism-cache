package it.unibo.javacrush.model.api;

import it.unibo.javacrush.common.Direction;

/**
 * Interface representing the gravity engine in the game.
 */
public interface GravityEngine {

    /**
     * Apply the gravity to the board, making the cells fall down if there are empty cells below them.
     * 
     * @param board the board to apply the gravity to
     * 
     * @return true if any cell has moved, false otherwise
     */
    boolean applyGravity(Board board);

    /**
     * Get the current gravity direction.
     * 
     * @return the current gravity direction
     */
    Direction getDirection();

}
