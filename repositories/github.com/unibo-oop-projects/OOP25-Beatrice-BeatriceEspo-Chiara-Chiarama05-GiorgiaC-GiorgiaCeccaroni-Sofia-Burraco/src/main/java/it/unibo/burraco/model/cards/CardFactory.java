package it.unibo.burraco.model.cards;

/**
 * Factory for creating Card instances.
 * Ensures every Card gets a unique id.
 */
public final class CardFactory {

    private CardFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Creates a new Card with the given seed and value.
     *
     * @param seed  the seed of the card
     * @param value the value of the card
     * @return a new Card instance with a unique id
     */
    public static Card create(final Seed seed, final CardValue value) {
        return new CardImpl(seed, value);
    }
}
