package utility;

/**
 * 
 * @author Denis
 *
 * @param <X> type of the first parameter of this pair
 * @param <Y> type of the second parameter of this pair 
 */

public class Pair<X,Y> {

	private X x;
	private Y y;
	
	
	/**
	 * 
	 * @param x first parameter of constructor 
	 * @param y second parameter of the constructor 
	 */
	public Pair(final X x, final Y y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * @return X x value 
	 */
	public X getX() {
		return this.x;
	}
	
	
	/**
	 * 
	 * @return Y y value 
	 */
	public Y getY() {
		return this.y;
	}
	
	
	/**
	 * 
	 * @param x the value of the first parameter of Pair 
	 */
	public void setX(X x) {
		this.x = x;
	}
	
	
	/**
	 * 
	 * @param y the value of the second parameter of Pair 
	 */
	public void setY(Y y) {
		this.y = y;
	}

	// Auto generated hashCode and equals 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		Pair other = (Pair) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}
	
	
	
}
