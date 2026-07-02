package it.unibo.monoopoly.controller.state.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.controller.data.api.DataBuilderInput;
import it.unibo.monoopoly.controller.data.impl.DataBuilderInputImpl;
import it.unibo.monoopoly.controller.data.impl.DataBuilderOutputImpl;
import it.unibo.monoopoly.controller.data.impl.DataOutput;
import it.unibo.monoopoly.controller.main.api.MainController;
import it.unibo.monoopoly.controller.state.api.ControllerState;
import it.unibo.monoopoly.model.gameboard.api.Dices;
import it.unibo.monoopoly.model.state.api.ModelState;
import it.unibo.monoopoly.view.state.api.ViewState;

/**
 * Implementations of {@link ControllerState} for the movement's state,
 * that call the {@link ModelState} and {@link ViewState} methods.
 * Build with {@link DataBuilderInput} all the data that need the View.
 */
public class ControllerMovementState implements ControllerState {

    private final MainController mainController;
    private final ModelState actualModelState;
    private final ViewState actualViewState;
    private final Dices dices;
    private boolean rollDice;

    /**
     * inizialize fields of class.
     * 
     * @param mainController used to set the next state
     * @param modelState relative state in the model that executes the logic
     * @param viewState relative state in the view to show information and choices to the user 
     * @param dices used to move the player
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public ControllerMovementState(final MainController mainController,
            final ModelState modelState,
            final ViewState viewState,
            final Dices dices) {
        this.mainController = mainController;
        this.actualModelState = modelState;
        this.actualViewState = viewState;
        this.dices = dices;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startControllerState() {
        this.rollDice = this.actualModelState.verify();
        if (this.rollDice) {
            this.actualViewState.visualize(new DataBuilderInputImpl().enabled(this.rollDice).build());
        } else {
            closeControllerState(new DataBuilderOutputImpl().build());
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void closeControllerState(final DataOutput dataOutput) {
        this.actualModelState.doAction(new DataBuilderOutputImpl().build());
        if (this.rollDice) {
            this.actualViewState.visualize(new DataBuilderInputImpl().dices(this.dices.getDices()).build());
        }
        this.actualModelState.closeModelState();
        mainController.nextPhase();
    }
}
