package it.unibo.oop.manpac.utils;

import java.util.Objects;

/**
 * It is a class that represents a pair of subjects.
 *
 * @param <X> The first subjects
 * @param <Y> The second subjects
 */
public final class PairImpl<X, Y> implements Pair<X, Y> {

    private final X first;
    private final Y second;

    /**
     * Constructor of the PairImpl class.
     * 
     * @param first  The first subjects
     * @param second The second subjects
     */
    public PairImpl(final X first, final Y second) {
        this.first = Objects.requireNonNull(first);
        this.second = Objects.requireNonNull(second);
    }

    @Override
    public X getFirst() {
        return this.first;
    }

    @Override
    public Y getSecond() {
        return this.second;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * (prime + first.hashCode()) + second.hashCode();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PairImpl<X, Y> other = (PairImpl<X, Y>) obj;
        if (!first.equals(other.first)) {
            return false;
        }
        return !second.equals(other.second) ? false : true;
    }

    @Override
    public String toString() {
        return "<" + this.first + "," + this.second + ">";
    }

}
