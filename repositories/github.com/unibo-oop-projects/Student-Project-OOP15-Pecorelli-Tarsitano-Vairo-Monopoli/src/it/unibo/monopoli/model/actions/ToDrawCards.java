package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.mainunits.Player;

/**
 * This class represent one of the {@link Action}s of the game. This one is for
 * drawing {@link Card}s.
 *
 */
public class ToDrawCards implements Action {

    private final Deck deck;

    /**
     * Constructs a new instance of {@link ToDrawCards}'s {@link Action}. The
     * {@link Deck} in input is the one whence you draw.
     * 
     * @param deck
     *            - the {@link Deck} whence you draw
     */
    public ToDrawCards(final Deck deck) {
        this.deck = deck;
    }

    @Override
    public void play(final Player player) {
        final Card card = this.deck.getFirstCard();
        player.setLastCardDrew(card);
    }

}
