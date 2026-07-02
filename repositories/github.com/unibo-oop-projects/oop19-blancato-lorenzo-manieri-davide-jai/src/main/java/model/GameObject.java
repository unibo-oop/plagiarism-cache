package model;


import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public interface GameObject extends Entity, Movable{

	/**
	 * 
	 * @return
	 * 		Return true if gameObject is visible
	 */
	public boolean isVisible();
	
	/**
	 * Set game object visible
	 * @param visible
	 */
	public void setVisible(boolean visible);
	
	/**
	 * Get the game object rectangle
	 * @return
	 */
	public Rectangle2D getBounds();
	
	/**
	 * Check if this game object has intersection with an other rectangle
	 * @param r
	 * 		The other rectangle to check collision with
	 * @return
	 */
	public boolean checkCollision(Rectangle2D r);
	
	/**
	 * Set the game object image
	 * @param img
	 */
	public void setImage(Image img);
	
	/**
	 * Draw the game object
	 * @param gc	
	 * 		The graphic context which has to draw this game object
	 */
	public void	draw(GraphicsContext gc);
	
	/**
	 * Set new position of this game object
	 * @param deltaX
	 * @param deltaY
	 */
	public void move(double deltaX, double deltaY);
	
	/**
	 * Set new x position of this game object
	 * @param deltaX
	 */
	public void moveX(double deltaX);
	
	/**
	 * Set new y position of this game object
	 * @param deltaY
	 */
	public void moveY(double deltaY);
	
	
	
	
}
