package it.unibo.monoopoly.controller.state.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.controller.data.api.DataBuilderInput;
import it.unibo.monoopoly.controller.data.impl.DataBuilderInputImpl;
import it.unibo.monoopoly.controller.data.impl.DataBuilderOutputImpl;
import it.unibo.monoopoly.controller.data.impl.DataOutput;
import it.unibo.monoopoly.controller.main.api.MainController;
import it.unibo.monoopoly.controller.state.api.ControllerState;
import it.unibo.monoopoly.model.deck.api.Card;
import it.unibo.monoopoly.model.deck.api.Deck;
import it.unibo.monoopoly.model.deck.impl.DeckImpl;
import it.unibo.monoopoly.model.deck.impl.DeckWrapper;
import it.unibo.monoopoly.model.state.api.ModelState;
import it.unibo.monoopoly.view.state.api.ViewState;

/**
 * Implementations of {@link ControllerState} for the card's phase:
 * that call the {@link ModelState} and {@link ViewState} methods,
 * in the right order,
 * with the right input.
 * Build with {@link DataBuilderInput} all the data that need the View.
 */
public class ControllerCardState implements ControllerState {
    private final MainController mainController;
    private final ModelState actualModelState;
    private final ViewState actualViewState;
    private final DeckWrapper deckWrapper;

    /**
     * Constructor of the class that sets the fields.
     * 
     * @param mainController   the main controller to be set.
     * @param actualModelState the actual {@link ModelState} to be set.
     * @param actualViewState  the actual {@link ViewState} to be set.
     * @param deckWrapper      the wrap of the {@link DeckImpl}.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State and pattern Proxy")
    public ControllerCardState(final MainController mainController, final ModelState actualModelState,
            final ViewState actualViewState, final DeckWrapper deckWrapper) {
        this.mainController = mainController;
        this.actualModelState = actualModelState;
        this.actualViewState = actualViewState;
        this.deckWrapper = deckWrapper;
    }

    /**
     *
     * {@inheritDoc}
     * In this specific case,
     * performs the verify operation,
     * setting it as input to the setMode of the view's state.
     * Then executes the doAction on the model,
     * followed by calling the visualize method,
     * passing the text of the current {@link Card} from the {@link Deck}.
     * Finally, it calls the closeControllerState method.
     */
    @Override
    public void startControllerState() {
        this.actualViewState.setter(this.actualModelState.verify());
        this.actualModelState.doAction(new DataBuilderOutputImpl().build());
        this.actualViewState.visualize(new DataBuilderInputImpl().text(
                this.deckWrapper.getActualCard().getEffectText()).build());
        closeControllerState(new DataBuilderOutputImpl().build());
    }

    /**
     *
     * {@inheritDoc}
     * In this specific case,
     * finalizes the model's state and calls the nextPhase method of the {@link MainController}.
     */
    @Override
    public void closeControllerState(final DataOutput dataOutput) {
        this.actualModelState.closeModelState();
        this.mainController.nextPhase();
    }

}
