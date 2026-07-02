package it.unibo.arkanoid.engine;

import it.unibo.arkanoid.controller.Command;

/**
 * The interface for game loop.
 *
 */
public interface GameLoop {

    /**
     * Allow to pause the game.
     */
    void setPause();

    /**
     * Restores the previously paused game.
     */
    void resumeGame();

    /**
     * Stop the game.
     */
    void stopGame();

    /**
     * Add input in the input queue of GameLoop.
     * 
     * @param input
     *            The client input catch from {@link View}.
     */
    void addInput(Command input);

    /**
     * Start the GameLoop.
     */
    void startGameLoop();


}
