package it.unibo.monoopoly.controller.state.impl;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.controller.data.api.DataBuilderInput;
import it.unibo.monoopoly.controller.data.impl.DataBuilderInputImpl;
import it.unibo.monoopoly.controller.data.impl.DataBuilderOutputImpl;
import it.unibo.monoopoly.controller.data.impl.DataOutput;
import it.unibo.monoopoly.controller.main.api.MainController;
import it.unibo.monoopoly.controller.state.api.ControllerState;
import it.unibo.monoopoly.model.player.api.Player;
import it.unibo.monoopoly.model.player.impl.PlayerWrapper;
import it.unibo.monoopoly.model.state.api.ModelState;
import it.unibo.monoopoly.view.state.api.ViewState;

/**
 * Implementations of {@link ControllerState} for the movement's state,
 * that call the {@link ModelState} and {@link ViewState} methods.
 * Build with {@link DataBuilderInput} all the data that need the View.
 */
public class ControllerPrisonState implements ControllerState {

    private final ModelState modelState;
    private final MainController mainController;
    private final ViewState viewState;
    private final PlayerWrapper playerWrapper;

    /**
     * Constructs the prison controller state.
     * 
     * @param mainController the mainController
     * @param modelState     the actual {@link ModelState}
     * @param viewState      the actual {@link ViewState}
     * @param playerWrapper  the {@link PlayerWrapper} of the turn.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State and pattern Proxy")
    public ControllerPrisonState(final MainController mainController, final ModelState modelState,
            final ViewState viewState, final PlayerWrapper playerWrapper) {
        this.modelState = modelState;
        this.viewState = viewState;
        this.playerWrapper = playerWrapper;
        this.mainController = mainController;
    }

    /**
     * Starts the state.
     * 
     * Verifies if the player must go to jail or if the player is in jail.
     * If the player must go to jail, the view is set to visualize the message.
     * If the player is in jail, the view is set to visualize the message and the options to get out of jail.
     */
    @Override
    public void startControllerState() {
        final boolean goToJail = modelState.verify();
        viewState.setter(goToJail);

        final DataBuilderInput dataBuilder = new DataBuilderInputImpl();
        if (goToJail) {
            viewState.visualize(dataBuilder.build());
            modelState.doAction(new DataOutput(Optional.empty(), Optional.empty()));
        } else {
            final Player currentPlayer = this.playerWrapper;
            if (currentPlayer.isPrisoned()) {
                final boolean hasCard = currentPlayer.getFreeJailCards() > 0;
                viewState.visualize(dataBuilder.enabled(hasCard).text(playerWrapper.getName()).build());
                modelState.doAction(new DataOutput(Optional.empty(), Optional.empty()));
            } else {
                viewState.visualize(dataBuilder.text(playerWrapper.getName()).build());
            }
        }
        this.closeControllerState(new DataBuilderOutputImpl().build());
    }

    /**
     * Continues the state.
     * 
     * @param data the data related to the user's selection
     */
    @Override
    public void closeControllerState(final DataOutput data) {
        modelState.closeModelState();
        mainController.nextPhase();
    }
}
