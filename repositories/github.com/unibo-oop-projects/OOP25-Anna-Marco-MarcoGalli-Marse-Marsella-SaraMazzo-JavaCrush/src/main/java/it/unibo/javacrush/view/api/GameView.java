package it.unibo.javacrush.view.api;

import it.unibo.javacrush.controller.api.AppController;
import it.unibo.javacrush.controller.api.GameController;
import javafx.scene.Parent;

/**
 * Interface for the game view.
 */
public interface GameView {

    /**
     * Update the view.
     */
    void updateView();

    /**
     * Quit the current level.
     */
    void quitLevel();

    /**
     * Get the view.
     * 
     * @return the view
     */
    Parent getView();

    /**
     * Set the controller for the view.
     * 
     * @param controller the game controller
     * @param appController the app controller
     */
    void setController(GameController controller, AppController appController);

}
