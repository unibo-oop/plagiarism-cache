package it.unibo.controller.end.api;

/**
 * Interface representing the controller for the end screen of the game. It
 * defines
 * the methods that the end screen view will call to handle user interactions.
 */
public interface EndController {

    /**
     * Return to the menu.
     */
    void menu();

    /**
     * Restart the game.
     */
    void restart();

    /**
     * Return the score of the player.
     * 
     * @return the score of the player.
     */
    int getScore();

    /**
     * Check if the player has achieved a new high score.
     * 
     * @return true if the player has achieved a new high score, false otherwise.
     */
    boolean isNewHighScore();

}
