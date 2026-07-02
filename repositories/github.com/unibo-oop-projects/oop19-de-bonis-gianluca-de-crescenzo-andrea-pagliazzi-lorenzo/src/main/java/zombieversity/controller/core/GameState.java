package zombieversity.controller.core;

/**
 * 
 * Used to manage the different states of the game.
 *
 */
public final class GameState {

    /**
     * Current state.
     */
    public static GameStateEnum state = GameStateEnum.MENU;
    /**
     * True if scene needs to be changed, false otherwise.
     */
    public static boolean change = false;
    /**
     * True to start a new game, false otherwise.
     */
    public static boolean init = false;
    /**
     * True to close the application, false otherwise.
     */
    public static boolean close = false;
    /**
     * True to turn off the sound, flase otherwise.
     */
    public static boolean soundOff = false;

    private GameState() {

    }

    /**
     * Enumeration to distinguish game states.
     */
    public static enum GameStateEnum {

        /**
         * MenuView.
         */
        MENU("menuview"),
        /**
         * GameView.
         */
        GAME("gameview"),
        /**
         * PauseView.
         */
        PAUSE("pauseview"),
        /**
         * Nickname insertion.
         */
        NICKNAME("nickInsertion"),
        /**
         * Leaderboard.
         */
        LEADERBOARD("leaderboard"),
        /**
         * End.
         */
        END("end");

        private final String name;

        GameStateEnum(final String stateName) {
            this.name = stateName;
        }

        /**
         * @return
         *      Name of the state as a String.
         */
        public final String getName() {
            return this.name;
        }

    }
}
