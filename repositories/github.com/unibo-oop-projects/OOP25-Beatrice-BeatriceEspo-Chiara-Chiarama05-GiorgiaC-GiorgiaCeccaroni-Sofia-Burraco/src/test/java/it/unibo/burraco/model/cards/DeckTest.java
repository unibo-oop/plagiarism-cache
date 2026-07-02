package it.unibo.burraco.model.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeckTest {
    private static final int FULL_DECK_SIZE = 108;
    private static final int JOLLY_COUNT = 4;

    private DeckImpl deck;

    @BeforeEach
    void init() {
        this.deck = new DeckImpl();
    }

    @Test
    void testInitialSize() {
        assertEquals(FULL_DECK_SIZE, this.deck.getCards().size());
    }

    @Test
    void testInitialNotEmpty() {
        assertFalse(this.deck.isEmpty());
    }

    @Test
    void testDeckComposition() {
    final List<Card> cards = deck.getCards();

    final long jollyCount = cards.stream()
            .filter(c -> c.getValue() == CardValue.JOLLY)
            .count();
    assertEquals(JOLLY_COUNT, jollyCount);

    for (final Seed seed : new Seed[]{Seed.SPADES, Seed.HEARTS, Seed.CLUBS, Seed.DIAMONDS}) {
        for (final CardValue value : CardValue.values()) {
            if (value == CardValue.JOLLY) {
                continue;
            }
            final long count = cards.stream()
                    .filter(c -> c.getSeed() == seed && c.getValue() == value)
                    .count();
            assertEquals(2, count, "Expected 2 copies of " + value + seed);
            }
        }
    }

    @Test
    void testDrawReducesSize() {
        final Card drawn = this.deck.draw();
        assertNotNull(drawn);
        assertEquals(FULL_DECK_SIZE - 1, this.deck.getCards().size());
    }

    @Test
    void testDrawUntilEmpty() {
        for (int i = 0; i < FULL_DECK_SIZE; i++) {
            assertNotNull(this.deck.draw());
        }
        assertTrue(this.deck.isEmpty());
    }

    @Test
    void testDrawOnEmptyReturnsNull() {
        for (int i = 0; i < FULL_DECK_SIZE; i++) {
            this.deck.draw();
        }
        assertNull(this.deck.draw());
    }

    @Test
    void testGetCardsIsUnmodifiable() {
        final List<Card> cards = this.deck.getCards();
        assertThrows(UnsupportedOperationException.class, () -> cards.remove(0));
    }

    @Test
    void testResetRestoresFullDeck() {
        this.deck.draw();
        this.deck.draw();
        this.deck.draw();

        this.deck.reset();

        assertEquals(FULL_DECK_SIZE, this.deck.getCards().size());
        assertFalse(this.deck.isEmpty());
    }

    @Test
    void testResetOnEmptyDeck() {
        for (int i = 0; i < FULL_DECK_SIZE; i++) {
            this.deck.draw();
        }
        assertTrue(this.deck.isEmpty());

        this.deck.reset();

        assertEquals(FULL_DECK_SIZE, this.deck.getCards().size());
        assertFalse(this.deck.isEmpty());
    }
}
