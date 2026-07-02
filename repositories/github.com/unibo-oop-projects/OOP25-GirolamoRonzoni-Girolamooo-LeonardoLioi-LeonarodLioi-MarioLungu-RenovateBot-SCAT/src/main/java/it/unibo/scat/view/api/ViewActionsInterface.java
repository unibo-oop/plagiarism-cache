package it.unibo.scat.view.api;

import java.util.List;

import javax.swing.JFrame;

import it.unibo.scat.common.EntityState;
import it.unibo.scat.common.GameRecord;
import it.unibo.scat.common.GameState;
import it.unibo.scat.control.api.ControlInterface;

/**
 * Interface for the View class, used by MenuPanel, GamePanel.
 */
public interface ViewActionsInterface {

    /**
     * Frame getter.
     * 
     * @return the frame.
     */
    JFrame getFrame();

    /**
     * Pauses the game.
     */
    void pauseGame();

    /**
     * Resumes the game.
     */
    void resumeGame();

    /**
     * Resets the game.
     */
    void resetGame();

    /**
     * Aborts the game.
     */
    void abortGame();

    /**
     * Quits the game.
     */
    void quitGame();

    /**
     * Username setter.
     * 
     * @param username the uername written by the player.
     */
    void setUsername(String username);

    /**
     * @return a list containing the current state of all entities in the game
     *         model.
     */
    List<EntityState> fetchEntitiesFromModel();

    /**
     * @return the list of GameRecords that represents the current leaderboard.
     */
    List<GameRecord> fetchLeaderboard();

    /**
     * @return the current player's username.
     */
    String fetchUsername();

    /**
     * Shows the game panel.
     */
    void showGamePanel();

    /**
     * Shows the menu panel.
     */
    void showMenuPanel();

    /**
     * @return the score.
     */
    int fetchScore();

    /**
     * @return the player's health.
     */
    int fetchPlayerHealth();

    /**
     * Starts the game.
     */
    void startGame();

    /**
     * Control interface getter.
     * 
     * @return the control interface.
     */
    ControlInterface getControlInterface();

    /**
     * Sets the index of the ship.
     * 
     * @param index the index of the ship selected by the player.
     */
    void setChosenShipIndex(int index);

    /**
     * Chosen ship getter.
     * 
     * @return the index of the chosen ship.
     */
    int getChosenShipIndex();

    /**
     * @return the current level.
     */
    int getLevel();

    /**
     * @return the time interval in milliseconds between two consecutive movements
     *         of the invaders.
     */
    int getInvadersStepMs();

    /**
     * @return the current accumulated time in milliseconds used to track the
     *         invaders' movement.
     */
    int getInvadersAccMs();

    /**
     * @return the current accumulated time in milliseconds used to track the bonus
     *         invader's movement.
     */
    int getBonusInvaderAccMs();

    /**
     * Game state getter.
     *
     * @return the current game state.
     */
    GameState getGameState();

}
