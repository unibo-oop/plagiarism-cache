package aboidsim.util;

/**
 * A standard generic Pair<X,Y>, with getters, hashCode, equals, and toString
 * well implemented.
 *
 * @param <X>
 *            the first element class
 * @param <Y>
 *            the second element class
 *
 */
public class Pair<X, Y> {

	private X x;
	private Y y;

	/**
	 * Constructor.
	 *
	 * @param first
	 *            the first element
	 * @param second
	 *            the second element
	 */
	public Pair(final X first, final Y second) {
		super();
		this.x = first;
		this.y = second;
	}

	/**
	 * Getter. This method returns the first element
	 * 
	 * @return the first element of the pair
	 */
	public X getX() {
		return this.x;
	}
	
	/**
	 * Getter. This method returns the second element
	 * 
	 * @return the second element of the pair
	 */
	public Y getY() {
		return this.y;
	}
	
	/**
	 * Set x.
	 * 
	 * @param newX
	 * 		set new x.
	 */
	public void setX(final X newX) {
		this.x = newX;
	}
	
	/**
	 * Set y.
	 * 
	 * @param newY
	 * 		set new y.
	 */
	public void setY(final Y newY) {
		this.y = newY;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.x == null) ? 0 : this.x.hashCode());
		result = prime * result + ((this.y == null) ? 0 : this.y.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Pair other = (Pair) obj;
		if (this.x == null) {
			if (other.x != null) {
				return false;
			}
		} else if (!this.x.equals(other.x)) {
			return false;
		}
		if (this.y == null) {
			if (other.y != null) {
				return false;
			}
		} else if (!this.y.equals(other.y)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Pair [x=" + this.x + ", y=" + this.y + "]";
	}

}
