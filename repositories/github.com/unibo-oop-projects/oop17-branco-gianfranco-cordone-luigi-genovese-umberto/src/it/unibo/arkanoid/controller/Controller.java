package it.unibo.arkanoid.controller;

import it.unibo.arkanoid.engine.GameLoop;
import it.unibo.arkanoid.view.View;

/**
 * The controller interface.
 *
 */
public interface Controller {

    /**
     * 
     */
    void next();

    /**
     * 
     * @param username
     *            The username to set.
     */
    void saveScore(String username);

    /**
     * 
     * @return score
     */
    ScoreList getScore();

    /**
     * Setter for {@link View} component.
     * 
     * @param view
     *            The view component.
     */
    void setView(View view);

    /**
     * Getter for {@link GameLoop}.
     * 
     * @return The GameLoop.
     */
    GameLoop getGameLoop();

    /**
     * Gets information about the current game.
     * 
     * @return The {@link GameStats}
     */
    GameStats getGameStats();

    /**
     * Getter for Game State.
     * 
     * @return The current Game State.
     */
    State getGameState();

}
