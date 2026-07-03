package tq2.interfaces.platform;

import tq2.interfaces.Entity;

/**
 * The Interface Crouch is meant for Entities (mainly characters) that can crouch.
 * 
 * @author Francesco Gori
 */

public interface Crouch extends Entity {
	
	/**
	 * Gets the height of the Entity when crouched.
	 *
	 * @return the height
	 */
	public abstract Integer getCrouchedHeight();
	
	/**
	 * Checks if the Entity is crouching.
	 *
	 * @return whether the Entity is crouched
	 */
	public abstract Boolean isCrouching();
	
	/**
	 * Sets if the the Entity is crouched.
	 *
	 * @param crouching whether the Entity is crouched
	 */
	public abstract void setCrouching(Boolean crouching);
	
	/**
	 * Sets the height of the Entity when crouched.
	 *
	 * @param crouchedHeight the new height
	 */
	public abstract void setCrouchedHeight(Integer crouchedHeight);
	
}
