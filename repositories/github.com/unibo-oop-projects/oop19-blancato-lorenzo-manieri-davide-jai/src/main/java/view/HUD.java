package view;


import controller.Controller;
import javafx.scene.canvas.GraphicsContext;

public interface HUD extends Controller{
	/**
	 * Update the player health bar and score in the game main view
	 * @param gc
	 * 		The Graphic Context used to draw in the canvas
	 */
	public void update(GraphicsContext gc);
}
