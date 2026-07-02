package it.unibo.monoopoly.controller.state.impl;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.controller.data.impl.DataBuilderInputImpl;
import it.unibo.monoopoly.controller.data.impl.DataBuilderOutputImpl;
import it.unibo.monoopoly.controller.data.impl.DataOutput;
import it.unibo.monoopoly.controller.main.api.MainController;
import it.unibo.monoopoly.controller.state.api.ControllerState;
import it.unibo.monoopoly.model.gameboard.api.Buyable;
import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.gameboard.api.Functional;
import it.unibo.monoopoly.model.gameboard.impl.CellWrapper;
import it.unibo.monoopoly.model.state.api.ModelState;
import it.unibo.monoopoly.view.state.api.ViewState;

/**
 * Implementation of {@link ControllerState} that retrieve data from model
 * according to the {@link Event} and pass it to the view.
 */
public class ControllerCheckActionState implements ControllerState {

    private final MainController mainController;
    private final ModelState modelState;
    private final ViewState viewState;
    private final CellWrapper cellWrapper;

    /**
     * Construct the {@link ControllerCheckActionState}.
     * 
     * @param mainController the main controller
     * @param modelState     the actual model state
     * @param viewState      the view state
     * @param cellWrapper    the actual cell wrapped
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State and pattern Proxy")
    public ControllerCheckActionState(final MainController mainController, final ModelState modelState,
            final ViewState viewState, final CellWrapper cellWrapper) {
        this.mainController = mainController;
        this.modelState = modelState;
        this.viewState = viewState;
        this.cellWrapper = cellWrapper;
    }

    /**
     * {@inheritDoc}
     * Call the model and the view in the right order to perform and visualize the
     * right action.
     */
    @Override
    public void startControllerState() {
        if (modelState.verify()) {
            visualizeBuyProperty(cellWrapper);
        } else {
            modelState.doAction(new DataBuilderOutputImpl().build());
            final Optional<Event> actualEvent = this.mainController.getActualEvent();
            if (actualEvent.isPresent()) {
                if (actualEvent.get().equals(Event.RENT_PAYMENT)) {
                    visualizeRentPayment(cellWrapper, actualEvent);
                } else if (actualEvent.get().equals(Event.TAX_PAYMENT)) {
                    visualizeTaxPayment(cellWrapper, actualEvent);
                }
            }
            this.closeControllerState(new DataBuilderOutputImpl().build());
        }
    }

    private void visualizeTaxPayment(final Cell actualCell, final Optional<Event> actualEvent) {
        viewState.visualize(new DataBuilderInputImpl()
                .event(actualEvent.get())
                .valueToPay(((Functional) actualCell).getAction().get().data().get()).build());
    }

    /**
     * If needed perform the action of buy a property given the input of the player,
     * and next ends the state.
     * {@inheritDoc}
     */
    @Override
    public void closeControllerState(final DataOutput dataOutput) {
        if (dataOutput.buyProperty().isPresent()) {
            modelState.doAction(dataOutput);
        }
        modelState.closeModelState();
        mainController.nextPhase();

    }

    private void visualizeRentPayment(final Cell actualCell, final Optional<Event> actualEvent) {
        viewState.visualize(new DataBuilderInputImpl().event(actualEvent.get())
                .valueToPay(((Buyable) actualCell).getRentalValue())
                .text(((Buyable) actualCell).getOwner().get().getName()).build());
    }

    private void visualizeBuyProperty(final Cell actualCell) {
        viewState.visualize(new DataBuilderInputImpl()
                .event(Event.BUY_PROPERTY)
                .valueToPay(((Buyable) actualCell).getCost())
                .text(actualCell.getName()).build());
    }

}
