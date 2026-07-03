package tq2.interfaces;

import java.awt.Graphics2D;
import java.util.LinkedList;

/**
 * The Interface Handler.
 * 
 * @author Francesco Gori
 */
public interface Handler {

	/**
	 * Render the Entities inside the Handler.
	 *
	 * @param g the Graphics
	 */
	public abstract void render(Graphics2D g);

	/**
	 * Updates the status of the Entities inside the Handler
	 */
	public abstract void tick();

	/**
	 * Returns the game the Handler is relative to.
	 *
	 * @return the game
	 */
	public abstract Game getGame();

	/**
	 * Gets the layers of Entities currently loaded in the Handler.
	 *
	 * @return the layers
	 */
	public abstract LinkedList<LevelLayer> getLayers();

	/**
	 * Adds the Entity to the specified Layer.
	 *
	 * @param en the Entity
	 * @param i the Layer
	 */
	public abstract void addEntity(Entity en, Integer i);

	/**
	 * Removes the Entity checking in all layers.
	 *
	 * @param en the Entity
	 */
	public abstract void removeEntity(Entity en);

	/**
	 * Gets the layer of the specified Entity.
	 *
	 * @param en the Entity
	 * @return the layer of the Entity
	 */
	public abstract LevelLayer getLayerOf(Entity en);

	/**
	 * Gets the layer at the specified position.
	 *
	 * @param index the index
	 * @return the layer
	 */
	public abstract LevelLayer getLayer(Integer index);

	/**
	 * Gets the scale of the game window.
	 *
	 * @return the game scale
	 */
	public abstract Integer getGameScale();

	/**
	 * Removes all the contents the Handler contains.
	 */
	public abstract void clearLevel();
	
	/**
	 * Checks if the game is paused.
	 *
	 * @return the boolean
	 */
	public abstract Boolean isPaused();

	/**
	 * Sets whether the game is paused.
	 *
	 * @param paused the new paused
	 */
	public abstract void setPaused(Boolean paused);
	
	/**
	 * Either pause or resume the game depending on its status.
	 */
	public abstract void pause();
	
	/**
	 * Sets the specified layer as menu to display when the game is paused. A pause menu should not be necessary to run the game though.
	 * 
	 *@param pauseMenu the LevelLayer to display when the game is paused	 */
	public abstract void setPauseMenu(LevelLayer pauseMenu);

}