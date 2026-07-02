package it.unibo.monoopoly.model.state.impl;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.controller.data.impl.DataOutput;
import it.unibo.monoopoly.model.banker.api.Banker;
import it.unibo.monoopoly.model.banker.impl.BankerImpl;
import it.unibo.monoopoly.model.gameboard.api.Buildable;
import it.unibo.monoopoly.model.gameboard.api.Buyable;
import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.main.api.MainModel;
import it.unibo.monoopoly.model.player.api.Player;
import it.unibo.monoopoly.model.state.api.ModelState;

/**
 * Implementation of {@link ModelState} for the banker state,
 * used to pay an amount to the actual {@link Player}.
 */
public class ModelBankerState implements ModelState {
    private final MainModel mainModel;
    private final Banker banker = new BankerImpl();
    private boolean isIndebted;
    private final boolean isInPrison;
    /**
     * Constructor of the class,
     * that takes the {@link MainModel} reference to perform all necessary state operations,
     * according to the State pattern.
     * 
     * @param mainModel the reference to perform the operations.
     * @param isInPrison tells if the {@link Player} is paying to get out of prison
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public ModelBankerState(final MainModel mainModel, final boolean isInPrison) {
        this.mainModel = mainModel;
        this.isInPrison = isInPrison;
    }

    private Player getPlayer() {
        return this.mainModel.getGameBoard().getCurrentPlayer();
    }
    /**
     * {@inheritDoc}
     * In this specific case,
     * the method verify if the {@link Player} have enough money to pay,
     * and set the relative field.
     */
    @Override
    public boolean verify() {
        if (getPlayer().getMoneyAmount() >= 0) {
            this.isIndebted = false;
        } else {
            this.mainModel.setEvent(Optional.of(this.banker.selectOperations(getPlayer())));
            this.isIndebted = true;
        }
        return this.isIndebted;
    }
    /**
     * {@inheritDoc}
     * In this specific case,
     * pay the {@link Player} depending on the property chosen by the player.
     */
    @Override
    public void doAction(final DataOutput dataOutput) {
        if (dataOutput.selectedCell().isEmpty()) {
            return;
        }
        final Cell cellChosen = this.mainModel.getGameBoard().getCell(dataOutput.selectedCell().get());
        if (cellChosen instanceof Buildable && ((Buildable) cellChosen).getHousesNumber() > 0) {
            getPlayer().receive(((Buildable) cellChosen).sellHouse());
        } else {
            getPlayer().receive(((Buyable) cellChosen).getMortgageValue());
            ((Buyable) cellChosen).setMortgage();
        }
    }
    /**
     * {@inheritDoc}
     * In this specific case,
     * set the new {@link ModelState}:
     * -{@link ModelPrisonState} if the {@link Player} is bankrupt
     * -{@link ModelBankerState} if the payment isn't enough to pay the amount
     * -{@link ModelBuildHouseState} if the payment is enough to pay the amount
     */
    @Override
    public void closeModelState() {
        if (getPlayer().isBankrupt()) {
            completeBankrupt();
            this.mainModel.nextTurn();
        } else if (isIndebted) {
            this.mainModel.setState(new ModelBankerState(mainModel, this.isInPrison));
        } else if (isInPrison) {
            this.mainModel.setState(new ModelMovementState(this.mainModel, Optional.empty()));
        } else {
            this.mainModel.setState(new ModelUnmortgageState(this.mainModel));
        }
    }

    private void completeBankrupt() {
        for (int index = 0; index < getPlayer().getFreeJailCards(); index++) {
            this.mainModel.getGameBoard().getDeck().addPrisonCard();
            getPlayer().useGetOutOfJailCard();
        }
        this.mainModel.getGameBoard().removePlayer();
    }
}
