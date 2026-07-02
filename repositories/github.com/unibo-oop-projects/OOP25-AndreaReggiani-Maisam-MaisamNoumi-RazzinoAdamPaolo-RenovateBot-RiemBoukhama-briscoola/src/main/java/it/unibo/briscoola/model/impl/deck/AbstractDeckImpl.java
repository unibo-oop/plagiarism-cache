package it.unibo.briscoola.model.impl.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.deck.Deck;

/**
 * Abstract implementation of the {@link Deck} interface.
 * Provides the basic logic for drawing, shuffling,
 * and querying the deck of cards.
 *
 * @author Andrea
 *
 * @param <T> the type of {@link Card} handled by the deck
 */
public abstract class AbstractDeckImpl<T extends Card> implements Deck<T> {

    private final List<T> deckOfCards;
    private T tempBriscola;

    /**
     * Constructs an empty abstract deck initialized with an ArrayList.
     */
    public AbstractDeckImpl() {
        this.deckOfCards = new ArrayList<>();
    }

    /**
     * Constructs an abstract deck populated with an initial set of cards.
     *
     * @param initialSetOfCards the list of cards
     */
    public AbstractDeckImpl(final List<T> initialSetOfCards) {
        this.deckOfCards = new ArrayList<>(initialSetOfCards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void shuffle() {
        if (!deckOfCards.isEmpty()) {
            Collections.shuffle(deckOfCards);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Optional<T> draw() {
        if (deckOfCards.isEmpty()) {
           return Optional.empty(); 
        } else {
            return Optional.of(deckOfCards.removeFirst());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Optional<T> getBriscolaSeed() {
        if (deckOfCards.isEmpty()) {
            return Optional.ofNullable(tempBriscola);
        } else {
            if (tempBriscola == null) {
                this.tempBriscola = this.deckOfCards.getLast();
            }
            return Optional.of(tempBriscola);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getCurrentSize() {
        return deckOfCards.size();
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public final void refillDeck(final List<T> newInitialSetOfCards) {
        if (newInitialSetOfCards != null && !newInitialSetOfCards.isEmpty()) {
            this.deckOfCards.addAll(newInitialSetOfCards);
        }
    }

    /**
     * Allows DeckImpl to add cards to the deck, 
     * during the creation of the deck it self.
     * 
     * @param card the card
     */
    protected final void addCard(final T card) {
        this.deckOfCards.add(card);
    }
}
