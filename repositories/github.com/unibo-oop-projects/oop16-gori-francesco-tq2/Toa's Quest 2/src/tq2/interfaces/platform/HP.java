package tq2.interfaces.platform;

import tq2.interfaces.Entity;

/**
 * The Interface HP is meant for Entities (mainly characters) that have Health Points
 * 
 * @author Francesco Gori
 */
public interface HP extends Entity {
	
	/**
	 * Checks if the Entity is being hit.
	 *
	 * @return the boolean
	 */
	public abstract Boolean isHit();

	/**
	 * Gets the current amount of Health Points of the Entity.
	 *
	 * @return the amount of HP
	 */
	public abstract Integer getHp();

	/**
	 * Gets the maximum possible amount of Health Points of the Entity.
	 *
	 * @return the maximum amount of HP
	 */
	public abstract Integer getMaxHp();
	
	/**
	 * Sets whether the Entity is being hit.
	 *
	 * @param hit the new hit
	 */
	public abstract void setHit(Boolean hit);
	
	/**
	 * Sets the the amount of Health Points of the Entity to the specified value.
	 *
	 * @param hp the new amount of Health Points
	 */
	public abstract void setHp(Integer hp);

	/**
	 * Sets the maximum possible amount of Health Points of the Entity.
	 *
	 * @param maxHp the new maximum amount of HP
	 */
	public abstract void setMaxHp(Integer maxHp);

	/**
	 * Sums the specified number to the amount of Health Points of this Entity
	 *
	 * @param points the points
	 */
	public abstract void addHp(Integer points);

	/**
	 * Resets the maximum possible amount of Health Points of the Entity.
	 */
	public abstract void resetMaxHp();
}
