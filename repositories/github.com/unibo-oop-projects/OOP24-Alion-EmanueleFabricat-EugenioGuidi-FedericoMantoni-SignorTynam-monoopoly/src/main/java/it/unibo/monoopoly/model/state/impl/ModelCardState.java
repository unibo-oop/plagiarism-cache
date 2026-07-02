package it.unibo.monoopoly.model.state.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.controller.data.impl.DataOutput;
import it.unibo.monoopoly.model.deck.api.Card;
import it.unibo.monoopoly.model.deck.api.Deck;
import it.unibo.monoopoly.model.gameboard.api.Buildable;
import it.unibo.monoopoly.model.main.api.MainModel;
import it.unibo.monoopoly.model.state.api.ModelState;

/**
 * Implementations of {@link ModelState} for the card's phase:
 * the state will draw the next card,
 * return the text and depending on the type of action,
 * it changes the state.
 */
public class ModelCardState implements ModelState {
    private static final int TAX_FOR_HOUSE = 40;
    private final MainModel mainModel;

    /**
     * Constructor of the class that sets the mainModel field.
     * 
     * @param mainModel to set.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public ModelCardState(final MainModel mainModel) {
        this.mainModel = mainModel;
    }

    private Deck getDeck() {
        return this.mainModel.getGameBoard().getDeck();
    }

    private Card getCard() {
        return getDeck().getActualCard();
    }

    /**
     * {@inheritDoc}
     * In this specific case,
     * this method is useless.
     */
    @Override
    public boolean verify() {
        return true;
    }

    /**
     * {@inheritDoc}
     * In this specific case,
     * the method draw the next {@link Card}.
     */
    @Override
    public void doAction(final DataOutput dataOutput) {
        getDeck().draw();
    }

    /**
     * {@inheritDoc}
     * In this case,
     * depending on the {@link Event} set the next state.
     */
    @Override
    public void closeModelState() {
        this.mainModel.setState(
            switch (getCard().getMessage().typeOfAction()) {
                case Event.FREE_CARD -> {
                        this.mainModel.getGameBoard().getCurrentPlayer().addGetOutOfJailCard();
                        yield new ModelUnmortgageState(mainModel);
                }
                case Event.MOVE_CARD ->
                    new ModelMovementState(mainModel, getCard().getMessage().data());
                case Event.PRISON ->
                    new ModelPrisonState(this.mainModel, true);
                case Event.CARD_PAYMENT -> {
                        if (getCard().getMessage().data().isPresent()) {
                            this.mainModel.getGameBoard().getCurrentPlayer().pay(getCard().getMessage().data().get());
                            yield new ModelBankerState(mainModel, false);
                        } else {
                            this.mainModel.getGameBoard().getCurrentPlayer().pay(payForHouse());
                            yield new ModelBankerState(mainModel, false);
                        }
                    }
                case Event.RECEIVE_CARD -> {
                        this.mainModel.getGameBoard().getCurrentPlayer().receive(getCard().getMessage().data().get());
                        yield new ModelUnmortgageState(mainModel);
                    }
                default ->
                    throw new IllegalStateException("la carta non dovrebbe non far niente");
            }
        );
    }

    private int payForHouse() {
        return numberOfHouses() * TAX_FOR_HOUSE;

    }

    private int numberOfHouses() {
        return this.mainModel.getGameBoard().getCurrentPlayer().getProperties().stream()
                .filter(c -> c instanceof Buildable)
                .map(c -> (Buildable) c)
                .mapToInt(Buildable::getHousesNumber)
                .sum();
    }
}
