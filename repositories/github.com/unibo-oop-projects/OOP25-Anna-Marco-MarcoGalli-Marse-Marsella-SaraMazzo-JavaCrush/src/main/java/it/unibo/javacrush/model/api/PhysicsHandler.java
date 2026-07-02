package it.unibo.javacrush.model.api;

/**
 * Interface representing the physics handler in the game.
 * This interface defines the contract for a physics handler,
 * which is responsible for applying physics to the game board.
 */
public interface PhysicsHandler {

    /**
     * Applies the physics to the board, such as gravity and refill at the same step.
     * 
     * @param board the game board game to update
     * @return true if the board was changed, false otherwise
     */
    boolean update(Board board);

    /**
     * changes the current gravity strategy at runtime.
     * 
     * @param gravity the new gravity strategy to be applied
     */
    void setGravity(GravityEngine gravity);

    /**
     * initializes the board with a random configuration of cells, ensuring that there are no initial matches.
     * 
     * @param board the game board to initialize
     */
    void initializeBoard(Board board);
}
