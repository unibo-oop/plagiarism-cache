package talisman.model.cards;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DeckImpl implements Deck {
    private final Queue<Card> cards;
    private final DeckType type;

    public DeckImpl(final DeckType type, final Queue<Card> cards) {
        this.type = type;
        this.cards = new LinkedList<>(cards);
    }
    /**
     * {@inheritDoc}
     */
    public List<Card> getCards() {
        return List.copyOf(this.cards);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Card draw() {
        return cards.poll();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfCards() {
        return cards.size();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DeckType getType() {
        return this.type;
    }
    @Override
    public void shuffle() {
        Collections.shuffle((List<?>) this.cards);
    }
}
