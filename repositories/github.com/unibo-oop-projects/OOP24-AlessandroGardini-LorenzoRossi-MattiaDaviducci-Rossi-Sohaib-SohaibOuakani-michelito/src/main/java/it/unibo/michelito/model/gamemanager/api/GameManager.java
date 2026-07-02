package it.unibo.michelito.model.gamemanager.api;

import it.unibo.michelito.controller.playercommand.api.PlayerCommand;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.GameObject;

import java.util.Set;

/**
 * Represents the Game Manager of the Michelito Application.
 * This interface defines the core logic for controlling the game state
 * ant it is the single entry point of the model.
 * Provides getter for the current state of the game.
 */
public interface GameManager {
    /**
     * Retrieves gameOver state.
     *
     * @return {@code true} if the game is lost, {@code false} otherwise.
     */
    boolean isGameOver();

    /**
     * Retrieves gameWon state.
     *
     * @return {@code true} if the game is won, {@code false} otherwise.
     */
    boolean isGameWon();

    /**
     * Retrieves Set of {@link GameObject} currently in the {@link Maze}.
     *
     * @return the Set of {@link GameObject} currently in the {@link Maze}.
     */
    Set<GameObject> getObjects();

    /**
     * Retrieves remaining lives in the game.
     *
     * @return remaining lives count.
     */
    int getRemainingLives();

    /**
     * Retrieves current level index.
     *
     * @return current level index.
     */
    int getCurrentIndexLevel();

    /**
     * Sets the command to be executed on the current {@link Player}.
     * This method should be called before invoking {@link #update(long)} to ensure
     * that the player's action is processed in the next game update cycle.
     *
     * @param playerCommand the command to be executed on the {@link Player}.
     */
    void setCommand(PlayerCommand playerCommand);

    /**
     * Updates the state of the game, applying the player's command and updating
     * the current {@link Maze}.
     *
     * @param deltaTime the delta between last update in milliseconds, used for game state updates.
     */
    void update(long deltaTime);
}
