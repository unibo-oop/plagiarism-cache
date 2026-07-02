package model;

import javafx.scene.canvas.GraphicsContext;

public interface HealthBar {

	/**
	 * @return return health bar height
	 * 
	 */
	public double getHealthHeight();
	
	/**
	 * set health when not equal to maximum
	 * @param value decimal value used to set current health rectangle width
	 * 
	 */
	public void setCurrentHealth(double value);
	
	/**
	 * draw health bar rectangles at certain position
	 * @param gc graphic context used to draw on the window
	 * @param x coordinate x where the rectangle has to be drew
	 * @param y coordinate y where the rectangle has to be drew
	 */
	public void drawRectangle(GraphicsContext gc, double x, double y );
	
}


