package it.unibo.monoopoly.model.deck.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.common.Message;
import it.unibo.monoopoly.model.deck.api.Card;
import it.unibo.monoopoly.model.deck.api.Deck;

/**
 * Implementation of {@link Deck}.
 */
public class DeckImpl implements Deck {
    private final List<Card> deck = new LinkedList<>();
    private final Set<Card> discardPile = new HashSet<>();
    private Card actualCard;
    private static final String PRISON_CARD_TEXT = "Uscite gratis di prigione,"
            + " se non ci siete: potete conservare questo cartoncino sino al momento di servirvene";

    /**
     * Constructor that initialize and shuffle the deck.
     */
    public DeckImpl() {
        this.deck.addAll(new CardsFactoryImpl().createDeck());
        shuffleDeck();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        if (this.deck.isEmpty()) {
            this.shuffleDeck();
        }
        this.actualCard = this.deck.removeFirst();
        if (this.actualCard.getMessage().typeOfAction() != Event.FREE_CARD) {
            this.discardPile.add(actualCard);
        }
    }

    private void shuffleDeck() {
        if (this.deck.isEmpty()) {
            this.deck.addAll(this.discardPile);
            this.discardPile.clear();
        }
        Collections.shuffle(this.deck);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Card getActualCard() {
        return this.actualCard;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPrisonCard() {
        final Card card = new CardImpl(PRISON_CARD_TEXT,
                new Message(Event.FREE_CARD, Optional.of(0)));
        this.discardPile.add(card);
    }

}
