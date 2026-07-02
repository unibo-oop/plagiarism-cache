package migglione.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import migglione.model.api.Deck;
import migglione.model.api.CardDraw;
import migglione.model.impl.Card;
import migglione.model.impl.Cards;
import migglione.model.impl.DeckImpl;
import migglione.model.impl.StandardCardDrawImpl;

/**
 * Responsible for testing the StandardCardDrawImpl class.
 * 
 * <p>
 * This test check the validity for: if no cards have been
 * drawn then it contains the same cards as the deck, when a
 * card is drawn it is removed from the remainingCards and that
 * what is removed is indeed an object Card
 */
class StandardCardDrawTest {

    private final Deck deck = new DeckImpl();
    private final CardDraw standardDraw = new StandardCardDrawImpl(deck);
    private final Cards database = new Cards();

    @Test
    void drawSameCardsInDeck() {
        final List<Card> cardsInDeck = new ArrayList<>(deck.getDeck()); 
        final List<Card> cardsWithDraw = createDeckList();

        assertEquals(
            cardsInDeck.stream().map(Card::getName).sorted().toList(),
            cardsWithDraw.stream().map(Card::getName).sorted().toList());
    }

    private List<Card> createDeckList() {
        final List<Card> deckList = new ArrayList<>();
        final int fullDeckSize = standardDraw.getSizeDeck();

        for (int i = 0; i < fullDeckSize; i++) {
            deckList.add(standardDraw.getCard());
        }
        return deckList;
    }

    @Test
    void checkDrawRemovesCard() {
        final int beforeDraw = standardDraw.getSizeDeck();
        standardDraw.getCard();
        final int afterDraw = standardDraw.getSizeDeck();

        assertNotEquals(beforeDraw, afterDraw);
    }

    @Test
    void removedObjectIsCard() {
        final Card removedCard = standardDraw.getCard();
        final Set<String> cardsNames = database.getCards().values()
            .stream()
            .map(Card::getName)
            .collect(Collectors.toSet());

        assertTrue(cardsNames.contains(removedCard.getName()));
    }
}
