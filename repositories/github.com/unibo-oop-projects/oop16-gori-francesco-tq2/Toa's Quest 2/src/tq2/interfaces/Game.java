package tq2.interfaces;

import java.awt.Rectangle;

/**
 * The Interface Game. It contains the basic elements for a game canvas.
 * 
 * @author Francesco Gori
 */
public interface Game {

	/**
	 * Gets the width of the game window.
	 *
	 * @return the width
	 */
	public abstract int getGameWidth();

	/**
	 * Gets the height of the game window.
	 *
	 * @return the height
	 */
	public abstract int getGameHeight();

	/**
	 * Gets the scale of the game window.
	 *
	 * @return the scale
	 */
	public abstract int getScale();

	/**
	 * Gets the area of the game window.
	 *
	 * @return the window bounds
	 */
	public abstract Rectangle getWindowBounds(LevelLayer layer);

	/**
	 * Gets the camera relative to the game.
	 *
	 * @return the camera
	 */
	public abstract Camera getCamera();
	
	/**
	 * Gets the handler relative to the game.
	 *
	 * @return the handler
	 */
	public abstract Handler getHandler();

	/**
	 * Gets the level currently loaded.
	 *
	 * @return the current level
	 */
	public abstract Level getCurrentLevel();
	
	/**
	 * Gets the index of the current level.
	 *
	 * @return the current level index
	 */
	public abstract Integer getCurrentLevelIndex();

	/**
	 * Renders the Entities in the game window.
	 */
	public abstract void render();

	/**
	 * Updates the status of the game.
	 */
	public abstract void tick();

	/**
	 * Jumps to the following level, if present
	 */
	public abstract void nextLevel();

	/**
	 * Jump to the desired level, if present.
	 *
	 * @param level the level
	 */
	public abstract void jumpToLevel(Integer level);
	
	/**
	 * Sets the size of the game window.
	 *
	 * @param width the desired width
	 * @param height the desired height
	 */
	public abstract void setSize(Integer width, Integer height);
}