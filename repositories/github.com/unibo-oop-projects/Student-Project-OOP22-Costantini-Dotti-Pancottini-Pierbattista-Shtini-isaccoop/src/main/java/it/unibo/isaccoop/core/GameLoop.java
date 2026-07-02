package it.unibo.isaccoop.core;

import it.unibo.isaccoop.model.room.Level;

/**
 * Interface representing the game loop.
 */
public interface GameLoop {

    /**
     * Method that starts the main game loop: input, update, render.
     */
    void gameLoop();

    /**
     * Get if game loop is in pause or not.
     *
     * @return if game loop is in pause
     */
    boolean isPause();

    /**
     * Set game loop pause state.
     *
     * @param isPause new game loop pause state
     */
    void setPause(boolean isPause);

    /**
     *  Method to get the level handled by the game loop.
     *
     *  @return the level handled by the game loop
     */
    Level getLevel();
}
