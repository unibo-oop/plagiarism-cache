package controller.game;

/**
 * Represent all the possible state during the game.
 */
public enum GameStatus {

    /**
     * Initial phase of entity creation.
     */
    START,

    /**
     * Run the game with gameloop cycle.
     */
    RUNNING,

    /**
     * Use to freeze the game for the start.
     */
    PAUSE,

    /**
     * When you finish the level you can pass to next level or finish the game.
     */
    WIN,

    /**
     * When your life are ends the game is finish and you can enter your alias to see your ranking.
     */
    LOST,

    /**
     * Pre-game where you can navigate to see more info like settings, ranking and go to creative mode.
     */
    MENU;
}
