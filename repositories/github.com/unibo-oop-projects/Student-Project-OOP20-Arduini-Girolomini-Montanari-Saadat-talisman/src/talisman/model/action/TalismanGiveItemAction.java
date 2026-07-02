package talisman.model.action;

import java.util.Locale;

import talisman.Controllers;
import talisman.model.cards.CardImpl;
import talisman.model.cards.DeckType;

/**
 * An action that gives an item to a player.
 * 
 * @author Alberto Arduini
 *
 */
public class TalismanGiveItemAction extends TalismanActionImpl {
    private static final long serialVersionUID = -1469065542993618344L;
    private static final String DESCRIPTION_FORMAT = "You gain an item from the %s deck";

    private final DeckType fromDeck;

    public TalismanGiveItemAction(final DeckType fromDeck) {
        this.fromDeck = fromDeck;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return String.format(TalismanGiveItemAction.DESCRIPTION_FORMAT,
                this.getDeckType().toString().toLowerCase(Locale.ENGLISH));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply() {
        Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter().getInventory()
                .addCard((CardImpl) Controllers.getDeckController(this.getDeckType()).draw());
        this.actionEnded();
    }

    /**
     * Gets the deck from which the item will be drawn.
     * 
     * @return the item's index
     */
    public DeckType getDeckType() {
        return this.fromDeck;
    }
}
