package controller;

/**
 * GameStatusImpl interface.
 *
 */
public interface GameStatus {

    /**
     * Resets game settings to restart the game and shows GameOver scene when player
     * loses the game.
     */
    void gameOver();

    /**
     * Pauses the game and shows PauseMenu scene when player presses the ESC button.
     */
    void gamePause();

    /**
     * Method called when player completes successfully a level.
     */
    void success();

    /**
     * Returns true if the game is paused, false otherwise.
     * 
     * @return gamePauseStatus
     */
    boolean isGamePause();

    /**
     * Setter of gamePauseStatus.
     * 
     * @param gamePause
     *            specifies if the game is paused or not
     */
    void setGamePause(boolean gamePause);
}
