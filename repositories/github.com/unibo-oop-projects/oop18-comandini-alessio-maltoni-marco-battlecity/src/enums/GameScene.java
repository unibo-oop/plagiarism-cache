package enums;

/**
 * This is the enumeration for the scenes of the game.
 */
public enum GameScene {

    /**
     * The scene of the game playing.
     */
    GAME_GUI,
    /**
     * The game over scene.
     */
    GAME_OVER,
    /**
     * The first main menu scene.
     */
    MAIN_MENU_NEW,
    /**
     * The main menu with no animation, created only because Construction mode is
     * not implemented yet.
     */
    MAIN_MENU_OLD,
    /**
     * The scene that recapitulate the total points.
     */
    POINTS_RECAP,
    /**
     * The transition between main menu and construction mode.
     */
    TRANSITION_CONSTRUCTION,
    /**
     * The transition between main menu and one player mode.
     */
    TRANSITION_STAGE_1P,
    /**
     * The transition between main menu and two players mode.
     */
    TRANSITION_STAGE_2P;

}
