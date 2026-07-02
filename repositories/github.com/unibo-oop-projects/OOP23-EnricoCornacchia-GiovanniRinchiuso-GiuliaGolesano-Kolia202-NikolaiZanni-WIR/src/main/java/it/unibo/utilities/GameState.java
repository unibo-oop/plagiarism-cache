package it.unibo.utilities;

/**
 * Different states of the game.
 */
public enum GameState {
    /**
     * Represents the home screen.
     */
    HOME,
    /**
     * Represents the game being played.
     */
    PLAYING,
    /**
     * Represents the game being paused.
     */
    PAUSED,
    /**
     * Represents the game being over.
     */
    GAMEOVER,
    /**
     * Represents the game being won.
     */
    WIN,
    /**
     * Represents the settings screen.
     */
    SETTINGS;

    private static GameState gamestate = HOME;

    /**
     * Get the current game state.
     * 
     * @return the current game state
     */
    public static GameState getGameState() {
        return gamestate;
    }

    /**
     * Set the game state.
     * 
     * @param state the game state
     */
    public static void setGameState(final GameState state) {
        gamestate = state;
    }
}
