package it.unibo.monoopoly.model.gameboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.model.deck.api.Card;
import it.unibo.monoopoly.model.deck.api.Deck;
import it.unibo.monoopoly.model.deck.impl.DeckImpl;

/**
 * Tester of {@link Deck} and {@link Card}.
 */
class DeckAndCardsTest {
    private Deck deck;
    private static final int NUMBER_OF_FREE_CARD = 2;
    private static final int NUMBER_OF_MOVE = 8;
    private static final int NUMBER_OF_PAY = 9;
    private static final int NUMBER_OF_PRISON = 2;
    private static final int NUMBER_OF_RECEIVE = 10;
    private static final int NUMBER_OF_ALL_CARDS = 31;

    /**
     * Initialization for the test.
     */
    @BeforeEach
    void init() {
        this.deck = new DeckImpl();
    }

    /**
     * Test to make sure of all cards are correctly set up,
     * the last assert is for control that the refill of deck go successfully.
     */
    @Test
    void testNumberOfCardsAndRestoreDeck() {
        boolean catchIt = false;
        final List<Card> cards = getList(NUMBER_OF_ALL_CARDS);
        assertEquals(cards.stream()
                .filter(p -> p.getMessage().typeOfAction() == Event.FREE_CARD)
                .count(), NUMBER_OF_FREE_CARD);
        assertEquals(cards.stream()
                .filter(p -> p.getMessage().typeOfAction() == Event.MOVE_CARD)
                .count(), NUMBER_OF_MOVE);
        assertEquals(cards.stream()
                .filter(p -> p.getMessage().typeOfAction() == Event.CARD_PAYMENT)
                .count(), NUMBER_OF_PAY);
        assertEquals(cards.stream()
                .filter(p -> p.getMessage().typeOfAction() == Event.PRISON)
                .count(), NUMBER_OF_PRISON);
        assertEquals(cards.stream()
                .filter(p -> p.getMessage().typeOfAction() == Event.RECEIVE_CARD)
                .count(), NUMBER_OF_RECEIVE);
        try {
            this.deck.draw();
        } catch (final NoSuchElementException e) {
            catchIt = true;
        }
        assertEquals(false, catchIt, "exception thrown");
    }

    /**
     * Gets the {@link List} composed of each card drawn,
     * with a given number of draws as input.
     * 
     * @param times the card is drawn.
     * @return the {@link List} composed of each card drawn in 39 draws.
     */
    List<Card> getList(final int times) {
        final List<Card> cards = new LinkedList<>();
        for (int i = 0; i < times; i++) {
            this.deck.draw();
            cards.add(this.deck.getActualCard());
        }
        return cards;
    }

    /**
     * Test to make sure that the functions that concern,
     * the insertion or removal of the 'Go to Jail' card works.
     */
    @Test
    void testJailOperations() {
        final List<Card> cards = new LinkedList<>();
        getList(NUMBER_OF_ALL_CARDS);
        cards.addAll(getList(NUMBER_OF_ALL_CARDS - 2));
        assertEquals(0, cards.stream()
                .map(c -> c.getMessage().typeOfAction())
                .filter(a -> a.equals(Event.FREE_CARD))
                .count());
        this.deck.addPrisonCard();
        this.deck.addPrisonCard();
        cards.clear();
        cards.addAll(getList(NUMBER_OF_ALL_CARDS));
        assertEquals(2, cards.stream()
                .map(c -> c.getMessage().typeOfAction())
                .filter(a -> a.equals(Event.FREE_CARD))
                .count());
    }
}
