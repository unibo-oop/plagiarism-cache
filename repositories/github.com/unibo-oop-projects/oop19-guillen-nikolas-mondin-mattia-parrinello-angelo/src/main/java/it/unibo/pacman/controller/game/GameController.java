package it.unibo.pacman.controller.game;

import it.unibo.pacman.model.utilities.Difficulty;

/**
 * Controls the logic of the game.
 */
public interface GameController {
    /**
     * Used to start the game.
     * 
     * @param mapName the name of a particular map.
     * @param difficulty of the game
     */
    void startGame(String mapName, Difficulty difficulty);
    /**
     * Used to pause the game.
     */
    void pauseGame();
    /**
     * Used to notify the end of game.
     */
    void endGame();
    /**
     * Used to know if game is over.
     * 
     * @return true if pacman is dead.
     */
    boolean isGameOver();
    /**
     * Used to know if the player has won.
     * 
     * @return true if the pacman has eaten all the pills.
     */
    boolean hasWon();
    /**
     * Used to update any entity in the world.
     */
    void update();
}
