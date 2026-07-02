/**
 * 
 */
package model.utilities;

/**
 * Enumeration to define the status of the Model.
 */
public enum GameStatus {
    /**
     * Game hasn't begun.
     */
    UNINITIALIZED,
    /**
     * Team 1 won.
     */
    PLAYER1_WON,
    /**
     * Team 2 won.
     */
    PLAYER2_WON,
    /**
     * The game is running.
     */
    RUNNING;
}
