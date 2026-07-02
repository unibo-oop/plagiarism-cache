package utilities;

/**
 * Models a pair of two object.
 * @param <X> the type of the first object.
 * @param <Y> the type of the second object.
 */
public class Pair<X, Y> {

    private final X first;
    private final Y second;

    public Pair(final X first, final Y second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the first member of the pair.
     * @return the first member of the pair.
     */
    public X getFirst() {
        return this.first;
    }

    /**
     * Returns the second member of the pair.
     * @return the second member of the pair.
     */
    public Y getSecond() {
        return this.second;
    }

    /**
     * Returns the inverted pair of this pair.
     * @return the inverted pair of this pair.
     */
    public Pair<Y, X> getInverted() {
        return new Pair<Y, X>(this.getSecond(), this.getFirst());
    }

    /*EQUALS-----------------------------------------------------------*/
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((first == null) ? 0 : first.hashCode());
        result = prime * result + ((second == null) ? 0 : second.hashCode());
        return result;
    }
    /**
     * {@inheritDoc}
     */
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
        @SuppressWarnings("unchecked")
        final Pair<X, Y> other = (Pair<X, Y>) obj;
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
    /*-----------------------------------------------------------------*/

}
