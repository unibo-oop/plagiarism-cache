package it.unibo.arkanoid.controller;

/**
 * The four possible game status, when the game start.
 *
 */
public enum GameState {

    /**
     * If player finish the level with success.
     */
    WIN,
    /**
     * If the player has lost all his lives.
     */
    GAME_OVER,
    /**
     * If GamelLoop works and the player is playing.
     */
    RUNNING,
    /**
     * If the GameLoop is ready to start.
     */
    READY,

    /**
     * Initialize the first level.
     */
    INITIALIZE,

    /**
     * If the game is over.
     */
    GAME_FINISHED;

}
