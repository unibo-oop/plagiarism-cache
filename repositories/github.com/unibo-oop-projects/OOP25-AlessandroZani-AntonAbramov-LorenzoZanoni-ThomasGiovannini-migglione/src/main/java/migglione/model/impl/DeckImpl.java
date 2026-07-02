package migglione.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import migglione.model.api.Deck;

/**
 * Implementation of the Deck interface.
 * 
 * <p>
 * It can be one of many implementations of it, but
 * in our case we are planning to use only this one.
 * 
 * <p>
 * But in order to grant the opportunity for the future
 * to design different kinds of implementation, it's best to
 * maintain the Strategy method.
 */
public final class DeckImpl implements Deck {

    private final Cards cards = new Cards();
    private final List<Card> deck = new ArrayList<>();
    private final Random random;

    /**
     * The constructor of the class.
     * Since in this case the shuffling has no
     * constraints and is completly random, it initializes
     * the deck normally and proceeds to initialize random
     */
    public DeckImpl() {
        createDeck();
        random = new Random();
    }

    private void createDeck() {
        cards.getCards().values().stream().forEach(deck::add);
    }

    @Override
    public void shuffle() {
        final List<Card> temp = new ArrayList<>();

        for (int i = deck.size(); i > 0; i--) {
            final int n = random.nextInt(deck.size());
            temp.addLast(deck.remove(n));
        }

        deck.addAll(temp);
    }

    @Override
    public List<Card> getDeck() {
        return Collections.unmodifiableList(this.deck);
    }
}
