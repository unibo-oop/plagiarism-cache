package controller;

/**
 * 
 * Class that save the current state of the game.
 *
 */
public final class GameState {

    private StateList state = StateList.MENU;
    private static final GameState GAMESTATE = new GameState();

    private GameState() {
    }

    /**
     * Singleton method.
     * 
     * @return current class.
     */
    public static GameState getGameState() {
        return GAMESTATE;
    }

    /**
     * 
     * @return current game state.
     */
    public StateList getState() {
        return state;
    }

    /**
     * 
     * @param state set new game state.
     */
    public void setState(final StateList state) {
        this.state = state;
    }

}
