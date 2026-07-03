package tq2.interfaces.platform;

import tq2.interfaces.Entity;

/**
 * The Interface Shoot is meant for Entities that can shoot objects.
 * 
 * @author Francesco Gori
 */
public interface Shoot extends Entity {

	/**
	 * Makes this Entity shoot.
	 */
	public abstract void shoot();

	/**
	 * Checks if the Entity is shooting.
	 *
	 * @return whether the Entity is shooting
	 */
	public abstract Boolean isShooting();

	/**
	 * Sets if the Entity is shooting.
	 *
	 * @param shooting whether the Entity is shooting
	 */
	public abstract void setShooting(Boolean shooting);

}