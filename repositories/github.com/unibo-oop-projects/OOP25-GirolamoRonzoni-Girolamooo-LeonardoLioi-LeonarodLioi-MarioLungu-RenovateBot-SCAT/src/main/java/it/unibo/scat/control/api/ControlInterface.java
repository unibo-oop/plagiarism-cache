package it.unibo.scat.control.api;

import it.unibo.scat.common.Direction;
import it.unibo.scat.common.GameState;

/**
 * Interface for the controller component.
 * Defines notifications sent from the view to the control layer.
 */
public interface ControlInterface {

    /**
     * Notifies that the username has been set.
     *
     * @param username player username
     */
    void notifySetUsername(String username);

    /**
     * Notifies that the player has fired a shot.
     */
    void notifyPlayerShot();

    /**
     * Notifies that the application should terminate.
     */
    void notifyQuitGame();

    /**
     * Notifies a player movement request.
     *
     * @param direction movement direction
     */
    void notifyPlayerMovement(Direction direction);

    /**
     * Notifies that the game should start.
     */
    void notifyStartGame();

    /**
     * Notifies that the game should be paused.
     */
    void notifyPauseGame();

    /**
     * Notifies that the game should resume.
     */
    void notifyResumeGame();

    /**
     * Notifies that the game should be reset.
     */
    void notifyResetGame();

    /**
     * @return the current game state.
     * 
     */
    GameState getGameState();
}
