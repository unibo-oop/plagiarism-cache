package controller;


import javafx.scene.canvas.GraphicsContext;
import model.PowerUp;

/**
 *	PowerUp managment class
 */
public interface PowerUpManager extends Controller {
	
	/**
	 * Add the specified powerUp to the powerUp manager list
	 * @param p
	 */
	public void addPowerUp(PowerUp p);
	
	/**
	 * Remove the specified powerUp from the list
	 * @param p
	 */
	public void removePowerUp(PowerUp p);
	
	/**
	 * Update movement and draw all powerUp in the list, managing also the collision with the player
	 * @param
	 */
	public void update(GraphicsContext gc) ;
	

}
