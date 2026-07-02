package javarogue.utility;

/**
 * 
 * Represents a 2D point (x, y) with integer coordinates.
 *
 */
public class Position {

	private int x;
	private int y;

	/**
	 * Builds a position with provided x and y arguments.
	 * 
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns position's 'x' element.
	 * 
	 * @return x
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * 
	 * Returns position's 'y' element.
	 * 
	 * @return y
	 */
	public int getY() {
		return this.y;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (o instanceof Position) {
			Position p = (Position) o;
			return this.x == p.x && this.y == p.y;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		// Using Cantor Pairs
		int cantor = (x + y) * (x + y + 1) / 2 + y;
		return Integer.hashCode(cantor);
	}

	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
	
}
