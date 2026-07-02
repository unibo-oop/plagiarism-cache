package it.unibo.model.launchedgame.api;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Abstract base class for launched game states.
 */
public abstract class AbstractLaunchedState implements StateOfLaunchedGame {

    // CHECKSTYLE: VisibilityModifier OFF
    // The rule is disabled because this class is a template and classes that extend
    // it need to access the menu field to change the state of the launched game.

    /**
     * The game context that this state belongs to.
     */
    protected LaunchedGame launchedGame;

    // CHECKSTYLE: VisibilityModifier ON

    /**
     * Constructs a new BaseLaunchedState.
     * 
     * @param launchedGame the game context
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "The launched game is a mutable object, but it is necessary"
            + " to pass it to the state to comply to the state pattern.")
    public AbstractLaunchedState(final LaunchedGame launchedGame) {
        this.launchedGame = launchedGame;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final double dt) {
    }

}
