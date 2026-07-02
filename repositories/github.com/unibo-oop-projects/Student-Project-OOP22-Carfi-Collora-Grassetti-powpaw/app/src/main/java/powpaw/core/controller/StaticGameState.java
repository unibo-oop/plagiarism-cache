package powpaw.core.controller;

import powpaw.core.view.api.GameStateView;
import powpaw.core.view.impl.GameStateViewImpl;

/**
 * Create a static GameState.
 * 
 * @author Simone Collorà
 */
public final class StaticGameState {

    private static GameStateView state = new GameStateViewImpl();

    private StaticGameState() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Return state.
     * 
     * @return state.
     */
    public static GameStateView getGameStateView() {
        return state;
    }

}
