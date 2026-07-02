package ballblast.model.levels;

/**
 * Defines the status of a game session.
 * 
 */
public enum GameStatus {
    /**
     * The game is over when the player has been destroyed.
     */
    OVER,
    /**
     * The game is running.
     */
    RUNNING,
    /**
     * Game paused.
     */
    PAUSE;
}
