package vg.utils;

public enum GameState {
    /**
     * Game level is running.
     */
    PLAYING,
    /**
     * Game level is paused.
     */
    PAUSED,
    /**
     * Player has been killed and did not complete level.
     */
    GAMEOVER,
    /**
     * Player has completed current level.
     */
    VICTORY,
    /**
     * Game level is not running.
     */
    QUITTING
}
