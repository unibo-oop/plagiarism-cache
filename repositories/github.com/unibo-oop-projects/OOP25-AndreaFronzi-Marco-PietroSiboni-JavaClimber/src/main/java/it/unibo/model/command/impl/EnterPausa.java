package it.unibo.model.command.impl;

import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.launchedgame.impl.PauseState;

/**
 * Rapresent the command to enter in the pause state.
 */
public class EnterPausa implements RunningCommand {

    /**
     * {@inheritDoc} If the command is executed, the game enter in the pause state.
     */
    @Override
    public void execute(final Alien alien, final LaunchedGame launchedGame) {
        launchedGame.setState(new PauseState(launchedGame));
    }

}
