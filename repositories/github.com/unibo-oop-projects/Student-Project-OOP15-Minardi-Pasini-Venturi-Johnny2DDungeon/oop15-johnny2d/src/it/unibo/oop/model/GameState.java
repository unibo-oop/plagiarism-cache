package it.unibo.oop.model;

import java.util.List;
import java.util.Optional;

import it.unibo.oop.utilities.Direction;

/**
 * Interface for game state.
 */
public interface GameState {

    /**
     * @param level
     *            of initialization.
     */
    void initialize(final int level);

    /**
     * Updates main character and enemies position.
     * 
     * @param newDirection
     *            of the main character.
     * @param isShooting
     *            if main character is shooting.
     */
    void updatePositions(Direction newDirection, boolean isShooting);

    /**
     * Gets the entities that can't be moved
     */
    List<AbstractEntity> getStableList();

    /**
     * Gets the entities that can be moved
     */
    List<MovableEntity> getMovableList();

    /**
     * Gets the {@link MainCharacter} as an {@link Optional}
     */
    Optional<MainCharacter> getMainChar();

    /**
     * Gets the {@link Arena} where the game is played
     */
    Arena getArena();

    /**
     * Getter for the game ending
     * @return true if game is finished.
     */
    boolean isGameEnded();

    /**
     * Determines if the player has made a new record.
     */
    void checkTopScore();

    /**
     * Getter for the score
     * @return hero's score.
     */
    Score getScore();
}