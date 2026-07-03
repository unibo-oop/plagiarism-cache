package tq2.interfaces.platform;

import tq2.interfaces.Entity;
/**
 * The Interface Jump is meant for Entities that can perform jumps
 * 
 * @author Francesco Gori
 */

public interface Jump extends Entity {
	
	/**
	 * Makes this entity perform a jump.
	 */
	public abstract void jump();

	/**
	 * Checks if he Entity is jumping.
	 *
	 * @return the boolean
	 */
	public abstract Boolean isJumping();
	
	/**
	 * Gets the maximum number of mid-air jumps the Entity can perform.
	 *
	 * @return the maximum number of jumps
	 */
	public abstract Integer getMaxJumps();

	/**
	 * Gets the number of mid-air jumps the Entity has performed.
	 *
	 * @return the jump counter
	 */
	public abstract Integer getJumpCounter();
	
	/**
	 * Gets the strength of a jump of the Entity.
	 *
	 * @return the jump strength
	 */
	public abstract Double getJumpStrength();

	/**
	 * Sets the maximum number of jumps the Entity can perform before touching the ground again.
	 *
	 * @param maxJumps the new maximum number of mid-air jumps
	 */
	public abstract void setMaxJumps(Integer maxJumps);
	
	/**
	 * Sets the jump counter to the specified number.
	 *
	 * @param jumpCounter the new value of the jump counter
	 */
	public abstract void setJumpCounter(Integer jumpCounter);
	
	/**
	 * Sets the jump strength.
	 *
	 * @param jumpStrength the new jump strength
	 */
	public abstract void setJumpStrength(Double jumpStrength);

	/**
	 * Sets the jump strength.
	 *
	 * @param jumpStrength the new jump strength
	 */
	public abstract void setJumpStrength(Integer jumpStrength);
	
	/**
	 * Sets whether the Entity is jumping.
	 *
	 * @param jumping the new jumping
	 */
	public abstract void setJumping(Boolean jumping);
	
	/**
	 * Resets the strength of the Entity's jumps to the starting value.
	 */
	public abstract void resetJumpStrength();
	
	/**
	 * Reset the maximum number of jumps the Entity can perform before touching the ground again.
	 */
	public abstract void resetMaxJumps();
	
	
}
