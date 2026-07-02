package it.unibo.monoopoly.model.state.impl;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.controller.data.impl.DataOutput;
import it.unibo.monoopoly.model.main.api.MainModel;
import it.unibo.monoopoly.model.player.api.Player;
import it.unibo.monoopoly.model.state.api.ModelState;

/**
 * The state of the model when a player is in jail.
 * {@link ModelState}
 */
public class ModelPrisonState implements ModelState {

    private static final int PRISON_COST = 50;
    private static final int PRISON_INDEX = 10;
    private final MainModel model;
    private final boolean goInJail;
    private boolean usedCard;

    /**
     * Constructs a ModelPrisonState.
     * 
     * @param model    the main game model
     * @param goInJail a boolean flag indicating whether the player must go to jail
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public ModelPrisonState(final MainModel model, final boolean goInJail) {
        this.model = model;
        this.goInJail = goInJail;
        this.usedCard = false;
    }

    /**
     * Verifies the state.
     * 
     * @return true if the player must go to jail, false if the player can get out of jail
     */
    @Override
    public boolean verify() {
        return goInJail;
    }

    /**
     * Performs the state action.
     * 
     * <p>
     * If the player must go to jail, they are imprisoned.
     * Otherwise, if the player has at least one "Get Out of Jail Free" card,
     * the card is used and the player is released from jail.
     * If the player has no cards, they must pay €50 to exit jail.
     * </p>
     * 
     * @param data the DataOutput containing mode information (e.g., whether a card was used)
     */
    @Override
    public void doAction(final DataOutput data) {
        final Player currentPlayer = model.getGameBoard().getCurrentPlayer();
        if (goInJail) {
            currentPlayer.setPrisoned();
        } else if (currentPlayer.isPrisoned() && currentPlayer.getFreeJailCards() > 0) {
                usedCard = true;
                currentPlayer.useGetOutOfJailCard();
                model.getGameBoard().getDeck().addPrisonCard();
            }
        }

    /**
     * Closes the state.
     * 
     * <p>
     * If the player must go to jail, the next turn is started.
     * Otherwise, if the player used a card, the model state is set to ModelMovementState.
     * If the player did not use a card, the model state is set to ModelBankerState with a payment of €50.
     * </p>
     */
    @Override
    public void closeModelState() {
        if (goInJail) {
            this.model.setState(new ModelMovementState(this.model, Optional.of(PRISON_INDEX)));
        } else if (this.model.getGameBoard().getCurrentPlayer().isPrisoned()) {
            this.model.getGameBoard().getCurrentPlayer().releaseFromPrison();
            if (usedCard) {
                model.setState(new ModelMovementState(model, Optional.empty()));
            } else {
                this.model.getGameBoard().getCurrentPlayer().pay(PRISON_COST);
                model.setState(new ModelBankerState(model, true));
            }
        } else {
            model.setState(new ModelMovementState(this.model, Optional.empty()));
        }
    }
}
