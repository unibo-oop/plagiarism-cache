package it.unibo.controller.pause.api;

/**
 * Represents the controller for the pause menu.
 */
public interface PauseController {

    /**
     * Resumes the game.
     */
    void resume();

    /**
     * Returns to the main menu.
     */
    void menu();

    /**
     * Returns the current score of the player.
     * 
     * @return the current score of the player.
     */
    int getScore();

    /**
     * Checks if the player has achieved a new high score.
     * 
     * @return true if the player has achieved a new high score, false otherwise.
     */
    boolean isNewHighScore();

}
