package frogger.common.input;

import frogger.common.GameState;
import frogger.model.interfaces.Game;

/**
 * Pause command.
 */
public final class Pause implements Command {

    /**
     * {@inheritDoc}
     * Change the game state to pause.
     */
    @Override
    public void execute(final Game game) {
        GameState.setState(GameState.PAUSE);
    }

}
