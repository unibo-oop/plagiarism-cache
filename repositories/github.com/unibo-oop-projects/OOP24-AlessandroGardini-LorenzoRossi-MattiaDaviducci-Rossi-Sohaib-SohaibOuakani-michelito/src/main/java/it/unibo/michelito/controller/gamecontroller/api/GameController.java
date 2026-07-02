package it.unibo.michelito.controller.gamecontroller.api;

import it.unibo.michelito.controller.maincontroller.api.Controller;

/**
 * Represents a controller for the game-related aspects.
 * Provides methods to start and stop the game.
 */
public interface GameController extends Controller {
    /**
     * Starts the game.
     * This method initializes and begins the game logic.
     */
    void startGame();

    /**
     * Stops the game.
     * This method handles the logic to properly end the game session.
     */
    void stopGame();
}
