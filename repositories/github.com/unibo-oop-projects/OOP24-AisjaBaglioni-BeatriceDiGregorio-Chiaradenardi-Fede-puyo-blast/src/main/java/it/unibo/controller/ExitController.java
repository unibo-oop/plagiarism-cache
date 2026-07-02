package it.unibo.controller;

import it.unibo.GameEvent;
import it.unibo.GameListener;
import it.unibo.controller.interfaces.ExitControllerInterface;

/**
 * Controller for handling the exit action in the game.
 * Responds to the exit button click, stops the game, and redirects to the main menu.
 */
public class ExitController implements ExitControllerInterface {
    private final GameListener listener;
    private final GameLoop gameLoop;

    /**
     * Constructor for the ExitController.
     * Initializes the controller with a listener and a game loop instance.
     * 
     * @param listener the listener that handles game events
     * @param gameLoop the game loop instance responsible for controlling the game flow
     */
    public ExitController(GameListener listener, GameLoop gameLoop) {
        this.listener = listener;
        this.gameLoop = gameLoop;
    }

    /**
     * Method to handle the exit button click event.
     * Stops the game and triggers an event to go to the main menu.
     */
    @Override
    public void onExitClicked() {
        gameLoop.stopGame();
        GameEvent e = new GameEvent(this);
        listener.goToMainMenu(e);
    }
}
