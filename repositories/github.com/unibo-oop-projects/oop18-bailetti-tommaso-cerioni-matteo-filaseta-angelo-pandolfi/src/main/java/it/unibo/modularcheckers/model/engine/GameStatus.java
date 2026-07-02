package it.unibo.modularcheckers.model.engine;

/**
 * Status of the game in a specific time.
 */
public enum GameStatus {
    /** The game is waiting to start. */
    NOT_IN_PROGRESS,
    /** The game is still in progress. */
    IN_PROGRESS,
    /** the game ended in TIE. */
    TIE,
    /** Someone won. */
    PLAYER_WON
}

