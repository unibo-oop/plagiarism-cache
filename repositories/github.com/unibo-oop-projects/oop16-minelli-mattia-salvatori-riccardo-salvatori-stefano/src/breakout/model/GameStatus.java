package breakout.model;

/**
 * Enum to define the status of the Model.
 * 
 */
public enum GameStatus {
    /**
     * Player win if he complets all levels.
     */
    Won,
    /**
     * The game is over when the player loose all his lives.
     */
    Over,
    /**
     * The game is running.
     */
    Running,

    /**
     * Player lose a life and the game restart.
     */
    Dead,

    /**
     * Game paused.
     */
    Pause;
}
