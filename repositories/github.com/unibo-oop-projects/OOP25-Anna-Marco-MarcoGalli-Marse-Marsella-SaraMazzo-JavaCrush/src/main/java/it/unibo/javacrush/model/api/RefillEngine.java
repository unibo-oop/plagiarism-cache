package it.unibo.javacrush.model.api;

/**
 * Interface representing the refill engine in the game.
 * This interface defines the contract for a refill engine,
 * which is responsible for refilling the board after the gravity has been applied.
 */
public interface RefillEngine {

    /**
     * Refill the board, filling the empty cells with new random cells.
     * 
     * @param board the board to refill
     * @return true if the board was changed, false otherwise
     */
    boolean refill(Board board);

    /**
     * Refill the board, filling the empty cells with new random cells without checking for matches.
     * 
     * @param board the board to refill
     */
    void refillAll(Board board);
}
