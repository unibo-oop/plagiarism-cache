package tq2.interfaces.platform;

import tq2.interfaces.Entity;

/**
 * The Interface Walk is meant for objects that can walk
 * 
 * @author Francesco Gori
 */
public interface Walk extends Entity {
	
	/**
	 * Gets the maximum velocity on the X axis for the Entity.
	 *
	 * @return the maximum X velocity
	 */
	public abstract Integer getMaxVelX();
	
	/**
	 * Gets the walking speed of the Entity.
	 *
	 * @return the walking speed
	 */
	public abstract Integer getWalkSpeed();

	/**
	 * Checks if the Entity is walking.
	 *
	 * @return whether the Entity is walking
	 */
	public abstract Boolean isWalking();
	
	/**
	 * Sets the maximum velocity on the X axis for the Entity.
	 *
	 * @param maxVelX the new the maximum X velocity
	 */
	public abstract void setMaxVelX(Integer maxVelX);
	
	/**
	 * Sets the walk speed of this Entity.
	 *
	 * @param walkSpeed the new walk speed
	 */
	public abstract void setWalkSpeed(Integer walkSpeed);
	
	/**
	 * Sets the walking speed of the Entity.
	 *
	 * @param walking the new walking speed
	 */
	public abstract void setWalking(Boolean walking);

	/**
	 * Resets the maximum velocity on the X axis for the Entity.
	 */
	public abstract void resetMaxVelX();
	
	/**
	 * Resets the walk speed of this Entity.
	 */
	public abstract void resetWalkSpeed();
	
}
