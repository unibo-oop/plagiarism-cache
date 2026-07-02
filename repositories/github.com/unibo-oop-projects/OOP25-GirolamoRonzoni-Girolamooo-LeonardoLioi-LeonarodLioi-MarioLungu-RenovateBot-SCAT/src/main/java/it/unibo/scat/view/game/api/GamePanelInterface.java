package it.unibo.scat.view.game.api;

import it.unibo.scat.common.GameState;

/**
 * Interface for the game panel.
 */
public interface GamePanelInterface {
    /**
     * Pauses the game.
     */
    void pause();

    /**
     * Resumes the game from the paused state.
     */
    void resume();

    /**
     * Aborts the current match and returns the user to the main menu.
     */
    void abortGame();

    /**
     * Quits/terminates the application.
     */
    void quit();

    /**
     * @return the current score to display.
     */
    int getScore();

    /**
     * @return the current player health (number of hearts).
     */
    int getPlayerHealth();

    /**
     * @return the current level.
     */
    int getLevel();

    /**
     * @return the username associated with the current session.
     */
    String getUsername();

    /**
     * Resets the game state to start a new match.
     */
    void restart();

    /**
     * Displays the display of Game Over on the screen.
     */
    void showGameOver();

    /**
     * @return the current state of the game.
     */
    GameState getGameState();
}
