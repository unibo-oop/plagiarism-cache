package it.unibo.model.worldconstructor.worldgenerator.api;

/**
 * Interface for managing world difficulty.
 * Responsible for adjusting the game difficulty based on player progression.
 */
public interface WorldDifficult { // NOPMD this interface is necessary to define the contract for difficulty management.

    /**
     * Checks and updates the current difficulty level of the world.
     * Typically called periodically or based on events.
     * 
     * @param height The current height or progression level of the player, used to determine difficulty adjustments.
     */
    void createDifficult(double height);

}
