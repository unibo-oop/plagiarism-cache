package it.unibo.model;

import java.util.Optional;
import it.unibo.model.interfaces.StatusModelInterface;

/**
 * The {@code StatusModel} class represents the current status of the game, 
 * tracking whether the game has ended and the number of stars earned at the end.
 * 
 * This class implements the {@link StatusModelInterface} and provides methods 
 * to update and retrieve the game state.
 */
public class StatusModel implements StatusModelInterface {
    
    /** Indicates whether the game has ended. */
    private boolean gameEnded;

    /** The number of stars earned at the end of the game, if available. */
    private Optional<Integer> endStars;

    /**
     * Constructs a {@code StatusModel} instance with the game initially set as not ended 
     * and no stars assigned.
     */
    public StatusModel() {
        this.gameEnded = false;
        this.endStars = Optional.empty();
    }

    /**
     * Marks the game as ended.
     */
    @Override
    public void setGameEnded() {
        this.gameEnded = true;
    }

    /**
     * Sets the number of stars earned at the end of the game.
     * 
     * @param stars the number of stars earned
     */
    @Override
    public void setStars(int stars) {
        this.endStars = Optional.of(stars);
    }

    /**
     * Checks if the game has ended.
     * 
     * @return {@code true} if the game has ended, {@code false} otherwise
     */
    @Override
    public boolean isGameEnded() {
        return this.gameEnded;
    }

    /**
     * Retrieves the number of stars earned at the end of the game.
     * 
     * @return an {@link Optional} containing the number of stars, or an empty {@code Optional} if no stars are set
     */
    @Override
    public Optional<Integer> getEndStars() {
        return this.endStars;
    }
}
