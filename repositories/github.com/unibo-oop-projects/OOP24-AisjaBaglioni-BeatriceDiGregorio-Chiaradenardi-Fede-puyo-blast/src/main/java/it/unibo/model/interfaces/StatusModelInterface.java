package it.unibo.model.interfaces;

import java.util.Optional;

/**
 * The {@code StatusModelInterface} defines the methods for managing the game's status.
 * It provides functionality to track whether the game has ended and the number of stars earned.
 */
public interface StatusModelInterface {

    /**
     * Marks the game as ended.
     */
    void setGameEnded();

    /**
     * Sets the number of stars earned at the end of the game.
     *
     * @param stars the number of stars obtained
     */
    void setStars(int stars);

    /**
     * Checks whether the game has ended.
     *
     * @return {@code true} if the game has ended, {@code false} otherwise
     */
    boolean isGameEnded();

    /**
     * Retrieves the number of stars earned at the end of the game.
     *
     * @return an {@link Optional} containing the number of stars, or an empty {@code Optional} if no stars are set
     */
    Optional<Integer> getEndStars();
}
