package controller;

import game.GameState;
import view.View;

/**
 * The basic methods the controller needs to have.
 */
public interface Controller extends ViewObserver {
    /**
     * Sets the window.
     * @param view the GUI of the application
     */
    void setView(View view);

    /**
     * Checks if the game is running, paused or ended.
     * @return the state of the game
     */
    GameState checkGameState();
}
