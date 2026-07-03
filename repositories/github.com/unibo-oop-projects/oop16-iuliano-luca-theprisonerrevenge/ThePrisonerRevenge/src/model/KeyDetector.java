package model;

import java.awt.event.KeyEvent;

/**
 * This interface represents the contract to capture keyboard events pressed 
 * by the user.
 * 
 * @author Luca
 *
 */
public interface KeyDetector {
	/**
	 * Checks the key Pressed passed in argument to do a specific action.
	 * 
	 * @param key
	 *            a KeyEvent.
	 */
	void keyPressed(KeyEvent key);

	/**
	 * Checks the key Released passed in argument to do a specific action.
	 * 
	 * @param key
	 *            a KeyEvent.
	 */
	void keyReleased(KeyEvent key);
}
