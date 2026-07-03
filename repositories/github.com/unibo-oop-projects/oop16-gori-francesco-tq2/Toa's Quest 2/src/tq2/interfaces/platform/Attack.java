package tq2.interfaces.platform;

import tq2.interfaces.Entity;

/**
 * The Interface Attack is meant for Entities that can perform an attack.
 * 
 * @author Francesco Gori
 */
public interface Attack extends Entity {
	
	/**
	 * Perform an attack.
	 */
	public abstract void attack();

	/**
	 * Returns the attack damage of this Entity.
	 *
	 * @return the attack damage
	 */
	public abstract Integer getAttackDamage();
	
	/**
	 * Checks if the Entity is currently attacking.
	 *
	 * @return the boolean
	 */
	public abstract Boolean isAttacking();

	/**
	 * Sets the attack damage of the Entity.
	 *
	 * @param attackDamage the new attack damage
	 */
	public abstract void setAttackDamage(Integer attackDamage);

	/**
	 * Sets whether the Entity is attacking.
	 *
	 * @param attacking the new attacking
	 */
	public abstract void setAttacking(Boolean attacking);

	/**
	 * Reset the attack damage of the Entity to its initial value.
	 */
	public abstract void resetAttackDamage();
}
