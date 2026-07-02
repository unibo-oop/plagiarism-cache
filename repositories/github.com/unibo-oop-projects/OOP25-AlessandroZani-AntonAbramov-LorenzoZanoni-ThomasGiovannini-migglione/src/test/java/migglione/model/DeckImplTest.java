package migglione.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import migglione.model.api.Deck;
import migglione.model.impl.Card;
import migglione.model.impl.DeckImpl;

/**
 * Test used to make sure that the Deck and DeckImpl classes work.
 * 
 * <p>
 * It is responsible for testing: if the deck is empty
 * when initialized, if two deck are equal when initialized
 * and if their size doesn't change upon shuffling and if the
 * cards are effectivly shuffled with the shuffle method
 * 
 * <p>
 * WARNING: there is the tiniest rarest chance that the last
 * test could fail if the randomized deck placed the cards in the
 * same order of the first deck, but that is intentional since we want
 * to make sure that the randomness is true for all cases
 */
class DeckImplTest {

    private final Deck deck = new DeckImpl();
    private final Deck deck2 = new DeckImpl();

    @Test
    void checkDeckNotEmpty() {
        assertFalse(deck.getDeck().isEmpty());
    }

    @Test
    void checkInitializedDecksWithSameCards() {
        List<Card> firstCardsList = new ArrayList<>(deck.getDeck());
        List<Card> secondCardsList = new ArrayList<>(deck2.getDeck());

        assertEquals(
            firstCardsList.stream().map(Card::getName).collect(Collectors.toSet()),
            secondCardsList.stream().map(Card::getName).collect(Collectors.toSet())
        );

        deck.shuffle();
        deck2.shuffle();
        firstCardsList = new ArrayList<>(deck.getDeck());
        secondCardsList = new ArrayList<>(deck2.getDeck());

        assertEquals(
            firstCardsList.stream().map(Card::getName).collect(Collectors.toSet()),
            secondCardsList.stream().map(Card::getName).collect(Collectors.toSet())
        );
        assertEquals(firstCardsList.size(), secondCardsList.size());
    }

    @Test
    void randomizedShuffle() {
        final List<Card> cards = new ArrayList<>(deck.getDeck());
        deck.shuffle();
        final List<Card> cardsAfter = new ArrayList<>(deck.getDeck());

        assertNotEquals(cards, cardsAfter);
    }
}
