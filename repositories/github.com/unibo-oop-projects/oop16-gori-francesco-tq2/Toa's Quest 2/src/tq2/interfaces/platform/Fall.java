package tq2.interfaces.platform;

import tq2.interfaces.Entity;
/**
 * The Interface Fall is meant for Entities that are subject to gravity
 * 
 * @author Francesco Gori
 */

public interface Fall extends Entity {
	
	/**
	 * Checks if the Entity is falling.
	 *
	 * @return whether the Entity is falling
	 */
	public abstract Boolean isFalling();

	/**
	 * Returns the gravity the Entity is subject to.
	 *
	 * @return the gravity
	 */
	public abstract Double getGravity();

	/**
	 * Returns the gravity acceleration the Entity is subject to.
	 *
	 * @return the gravity acceleration
	 */
	public abstract Double getGravityAcceleration();

	/**
	 * Gets the maximum velocity the Entity can achieve on the Y axis.
	 *
	 * @return the maximum Y velocity
	 */
	public abstract Integer getMaxVelY();
	
	/**
	 * Sets whether the Entity is falling.
	 *
	 * @param falling whether the Entity is falling
	 */
	public abstract void setFalling(Boolean falling);

	/**
	 * Sets the gravity the Entity is subject to.
	 *
	 * @param gravity the new gravity
	 */
	public abstract void setGravity(Double gravity);

	/**
	 * Sets the gravity the Entity is subject to.
	 *
	 * @param gravity the new gravity
	 */
	public abstract void setGravity(Integer gravity);

	/**
	 * Sets the gravity acceleration of the Entity.
	 *
	 * @param gravityAcceleration the new gravity acceleration
	 */
	public abstract void setGravityAcceleration(Double gravityAcceleration);

	/**
	 * Sets the gravity acceleration of the Entity.
	 *
	 * @param gravityAcceleration the new gravity acceleration
	 */
	public abstract void setGravityAcceleration(Integer gravityAcceleration);
	
	/**
	 * Sets the maximum velocity the Entity can achieve on the Y axis.
	 *
	 * @param maxVelY the new maximum Y velocity
	 */	
	public abstract void setMaxVelY(Integer maxVelY);
	
	/**
	 * Reset the gravity acceleration of the Entity to its starting value.
	 */
	public abstract void resetGravityAcceleration();
	
	/**
	 * Reset the maximum velocity the Entity can achieve on the Y axis to its starting value.
	 */
	public abstract void resetMaxVelY();
}
