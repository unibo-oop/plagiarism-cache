package tq2.interfaces.platform;

import tq2.interfaces.Entity;

/**
 * The Interface Stamina.
 * 
 * @author Francesco Gori
 */
public interface Stamina extends Entity {

	/**
	 * Gets the amount of stamina of this Entity.
	 *
	 * @return the amount of stamina
	 */
	public abstract Integer getStamina();

	/**
	 * Gets the maximum amount of stamina of this Entity.
	 *
	 * @return the maximum amount of stamina
	 */
	public abstract Integer getMaxStamina();

	/**
	 * Sets the amount of stamina of this Entity.
	 *
	 * @param stamina the new amount of stamina
	 */
	public abstract void setStamina(Integer stamina);

	/**
	 * Sets the amount of stamina of this Entity.
	 *
	 * @param stamina the new amount of stamina
	 */
	public abstract void setStamina(Double stamina);

	/**
	 * Sets the maximum amount of stamina of this Entity.
	 *
	 * @param maxStamina the new maximum amount of stamina
	 */
	public abstract void setMaxStamina(Integer maxStamina);

	/**
	 * Adds the stamina.
	 *
	 * @param points the points
	 */
	public abstract void addStamina(Integer points);

	/**
	 * Sums the specified number to the amount of stamina of this Entity.
	 *
	 * @param points the number to sum to the amount of stamina
	 */
	public abstract void addStamina(Double points);

	/**
	 * Sets how many points of stamina the Entity recovers at each tick.
	 *
	 * @param staminaRecover the new value of the stamina recover
	 */
	public abstract void setStaminaRecover(Double staminaRecover);
	
	/**
	 * Resets the maximum amount of stamina to its original value.
	 */
	public abstract void resetMaxStamina();
	
	/**
	 * Reset the speed of the stamina recovery to its original value.
	 */
	public abstract void resetStaminaRecover();
	
}
