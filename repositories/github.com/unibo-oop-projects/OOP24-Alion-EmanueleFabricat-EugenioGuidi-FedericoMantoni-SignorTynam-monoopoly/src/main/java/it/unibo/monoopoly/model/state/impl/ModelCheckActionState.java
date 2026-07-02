package it.unibo.monoopoly.model.state.impl;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.common.Message;
import it.unibo.monoopoly.controller.data.impl.DataOutput;
import it.unibo.monoopoly.model.gameboard.api.Buyable;
import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.gameboard.api.Functional;
import it.unibo.monoopoly.model.main.api.MainModel;
import it.unibo.monoopoly.model.notary.api.Notary;
import it.unibo.monoopoly.model.notary.impl.NotaryImpl;
import it.unibo.monoopoly.model.player.api.Player;
import it.unibo.monoopoly.model.state.api.ModelState;

/**
 * State that represent the control of what action will be performed depending
 * on the {@link Cell}.
 */
public class ModelCheckActionState implements ModelState {

    private final MainModel mainModel;
    private final Notary notary = new NotaryImpl();

    /**
     * Pass the mainModel to the state.
     * 
     * @param mainModel the main model
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public ModelCheckActionState(final MainModel mainModel) {
        this.mainModel = mainModel;
    }

    /**
     * {@inheritDoc}
     * 
     * Check if the action to perform is buy a property and set the state correctly
     * to perform the action.
     */
    @Override
    public boolean verify() {
        final boolean needInput = notary.isActionBuy(getActualCell(), getActualPlayer());
        if (needInput) {
            this.mainModel.setEvent(Optional.of(Event.BUY_PROPERTY));
        }
        return needInput;
    }

    /**
     * {@inheritDoc}
     * 
     * Perform the action or do nothing if the action it is duty of another state.
     */
    @Override
    public void doAction(final DataOutput data) {
        if (data.buyProperty().isEmpty()) {
            if (getActualCell().isBuyable()) {
                this.mainModel.setEvent(notary.checkProperty(getActualPlayer(), getActualCell()));
            } else {
                checkFunctionalCell();
            }
        } else if (data.buyProperty().get()) {
            notary.buyProperty(getActualPlayer(), (Buyable) getActualCell());
        } else {
            this.mainModel.setEvent(Optional.empty());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * The next state is set based of the action to perform or to be performed.
     */
    @Override
    public void closeModelState() {
        final Optional<Event> event = this.mainModel.getEvent();
        if (event.isEmpty() || event.get().equals(Event.BUY_PROPERTY)) {
            this.mainModel.setState(new ModelUnmortgageState(mainModel));
        } else {
            this.mainModel.setState(
                    switch (event.get()) {
                        case RENT_PAYMENT,
                            TAX_PAYMENT -> new ModelBankerState(mainModel, false);
                        case DRAW -> new ModelCardState(mainModel);
                        case PRISON -> new ModelPrisonState(mainModel, true);
                        default -> throw new IllegalStateException("Card event or unsupported event was insert");
                    });
        }
    }

    private void checkFunctionalCell() {
        final Functional functionalCell = (Functional) getActualCell();
        final var event = functionalCell.getAction().map(Message::typeOfAction);
        this.mainModel.setEvent(event);
        if (event.equals(Optional.of(Event.TAX_PAYMENT))) {
            this.getActualPlayer().pay(functionalCell.getAction().get().data().get());
        }
    }

    private Cell getActualCell() {
        return mainModel.getGameBoard().getCell(getActualPlayer().getActualPosition());
    }

    private Player getActualPlayer() {
        return mainModel.getGameBoard().getCurrentPlayer();
    }

}
