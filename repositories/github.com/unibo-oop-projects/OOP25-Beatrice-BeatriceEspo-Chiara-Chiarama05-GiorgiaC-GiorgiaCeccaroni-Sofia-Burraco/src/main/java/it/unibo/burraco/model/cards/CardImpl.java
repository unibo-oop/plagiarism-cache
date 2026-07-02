package it.unibo.burraco.model.cards;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Concrete implementation of Card.
 * Represents a playing card with a fixed suit and face value,
 * and tracks whether the card is currently acting as a wildcard.
 */
public final class CardImpl implements Card {

    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    private final int id;
    private final Seed seed;
    private final CardValue value;

    /**
     * Constructs a CardImpl with the specified seed and value.
     *
     * @param seed the seed of the card
     * @param value the face value of the card
     */
    public CardImpl(final Seed seed, final CardValue value) {
        this.seed = seed;
        this.value = value;
        this.id = COUNTER.getAndIncrement();
    }

    @Override
    public Seed getSeed() {
        return this.seed;
    }

    @Override
    public CardValue getValue() {
        return this.value;
    }

    /**
     * Returns a string representation of the card,
     * combining the face value display and the suit symbol.
     *
     * @return the string representation of the card
     */
    @Override
    public String toString() {
        return value.getDisplay() + seed.getSymbol();
    }

    @Override
    public int getNumericalValue() {
        return this.value.getNumericalValue();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CardImpl other = (CardImpl) obj;
        return this.id == other.id;
    }
}
