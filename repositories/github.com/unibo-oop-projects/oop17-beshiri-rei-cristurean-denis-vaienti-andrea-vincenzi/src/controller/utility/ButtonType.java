package controller.utility;

/**
 *To specify what button was pressed.
 */
public enum ButtonType {

    /**
     * The button pressed start a new game.
     */
    START_GAME,

    /**
     * The button pressed quit a game.
     */
    QUIT_GAME,

    /**
     * The button pressed stop the game.
     */
    PAUSE_GAME,

    /**
     * The button pressed allow to turn to main menu.
     */
    RETURN_MAIN_MENU,

    /**
     * The button pressed allow to resume game loop.
     */
    RESUME_GAME;
}
