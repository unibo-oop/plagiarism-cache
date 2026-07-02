package controller;

import javafx.scene.canvas.GraphicsContext;

public interface Controller {

	/**
	 * method used to draw object on the window and updating their movement and their state
	 * 
	 * @param gc  graphic context used to draw on the window
	 */
	public void update(GraphicsContext gc);
}
