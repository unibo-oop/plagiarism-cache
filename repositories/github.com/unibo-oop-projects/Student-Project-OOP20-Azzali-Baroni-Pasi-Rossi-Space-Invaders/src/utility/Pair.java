package utility;

/**
 * The Class Pair.
 *
 * @param <X> the generic type
 * @param <Y> the generic type
 */
public final class Pair <X, Y> {
	
	/** The x. */
	private X x;
	
	/** The y. */
	private Y y;
	
	/**
	 * Instantiates a new pair.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Pair(final X x, final Y y) {
		
		super();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public X getX() {
		return x; 
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public Y getY() {
		return y;
	}
	
	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(final X x) {
		this.x = x;
	}
	
	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(final Y y) {
		this.y = y;
	}
	 
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	
	}
	
	/**
	 * Equals.
	 *
	 * @param object the object
	 * @return true, if successful
	 */
	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		
		if (object == null) {
			return false;
		}
		
		if (getClass() != object.getClass()) {
			return false;
		}
		
		final Pair other = (Pair)object;
		if (x == null) {
			if(other.x != null) {
				return false;
			}
		}
			else if (!x.equals(other.x)) {
				return false;
			}
			if (y == null) {
				if (other.y != null) {
					return false;
				}
			}
				else if (!y.equals(other.y)) {
					return false;
				
			}
				return true;
			}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Pair[x="+ x +", y=" + y + "]";

	} 
	}
	
	

