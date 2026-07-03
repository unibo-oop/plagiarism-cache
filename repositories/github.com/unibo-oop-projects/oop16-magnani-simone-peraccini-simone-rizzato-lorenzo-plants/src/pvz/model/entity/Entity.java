package pvz.model.entity;

import javafx.geometry.Rectangle2D;

/**
 * This interface describes a generic game entity.<br>
 * A game entity is any object which has an active impact over the course of the
 * game. It has a position and is capable of colliding with other entities.
 */
public interface Entity {

	/**
	 * Returns the horizontal position of this <code>Entity</code> relative to
	 * the top-left corner of the game field.
	 * 
	 * @return x position
	 */
	double getX();

	/**
	 * Returns the vertical position of this <code>Entity</code> relative to the
	 * top-left corner of the game field.
	 * 
	 * @return y position
	 */

	double getY();

	/**
	 * Tells the entity to update its status, moving forward by one tick.
	 */
	void update();

	/**
	 * Returns <code>true</code> if this entity is colliding with the
	 * <code>other</code> entity.
	 * 
	 * @param box
	 *            other entity's box
	 * @return true if collision is happening
	 */
	boolean collidesWith(Rectangle2D box);

	/**
	 * Returns <code>true</code> if the game state is such that the entity
	 * should be no longer active.
	 * 
	 * @return true if this should be removed
	 */
	boolean shouldBeRemoved();

}