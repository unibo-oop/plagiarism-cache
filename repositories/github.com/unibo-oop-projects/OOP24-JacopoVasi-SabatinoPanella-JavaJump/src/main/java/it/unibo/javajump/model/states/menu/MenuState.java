package it.unibo.javajump.model.states.menu;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.states.GameState;
import it.unibo.javajump.model.states.GameStateHandler;
import it.unibo.javajump.model.states.ingame.InGameState;


/**
 * The class that implements the menu state.
 */
public final class MenuState implements GameStateHandler {
    /**
     * The Game state.
     */
    static final GameState GAME_STATE = GameState.MENU;
    private float deltaTime;

    /**
     * {@inheritDoc} The implemented method checks the action: if it corresponds to "Confirm", a new game is started,
     * else if the action corresponds to the "Pause" action, the game closes.
     */
    @Override
    public void handleAction(final GameModel model, final GameAction action) {
        switch (action) {
            case CONFIRM_SELECTION:
                model.startGame();
                model.setState(new InGameState());
                break;
            case PAUSE_GAME:
                model.stopGame();
                break;
            default:
                break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final GameModel model, final float deltaTime) {
        this.deltaTime = deltaTime;
        model.notifyObservers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getGameState() {
        return GAME_STATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getDeltaTime() {
        return deltaTime;
    }
}
