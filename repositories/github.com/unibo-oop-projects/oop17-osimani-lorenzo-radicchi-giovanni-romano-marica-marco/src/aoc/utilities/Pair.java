package aoc.utilities;

/**
 * This class manages a pair of elements.
 * @param <X>
 *     The first element of type X
 * @param <Y>
 *     The second element of type Y
 */
public class Pair<X, Y> {

	/**
	 * The first element.
	 */
    private final X first;
    
    /**
     * The second element.
     */
    private final Y second;

    /**
     * Constructor of this class.
     * @param f
     *     The first element. It must be of type X.
     * @param s
     *     The second element. It must be of type Y.
     */
    public Pair(final X f, final Y s) {
        this.first = f;
        this.second = s;
    }

    /**
     * Getter of the first element of the pair. 
     * @return
     *     The first element of the pair. Its type is X.
     */
    public X getFirst() {
        return this.first;
    }

    /**
     * Getter of the second element of the pair.
     * @return
     *    The second element of the pair. Its type is Y.
     */
    public Y getSecond() {
        return this.second;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.first == null) ? 0 : this.first.hashCode());
		result = prime * result + ((this.second == null) ? 0 : this.second.hashCode());
		return result;
	}
    
    /**
     * {@inheritDoc}
     */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pair))
			return false;
		Pair other = (Pair) obj;
		if (this.first == null) {
			if (other.first != null)
				return false;
		} else if (!this.first.equals(other.first))
			return false;
		if (this.second == null) {
			if (other.second != null)
				return false;
		} else if (!this.second.equals(other.second))
			return false;
		return true;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public String toString() {
		return "[" + this.first + ";" + this.second + "]";
	}
}
