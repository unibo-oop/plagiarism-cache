package it.unibo.monoopoly.controller.state.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.controller.data.api.DataBuilderInput;
import it.unibo.monoopoly.controller.data.impl.DataBuilderInputImpl;
import it.unibo.monoopoly.controller.data.impl.DataInput;
import it.unibo.monoopoly.controller.data.impl.DataOutput;
import it.unibo.monoopoly.controller.main.api.MainController;
import it.unibo.monoopoly.controller.state.api.ControllerState;
import it.unibo.monoopoly.model.gameboard.api.Buyable;
import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.player.impl.PlayerImpl;
import it.unibo.monoopoly.model.player.impl.PlayerWrapper;
import it.unibo.monoopoly.model.state.api.ModelState;
import it.unibo.monoopoly.view.state.api.ViewState;

/**
 * Implementations of {@link ControllerState} for the unmortgage phase:
 * that call the {@link ModelState} and {@link ViewState} methods,
 * in the right order,
 * with the right input.
 * Build with {@link DataBuilderInput} all the data that need the view.
 */
public class ControllerUnmortgageState implements ControllerState {
    private final MainController mainController;
    private final ModelState actualModelState;
    private final ViewState actualViewState;
    private boolean haveMortgagePayableProperty;
    private final DataBuilderInput dataBuilderInput = new DataBuilderInputImpl();
    private final PlayerWrapper playerWrapper;
    private final List<Cell> gameBoardCellList;

    /**
     * Constructor of the class that sets the fields.
     * 
     * @param mainController    the main controller to be set.
     * @param actualModelState  the actual {@link ModelState} to be set.
     * @param actualViewState   the actual {@link ViewState} to be set.
     * @param playerWrapper     the wrap of the {@link PlayerImpl}.
     * @param gameBoardCellList the {@link List} of all {@link Cell} of the game.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State and pattern Proxy")
    public ControllerUnmortgageState(final MainController mainController, final ModelState actualModelState,
            final ViewState actualViewState, final PlayerWrapper playerWrapper, final List<Cell> gameBoardCellList) {
        this.mainController = mainController;
        this.actualModelState = actualModelState;
        this.actualViewState = actualViewState;
        this.playerWrapper = playerWrapper;
        this.gameBoardCellList = List.copyOf(gameBoardCellList);
    }

    /**
     *
     * {@inheritDoc}
     * In this specific case,
     * sets the field haveMortgagePayableProperty using the verify method of the
     * model,
     * then passes the field to set the view.
     * After that,
     * calls the visualize method of the view state with a correctly formatted
     * {@link DataInput}.
     */
    @Override
    public void startControllerState() {
        this.haveMortgagePayableProperty = this.actualModelState.verify();
        this.actualViewState.setter(this.haveMortgagePayableProperty);
        this.actualViewState.visualize(buildData());
    }

    /**
     *
     * {@inheritDoc}
     * In this specific case,
     * executes the operations in the model according to the logic,
     * passing the player's possible choices,
     * then finalizes the model's state and calls the nextPhase method of the {@link MainController}.
     */
    @Override
    public void closeControllerState(final DataOutput dataOutput) {
        this.actualModelState.doAction(dataOutput);
        this.actualModelState.closeModelState();
        this.mainController.nextPhase();
    }

    private DataInput buildData() {
        if (haveMortgagePayableProperty) {
            return this.dataBuilderInput.cellMap(unmortgageableList()).build();
        } else {
            return this.dataBuilderInput.build();
        }
    }

    private Map<Integer, Integer> unmortgageableList() {
        return this.playerWrapper.getProperties().stream()
                .filter(Buyable::isMortgaged)
                .filter(this::isPayable)
                .collect(Collectors.toMap(this.gameBoardCellList::indexOf, Buyable::getUnmortgageValue));
    }

    private boolean isPayable(final Buyable property) {
        final int toPay = property.getUnmortgageValue();
        return this.playerWrapper.isPayable(toPay);
    }

}
