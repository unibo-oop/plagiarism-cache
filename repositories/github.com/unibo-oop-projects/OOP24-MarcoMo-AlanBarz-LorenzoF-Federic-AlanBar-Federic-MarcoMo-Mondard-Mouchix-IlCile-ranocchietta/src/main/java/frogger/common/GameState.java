package frogger.common;

/**
 * Represents the various possible states of the game.
 */
public enum GameState {

    /**
     * The game is actively being played.
     */
    PLAYING,

    /**
     * The player is in the shop interface.
     */
    SHOP,

    /**
     * The game is quitting or closing.
     */
    QUIT,

    /**
     * The game is in the main menu.
     */
    MENU,

    /**
    * The player has died.
    */
    DEAD,

    /**
     * The game is paused.
     */
    PAUSE;

    /**
     * The current state of the game.
     * Defaults to {@link #MENU}.
     */
    private static GameState state = MENU;

    /**
     * Returns the current game state.
     *
     * @return the current {@code GameState}
     */
    public static GameState getState() {
        return state;
    }

    /**
     * Sets the current game state to the specified value.
     *
     * @param newState the new {@code GameState} to set
     */
    public static void setState(final GameState newState) {
        state = newState;
    }
}
