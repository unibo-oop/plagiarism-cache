package it.unibo.controller.end.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.controller.end.api.EndController;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.launchedgame.impl.InitialState;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.impl.LaunchedGameState;
import it.unibo.model.menu.impl.MenuState;

/**
 * Implementation of {@link EndController}.
 */
public class EndControllerImpl implements EndController {

    /**
     * The entity which provide the istance of the game.
     */
    private final LaunchedGame launchedGame;

    /**
     * The entity which provide the istance of the menu.
     */
    private final Menu menu;

    /**
     * Constructor new EndControllerImpl.
     *
     * @param launchedGame the launched game entity
     * @param menu         the menu entity
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "The LaunchedGame and Menu instances are intentionally"
            + " shared with the controller because they need to be accessed for go back to the menu or restart the game.")
    public EndControllerImpl(final LaunchedGame launchedGame, final Menu menu) {
        this.launchedGame = launchedGame;
        this.menu = menu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void menu() {
        this.menu.setState(new MenuState(this.menu));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restart() {
        this.launchedGame.setState(new InitialState(this.launchedGame));
        this.menu.setState(new LaunchedGameState(this.menu));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return this.menu.getScoreManager().getCurrentScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNewHighScore() {
        return this.menu.getScoreManager().isNewHighScore();
    }

}
