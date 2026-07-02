package it.unibo.oop.controller;

/**
 * Enumeration with each application state, used to communicate the next state
 * of the app.
 */
public enum AppState {

    /**
     * To start the game.
     */
    START(false),

    /**
     * To play/replay the game.
     */
    PLAY(false),

    /**
     * To exit the game.
     */
    EXIT(false),

    /**
     * To come back the previous app-state.
     */
    BACK(false),

    /**
     * To pause the game.
     */
    PAUSE(true),

    /**
     * To launch the game.
     */
    LAUNCHING(true),

    /**
     * To quit the game.
     */
    QUIT(true),

    /**
     * To show the options of the app.
     */
    OPTIONS(true),

    /**
     * To show the credits of the app.
     */
    CREDITS(true),

    /**
     * To show the menu of game over.
     */
    GAME_OVER(true);

    private final boolean drawable;

    AppState(final boolean drawable) {
        this.drawable = drawable;
    }

    /**
     * @return true if the State has a view associated.
     */
    public boolean isDrawable() {
        return this.drawable;
    }
}