package it.unibo.the100dayswar.controller.gamecontroller.api;

/**
 * Interface for the controller of the game.
 */
public interface GameController {
    /**
     * This method allows the player to attack another enemy.
     */
    void attack();

    /**
     * This method allows the player to skip his turn.
     * This method also checks the end of the game.
     */
    void skip();

    /**
     * Check if the game is over.
     */
    void checkGameOver();
}
