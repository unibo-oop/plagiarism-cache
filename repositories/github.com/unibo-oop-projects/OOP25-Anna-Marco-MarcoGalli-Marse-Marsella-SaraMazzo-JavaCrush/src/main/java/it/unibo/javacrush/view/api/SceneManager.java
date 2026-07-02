package it.unibo.javacrush.view.api;

import it.unibo.javacrush.controller.api.GameController;

/**
 * Interface for managing the scene transitions.
 */
public interface SceneManager {

    /**
     * Show the menu scene.
     */
    void showMenu();

    /**
     * Show the levels scene.
     */
    void showLevels();

    /**
     * Show the game scene.
     * 
     * @param gameController the game controller
     */
    void showGame(GameController gameController); // Puoi passare parametri!

    /**
     * Get the GameView.
     * 
     * @return the gameView
     */
    GameView getGameView();

    /**
     * Show the instructions scene.
     */
    void showInstructions(); 

    /**
     * Quit the application.
     */
    void quit();
}
