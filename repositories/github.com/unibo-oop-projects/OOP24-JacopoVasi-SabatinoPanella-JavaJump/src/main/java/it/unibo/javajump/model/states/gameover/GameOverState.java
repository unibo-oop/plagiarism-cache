package it.unibo.javajump.model.states.gameover;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.states.GameState;
import it.unibo.javajump.model.states.GameStateHandler;
import it.unibo.javajump.model.states.menu.MenuState;

import java.util.Objects;

/**
 * The class that implements the Game over state.
 */
public final class GameOverState implements GameStateHandler {

    private float deltaTime;
    /**
     * The Game state.
     */
    static final GameState GAME_STATE = GameState.GAME_OVER;

    /**
     * {@inheritDoc} If the action corresponds to "Confirm", goes back to menu.
     */
    @Override
    public void handleAction(final GameModel model, final GameAction action) {

        if (Objects.requireNonNull(action) == GameAction.CONFIRM_SELECTION) {
            model.setState(new MenuState());
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
