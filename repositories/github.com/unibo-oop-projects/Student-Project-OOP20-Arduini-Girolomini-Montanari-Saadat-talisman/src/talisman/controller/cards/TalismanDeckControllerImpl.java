package talisman.controller.cards;

import talisman.model.cards.Card;
import talisman.model.cards.Deck;
import talisman.model.cards.DeckType;
import talisman.model.cards.TalismanDeckFactory;
import talisman.view.cards.TalismanDeckView;

public class TalismanDeckControllerImpl implements TalismanDeckController {
    private Deck deck;
    public TalismanDeckControllerImpl(final DeckType type) {
        this.deck = TalismanDeckFactory.createDeck(type);
    }

    @Override
    public Card draw() {
        Card drawnCard;
        drawnCard = deck.draw();
        if (drawnCard == null) {
            this.deck = TalismanDeckFactory.createDeck(this.deck.getType());
            this.shuffle();
            drawnCard = deck.draw();
        }
        return drawnCard;
    }

    @Override
    public TalismanDeckController shuffle() {
        deck.shuffle();
        return this;
    }

    @Override
    public TalismanDeckView createView() {
        // TODO Auto-generated method stub
        return null;
    }

}
