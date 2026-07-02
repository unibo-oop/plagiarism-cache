package talisman.model.action;

import talisman.Controllers;
import talisman.model.cards.Card;
import talisman.model.cards.DeckType;

/**
 * An action that makes the player draw a card and puts it on the cell.
 * 
 * @author Alberto Arduini
 *
 */
public class TalismanDrawCardAction  extends TalismanActionImpl {
    private static final long serialVersionUID = 1L;
    private static final String DESCRIPTION_FORMAT = "Draw a card if there isn't already one in this space";

    private final DeckType deck;

    /**
     * Creates a new draw card action.
     * 
     * @param deck the type of deck from which the card willbe drawn
     */
    public TalismanDrawCardAction(final DeckType deck) {
        this.deck = deck;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return String.format(TalismanDrawCardAction.DESCRIPTION_FORMAT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply() {
        if (!this.canBeApplied()) {
            return;
        }
        final Card card = Controllers.getDeckController(this.deck).draw();
        Controllers.getBoardController().setCurrentCharacterCellCard(card);
        this.actionEnded();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canBeApplied() {
        final int playerIndex = Controllers.getCharactersController().getCurrentPlayer().getIndex();
        return Controllers.getBoardController().getCharacterCell(playerIndex).getCard().isEmpty();
    }
}
