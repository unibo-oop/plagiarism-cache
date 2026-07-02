package it.unibo.javajump.model.level.spawn.difficulty;

/**
 * The interface describing the Difficulty manager, that updates the difficulty and returns the current difficulty.
 */
public interface DifficultyManager {
    /**
     * Method to update the difficulty, according to the current score reached by the player.
     *
     * @param score the score
     */
    void updateDifficulty(int score);

    /**
     * Gets the current difficulty.
     *
     * @return the current difficulty
     */
    DifficultyState getCurrentDifficulty();

    /**
     * Resets the DifficultyManager.
     */
    void reset();
}
