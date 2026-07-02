package game.state;

/**
 * This is a state controller. It must be used as a reference point to manage and know the current game state.
 *
 */
public final class StateController {
    private static final GameContext GAME_CONTEXT = new GameContext();


    private StateController() {
        super();
    }
    /**
     * 
     * @return the game context
     */
    public static GameContext getGameContext() {
        return GAME_CONTEXT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return GAME_CONTEXT.getCurrentState().getClass().getSimpleName();
    }
}
