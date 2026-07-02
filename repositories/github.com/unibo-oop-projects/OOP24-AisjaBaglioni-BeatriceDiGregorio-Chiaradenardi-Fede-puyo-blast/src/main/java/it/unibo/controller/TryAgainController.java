package it.unibo.controller;

import it.unibo.GameEvent;
import it.unibo.GameListener;
import it.unibo.controller.interfaces.TryAgainControllerInterface;

/**
 * Controller for handling the "Try Again" action in the game.
 * Responds to the "Try Again" button click, stops the current game, and restarts the game with the current level configuration.
 */
public class TryAgainController implements TryAgainControllerInterface {
    private final LevelManager.LevelConfig levelconfig;
    private final GameListener listener;
    private final GameLoop gameLoop;

    /**
     * Constructor for the TryAgainController.
     * Initializes the controller with the level configuration, game listener, and game loop instance.
     * 
     * @param levelconfig the configuration for the current level
     * @param listener the listener that handles game events
     * @param gameLoop the game loop instance responsible for controlling the game flow
     */
    public TryAgainController(LevelManager.LevelConfig levelconfig, GameListener listener, GameLoop gameLoop) {
        this.levelconfig = levelconfig;
        this.listener = listener;
        this.gameLoop = gameLoop;
    }

    /**
     * Method to handle the "Try Again" button click event.
     * Stops the game and triggers a new game start event with the current level configuration.
     */
    @Override
    public void handleClick() {
        gameLoop.stopGame();
        GameEvent e = new GameEvent(this, levelconfig);
        listener.startGame(e);
    }
}
