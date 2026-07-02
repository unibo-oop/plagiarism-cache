package talisman.model.action;

import java.io.IOException;
import java.io.ObjectInputStream;

import talisman.Controllers;
import talisman.model.character.CharacterModelImpl;

/**
 * Action that makes the player pay gold for another action.
 * 
 * @author Alberto Arduini
 *
 */
public class TalismanPayAction extends TalismanAmountAction {
    private static final long serialVersionUID = -4422869078909113064L;
    private static final String DESCRIPTION_FORMAT = "Pay %d gold for: %s";

    private final TalismanAction actionToApply;

    /**
     * Create a new pay action.
     * 
     * @param amount how much to pay
     * @param action the action to apply
     */
    public TalismanPayAction(final int amount, final TalismanAction action) {
        super(amount);
        this.actionToApply = action;
        this.actionToApply.setActionEndedListener(this::actionEnded);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return String.format(TalismanPayAction.DESCRIPTION_FORMAT, this.getAmount(),
                this.getActionToApply().getDescription());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply() {
        final CharacterModelImpl characterInfo = (CharacterModelImpl) Controllers.getCharactersController()
                .getCurrentPlayer().getCurrentCharacter();
        final int currentGold = characterInfo.getGold();
        if (currentGold < this.getAmount()) {
            return;
        }
        characterInfo.setGold(currentGold - this.getAmount());
        this.getActionToApply().apply();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canBeApplied() {
        return ((CharacterModelImpl) Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter())
                .getGold() >= this.getAmount();
    }

    /**
     * Gets the action that will be applied if the players pays.
     * 
     * @return the action
     */
    public TalismanAction getActionToApply() {
        return this.actionToApply;
    }

    private void readObject(final ObjectInputStream stream) throws ClassNotFoundException, IOException {
        stream.defaultReadObject();
        this.actionToApply.setActionEndedListener(this::actionEnded);
    }
}
