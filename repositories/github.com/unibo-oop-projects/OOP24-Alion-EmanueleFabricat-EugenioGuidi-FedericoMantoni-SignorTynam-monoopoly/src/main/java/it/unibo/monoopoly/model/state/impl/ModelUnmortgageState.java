package it.unibo.monoopoly.model.state.impl;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.controller.data.impl.DataBuilderOutputImpl;
import it.unibo.monoopoly.controller.data.impl.DataOutput;
import it.unibo.monoopoly.model.gameboard.api.Buyable;
import it.unibo.monoopoly.model.main.api.MainModel;
import it.unibo.monoopoly.model.player.api.Player;
import it.unibo.monoopoly.model.state.api.ModelState;

/**
 * Implementation of {@link ModelState} for the unmortgage property,
 * used to to release a mortgage on a property of the actual {@link Player},
 * if it has any,
 * for simplicity, one can only release the mortgage if they have liquid funds.
 */
public class ModelUnmortgageState implements ModelState {
    private boolean havePropertyToUnmortgage;
    private final MainModel mainModel;
    private DataOutput dataOutput;

    /**
     * Constructor of the class,
     * that takes the {@link MainModel} reference to perform all necessary state
     * operations,
     * according to the State pattern.
     * 
     * @param mainModel the reference to perform the operations.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public ModelUnmortgageState(final MainModel mainModel) {
        this.mainModel = mainModel;
        this.dataOutput = new DataBuilderOutputImpl().build();
    }

    /**
     * {@inheritDoc}
     * In this specific case,
     * the method verify if the {@link Player} has any property to unmortgage,
     * and set the relative field.
     */
    @Override
    public boolean verify() {
        this.havePropertyToUnmortgage = havePropertyToUnmortgage();
        return this.havePropertyToUnmortgage;
    }

    private boolean havePropertyToUnmortgage() {
        return this.mainModel.getGameBoard().getCurrentPlayer().getProperties().stream()
                .filter(this::isPayable)
                .anyMatch(Buyable::isMortgaged);
    }

    /**
     * {@inheritDoc}
     * In this specific case,
     * the method release a mortgage to the selected property.
     */
    @Override
    public void doAction(final DataOutput data) {
        this.dataOutput = data;
        if (data.selectedCell().isPresent()) {
            buyableFromIndex(data.selectedCell()).removeMortgage();
            this.mainModel.getGameBoard().getCurrentPlayer().pay(
                    buyableFromIndex(data.selectedCell()).getUnmortgageValue());
        }
    }

    private Buyable buyableFromIndex(final Optional<Integer> selectedCell) {
        return (Buyable) this.mainModel.getGameBoard().getCell(selectedCell.get());
    }

    private boolean isPayable(final Buyable property) {
        final int toPay = property.getUnmortgageValue();
        return this.mainModel.getGameBoard().getCurrentPlayer().isPayable(toPay);
    }

    /**
     * {@inheritDoc}
     * In this specific case,
     * set the new {@link ModelState}:
     * {@link ModelUnmortgageState} if the actual {@link Player} have properties to
     * unmortgage,
     * or wouldn't unmortgage
     * {@link ModelBuildHouseState} otherwise.
     */
    @Override
    public void closeModelState() {
        if (havePropertyToUnmortgage && dataOutput.selectedCell().isPresent()) {
            this.mainModel.setState(new ModelUnmortgageState(mainModel));
        } else {
            this.mainModel.setState(new ModelBuildHouseState(mainModel));
        }
    }

}
