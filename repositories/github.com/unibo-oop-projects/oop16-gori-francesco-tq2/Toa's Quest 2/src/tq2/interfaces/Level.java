package tq2.interfaces;

import java.awt.Cursor;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;

import tq2.implementations.graphics.Spritesheet;

/**
 * The Interface Level.
 * 
 * @author Francesco Gori
 */
public interface Level {

	/**
	 * Gets the images to use to load the layers of the level.
	 *
	 * @return the list of images
	 */
	public abstract LinkedList<BufferedImage> getLayerImages();

	/**
	 * Gets the sprite sheets currently loaded into the level.
	 *
	 * @return the sprite sheets
	 */
	public abstract HashMap<String, Spritesheet> getSpritesheets();

	/**
	 * Updates the status of the level, checking conditions specific to it
	 * (for example when to jump to the next level).
	 */
	public abstract void tick();
	
	/**
	 * Gets the cursor relative to this level. By default, this method should return null (default cursor).
	 *
	 * @return the cursor
	 */
	public abstract Cursor getCursor();
	
	/**
	 * Gets the Handler relative to this level.
	 *
	 * @return the Handler
	 */
	public abstract Handler getHandler();

	/**
	 * Gets the width of the level.
	 *
	 * @return the width
	 */
	public abstract Integer getWidth();

	/**
	 * Gets the height of the level.
	 *
	 * @return the height
	 */
	public abstract Integer getHeight();

	/**
	 * Returns an object containing the controls relative to this level.
	 *
	 * @return the controls
	 */
	public abstract Controls getControls();

	/**
	 * Imports the data relative to the level in the Handler.
	 */
	public abstract void loadLevel();

	/**
	 * Removes the contents of the level.
	 */
	public abstract void clearLevel();

}