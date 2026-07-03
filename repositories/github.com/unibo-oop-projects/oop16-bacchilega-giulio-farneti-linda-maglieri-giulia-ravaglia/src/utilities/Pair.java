package utilities;

/**
 * @param <X> the type of the first element of the pair
 * @param <Y> the type of the second element of the pair
 */
public class Pair <X, Y> {
    private final X first;
    private final Y second;
    /**
     * 
     * @param first the first given element
     * @param second the second given element
     */
    public Pair(final X first, final Y second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Return the first element of this pair.
     * @return <X>
     */
    public X getX() {
        return this.first;
    }

    /**
     * Return the second element of this pair.
     * @return <Y>
     */
    public Y getY() {
        return this.second;
    }

    /**
     * @param obj the given object
     * @return Boolean
     */
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
        @SuppressWarnings("rawtypes")
        final Pair other = (Pair) obj;
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
    public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((this.first == null) ? 0 : this.first.hashCode());
            result = prime * result + ((this.second == null) ? 0 : this.second.hashCode());
            return result;
    }

    /**
     * Print this object.
     * @return String
     */
    public String toString() {
        return "(" + this.first + "," + this.second + ")";
    }
}
