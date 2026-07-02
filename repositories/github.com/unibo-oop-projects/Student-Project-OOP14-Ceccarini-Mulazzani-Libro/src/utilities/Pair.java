package utilities;
/**
 * 
 * @author presa dalla soluzione di un esercizio fatto in laboratorio
 *
 *@param <Y>
 *@param <X>
 */
public class Pair<X, Y> {
    /**
     * first element of the pair.
     */
    private X first;
    /**
     * second element of the pair.
     */
    private Y second;
    
    /**
     * 
     * @param nfirst is the first element
     * @param nsecond is the second element
     */
    public Pair(final X nfirst, final Y nsecond) {
    	this.first = nfirst;
    	this.second = nsecond;
    }
    
    /**
     * 
     * @return the first element of the pair
     */
    public X getFirst() {
    	return this.first;
    }
    
    /**
     * 
     * @return the second element of the pair
     */
    public Y getSecond() {
    	return this.second;
    }
    
    /**
     * 
     * @param nfirst to set as first element
     */
    public void setFirst(final X nfirst) {
		this.first = nfirst;
	}

    /**
     * 
     * @param nsecond to set as first element
     */
	public void setSecond(final Y nsecond) {
		this.second = nsecond;
	}

	/**
	 * 
	 * @return the pair as a String
	 */
	public String toString() {
    	return "<" + this.first + ", " + this.second + ">";
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
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
		final Pair <X, Y> other = (Pair <X, Y>) obj;
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
	
	
} 
