package it.tbt.engine.api;

/**
 * Interface for the handling of all the steps of the GameLogic.
 */

public interface Game {

    /**
     * Initialization process for correct game logic handling.
     */
    void initialize();
    /**
     * Renders the current view.
     */
    void render();
    /**
     * @return true if any input has been discovered, false otherwise.
     */
    Boolean handleInput();
}
