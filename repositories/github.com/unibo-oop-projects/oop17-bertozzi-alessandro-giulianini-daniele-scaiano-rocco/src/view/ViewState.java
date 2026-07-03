package view;

/**
 * It is used with the pattern observer to notify the controller about changes in the 
 * view.
 */
public enum ViewState {
    /**
     * The highscores are needed.
     */
    HIGHSCORES, 
    /**
     * The gameLoop needs to be resumed.
     */
    RESUME, 
    /**
     * The gameLoop needs to be aborted.
     */
    ABORT, 
    /**
     * A new singleplayer game needs to be started.
     */
    START_SINGLEPLAYER, 
    /**
     * A new multiplayer game needs to be started.
     */
    START_MULTIPLAYER, 
    /**
     * The gameLoop needs to be paused.
     */
    PAUSE;

}
