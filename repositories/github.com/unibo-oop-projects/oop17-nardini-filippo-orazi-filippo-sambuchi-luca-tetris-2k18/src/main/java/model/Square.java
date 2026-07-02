package main.java.model;

import javafx.scene.paint.Color;

/**
 * Square is the class representing a filled cell in the game matrix.
 */
public class Square {
	
	private Point2D coords;
	private final Color color;
	
	/**
	 * Class constructor.
	 * 
	 * @param x	x value of the point
	 * @param y y value of the point
	 * @param color	fill color of the square
	 */
	public Square(int x, int y, Color color) {
		this.coords = new Point2D(x, y);
		this.color = color;
	}

	/**
	 * Getter for the coordinates.
	 */
	public Point2D getCoords() {
		return coords;
	}
	
	

	/**
	 * Setter for the coordinates.
	 */
	public void setCoords(Point2D coords) {
		this.coords = coords;
	}

	/**
	 * Getter for the color.
	 */
	public Color getColor() {
		return color;
	}
	
}
