package net.pokemonbt.utility;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class representing a mutable pair of different values with different types.
 *
 * @param <T> The type of the first value.
 * @param <X> The type of the second value.
 */
public class Pair<T, X> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private T firstValue;
    private X secondValue;

    /**
     * @param first The value to assign to the first entry.
     * @param second The value to assign to the second entry.
     */
    public Pair(final T first, final X second) {
        this.firstValue = first;
        this.secondValue = second;
    }

    /**
     * @return The value of the first entry.
     */
    public T first() {
        return this.firstValue;
    }

    /**
     * @param value The value to assign to the first entry.
     */
    public void first(final T value) {
        this.firstValue = value;
    }

    /**
     * @return The value of the second entry.
     */
    public X second() {
        return this.secondValue;
    }

    /**
     * @param value The value to assign to the second entry.
     */
    public void second(final X value) {
        this.secondValue = value;
    }

    /**
     * {@inheritDoc}
     *
     * @param o {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Pair<?, ?> that = (Pair<?, ?>) o;
        return Objects.equals(this.firstValue, that.firstValue)
                && Objects.equals(this.secondValue, that.secondValue);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                this.firstValue,
                this.secondValue);
    }
}
