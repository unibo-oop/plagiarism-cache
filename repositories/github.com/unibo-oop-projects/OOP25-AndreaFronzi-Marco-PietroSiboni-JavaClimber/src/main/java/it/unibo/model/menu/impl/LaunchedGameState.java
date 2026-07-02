package it.unibo.model.menu.impl;

import it.unibo.model.menu.api.Menu;
import it.unibo.model.launchedgame.impl.InitialState;
import it.unibo.model.launchedgame.impl.LaunchedGameImpl;
import it.unibo.model.menu.api.AbstractMenuState;

/**
 * Represents the state where the game has been launched from the menu.
 * Acts as a transition state between the menu system and the active gameplay.
 */
public class LaunchedGameState extends AbstractMenuState {

    /**
     * Constructs a new LaunchedGameState.
     * 
     * @param menu the Menu context
     */
    public LaunchedGameState(final Menu menu) {
        super(menu);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        final var launchedGame = new LaunchedGameImpl(this.menu);
        this.menu.setLaunchedGame(launchedGame);
        launchedGame.setState(new InitialState(launchedGame));
        this.menu.getMainController().launchGame();
    }

}
