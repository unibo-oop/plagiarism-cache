package it.unibo.jtrs.controller.api;

import it.unibo.jtrs.model.api.GameModel.GameState;

/**
 * An interface modelling an application. An application should be able to update
 * its state and its graphics. The application should be also able to tell whether is
 * running or not.
 */
public interface Application {

    /**
     * Updates application logic.
     */
    void update();

    /**
     * Updates graphical component.
     */
    void redraw();

    /**
     * Checks the application running status.
     *
     * @return true if running, false otherwise
     */
    boolean isRunning();

    /**
     * Sends an interrupt to the application.
     */
    void interrupt();

    /**
     * Terminates the application execution.
     */
    void terminate();

    /**
     * Returns the score controller.
     *
     * @return the controller
     */
    ScoreController getScoreController();

    /**
     * Returns the preview controller.
     *
     * @return the controller
     */
    PreviewController getPreviewController();

    /**
     * Returns the game controller.
     *
     * @return the controller
     */
    GameController getGameController();

    /**
     * Returns the current game state.
     *
     * @return the game state
     */
    GameState getState();

}
