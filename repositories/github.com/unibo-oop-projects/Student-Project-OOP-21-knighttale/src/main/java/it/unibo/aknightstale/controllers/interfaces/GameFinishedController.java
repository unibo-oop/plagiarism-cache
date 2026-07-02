package it.unibo.aknightstale.controllers.interfaces;

import it.unibo.aknightstale.views.interfaces.GameFinishedView;

public interface GameFinishedController extends Controller<GameFinishedView> {
    /**
     * Saves the score.
     *
     * @param name the name of the player.
     */
    void saveScore(String name);

    /**
     * Shows the main menu.
     */
    void showMainMenu();

    /**
     * Shows the scoreboard.
     */
    void showScoreboard();

    /**
     * Gets the score obtained in the game.
     *
     * @return Score obtained in the game.
     */
    int getScore();

    /**
     * Sets the score obtained in the game.
     *
     * @param score Score obtained in the game.
     */
    void setScore(int score);

    /**
     * Starts a new game.
     */
    void startNewGame();
}
