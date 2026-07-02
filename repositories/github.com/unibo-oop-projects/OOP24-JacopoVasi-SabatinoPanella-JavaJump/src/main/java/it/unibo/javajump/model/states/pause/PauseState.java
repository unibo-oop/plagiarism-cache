package it.unibo.javajump.model.states.pause;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.states.GameState;
import it.unibo.javajump.model.states.GameStateHandler;
import it.unibo.javajump.model.states.ingame.InGameState;
import it.unibo.javajump.model.states.menu.MenuState;


/**
 * The class that implements Pause state.
 */
public final class PauseState implements GameStateHandler {
    private float deltaTime;
    private PauseOption selection = PauseOption.CONTINUE;
    /**
     * The Game state.
     */
    static final GameState GAME_STATE = GameState.PAUSE;

    /**
     * {@inheritDoc} The implemented method checks the current selector and, when "Confirm" action is pressed,
     * the corresponding state is performed.
     */
    @Override
    public void handleAction(final GameModel model, final GameAction action) {
        switch (action) {
            case CONFIRM_SELECTION -> {
                // CHECKSTYLE: MissingSwitchDefault OFF
                // switch does not need a default case
                switch (selection) {
                    case CONTINUE -> model.setState(new InGameState());
                    case MAIN_MENU -> model.setState(new MenuState());
                    case QUIT -> model.stopGame();
                }
                // CHECKSTYLE: MissingSwitchDefault ON
            }
            case MOVE_MENU_UP -> {
                if (selection == PauseOption.CONTINUE) {
                    selection = PauseOption.QUIT;
                } else if (selection == PauseOption.MAIN_MENU) {
                    selection = PauseOption.CONTINUE;
                } else if (selection == PauseOption.QUIT) {
                    selection = PauseOption.MAIN_MENU;
                }
            }
            case MOVE_MENU_DOWN -> {
                if (selection == PauseOption.CONTINUE) {
                    selection = PauseOption.MAIN_MENU;
                } else if (selection == PauseOption.MAIN_MENU) {
                    selection = PauseOption.QUIT;
                } else if (selection == PauseOption.QUIT) {
                    selection = PauseOption.CONTINUE;
                }
            }
            default -> { }
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
     * Gets selection.
     *
     * @return the selection
     */
    public PauseOption getSelection() {
        return selection;
    }

    @Override
    public float getDeltaTime() {
        return deltaTime;
    }
}
