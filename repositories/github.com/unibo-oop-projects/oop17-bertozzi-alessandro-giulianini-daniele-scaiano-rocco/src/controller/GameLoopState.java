package controller;

/**
 * The state of the GameLoop.
 */
public enum GameLoopState {
    /**
     * GameLoop created but not running yet.
     */
    READY,

    /**
     * GameLoop running.
     */
    RUNNING,

    /**
     * GameLoop pause.
     */
    PAUSED,

    /**
     * GameLoop ended.
     */
    ENDED;
}
