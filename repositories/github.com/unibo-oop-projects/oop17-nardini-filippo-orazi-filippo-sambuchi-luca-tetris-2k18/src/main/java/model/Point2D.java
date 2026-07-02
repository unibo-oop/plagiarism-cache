package main.java.model;

/**
 * Class representing a 2-dimensional point on a surface.
 */
public class Point2D {
	
	private final int x;
	private final int y;
	
	/**
	 * Class constructor.
	 */
	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter for the x value.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Getter for the y value.
	 */
	public int getY() {
		return y;
	}
}
