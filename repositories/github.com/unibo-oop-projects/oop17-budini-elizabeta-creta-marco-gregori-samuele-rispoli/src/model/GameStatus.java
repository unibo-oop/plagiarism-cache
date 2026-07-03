package model;

/**
 * Defines the status of the game.
 */
public enum GameStatus {
    /**
     * If the player completes a levels.
     */
    Won,
    
    /**
     * If the player loses all the lives.
     */
    Over,
    
    /**
     * The game is running.
     */
    Running,

    /**
     * Player lose a life.
     */
    Dead,

    //watch later, unused
    /**
     * Game paused.
     */
    Pause;
}
