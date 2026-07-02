package it.unibo.javajump.controller;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.view.GameFrame;

/**
 * Interface for the game controller, that manages the game loop,
 * updates the model and the view using the current delta time.
 */
public interface GameController {
    /**
     * Starts the game loop, in a thread.
     *
     * @param model the game model
     * @param frame the game frame
     */
    void startGameLoop(GameModel model,  GameFrame frame);

}
