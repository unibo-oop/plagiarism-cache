package it.unibo.core.api;

import it.unibo.view.impl.WindowGame;

/**
 * Interface for the game engine.
 */
public interface GameEngine {
    /**
     * Loop of the game.
     * @param windowGame the main window of the game.
     */
    void gameLoop(WindowGame windowGame);
    /**
     * Update the game.
     */
    void update();
    /**
     * Draw the game.
     * @param windowGame the main window of the game.
     */
    void draw(WindowGame windowGame);
}

