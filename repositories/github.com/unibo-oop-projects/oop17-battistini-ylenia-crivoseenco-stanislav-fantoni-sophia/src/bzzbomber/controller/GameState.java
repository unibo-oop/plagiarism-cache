package bzzbomber.controller;

/**
 * Represents game states to handle with different logic.
 */
public enum GameState {
    /**
     * Initial state.
     */
    INIT,
    /**
     * Play state.
     */
    PLAY,
    /**
     * Pause state.
     */
    PAUSE,
    /**
     * Win state.
     */
    WON,
    /**
     * Lost state.
     */
    LOST,
    /**
     * Relaunch state.
     */
    RELAUNCH;
}
