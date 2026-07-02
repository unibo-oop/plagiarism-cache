package it.unibo.monoopoly.model.state.impl;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.controller.data.impl.DataOutput;
import it.unibo.monoopoly.model.gameboard.api.Dices;
import it.unibo.monoopoly.model.main.api.MainModel;
import it.unibo.monoopoly.model.player.api.Player;
import it.unibo.monoopoly.model.state.api.ModelState;

/**
 * Implementation of {@link ModelState} for the Dices state,
 * used to move the actual {@link Player}.
 */
public class ModelMovementState implements ModelState {

    private static final int PASS_GO_REWARD = 200;

    private final MainModel mainModel;
    private final Dices dices;
    private final Optional<Integer> cellIndex;

    /**
     * Creates a new instance of {@link ModelMovementState}.
     * This state is used to move the current {@link Player} based on the dice
     * result.
     * 
     * @param mainModel used to associate the dices roll state with the specific
     *                  next state to execute.
     * @param cellIndex used to move the player by moving it to the cell index
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public ModelMovementState(final MainModel mainModel, final Optional<Integer> cellIndex) {
        this.mainModel = mainModel;
        this.cellIndex = cellIndex;
        this.dices = mainModel.getGameBoard().getDices();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify() {
        return this.cellIndex.isEmpty();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void doAction(final DataOutput data) {
        if (verify()) {
            moveWithDices();
        } else {
            moveWithCards(this.cellIndex.get());
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void closeModelState() {
        if (getPlayer().isPrisoned()) {
            this.mainModel.nextTurn();
        } else {
            this.mainModel.setState(new ModelCheckActionState(this.mainModel));
        }
    }

    private void moveWithDices() {
        this.dices.rollDices();
        if (hasPassedGo()) {
            getPlayer().receive(PASS_GO_REWARD);
            movePlayer((getPlayerPosition() + diceResult()) % numberOfCells());
        } else {
            movePlayer(getPlayerPosition() + diceResult());
        }
    }

    private void moveWithCards(final int cellIndex) {
        if (cellIndex >= 0) {
            if (!getPlayer().isPrisoned() && cellIndex < getPlayer().getActualPosition()) {
                getPlayer().receive(PASS_GO_REWARD);
            }
            movePlayer(cellIndex);
        } else {
            if (getPlayerPosition() + cellIndex < 0) {
                movePlayer(getPlayerPosition() + cellIndex + numberOfCells());
            } else {
                movePlayer(getPlayerPosition() + cellIndex);
            }
        }
    }

    private int diceResult() {
        return this.dices.getResult();
    }

    private int numberOfCells() {
        return this.mainModel.getGameBoard().getCellsList().size();
    }

    private int getPlayerPosition() {
        return getPlayer().getActualPosition();
    }

    private Player getPlayer() {
        return this.mainModel.getGameBoard().getCurrentPlayer();
    }

    private void movePlayer(final int cellIndex) {
        getPlayer().changePosition(cellIndex);
    }

    private boolean hasPassedGo() {
        return getPlayerPosition() + diceResult() >= numberOfCells();
    }
}
