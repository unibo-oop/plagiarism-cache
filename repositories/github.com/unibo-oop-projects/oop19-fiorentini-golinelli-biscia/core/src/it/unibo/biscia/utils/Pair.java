package it.unibo.biscia.utils;

/**
 * Plain Old Java Object for pairing two different objects.
 *
 * @param <X> First object type.
 * @param <Y> Second object type
 */
public class Pair<X, Y> {

    private X first;
    private Y second;

    public Pair(final X first, final Y second) {
        this.first = first;
        this.second = second;
    }

    public final X getFirst() {
        return first;
    }

    public final Y getSecond() {
        return second;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((first == null) ? 0 : first.hashCode());
        result = prime * result + ((second == null) ? 0 : second.hashCode());
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Pair other = (Pair) obj;
        if (first == null) {
            if (other.first != null) {
                return false;
            }
        } else if (!first.equals(other.first)) {
            return false;
        }
        if (second == null) {
            if (other.second != null) {
                return false;
            }
        } else if (!second.equals(other.second)) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "Pair [first=" + first.toString() + ", second=" + second.toString() + "]";
    }

}
