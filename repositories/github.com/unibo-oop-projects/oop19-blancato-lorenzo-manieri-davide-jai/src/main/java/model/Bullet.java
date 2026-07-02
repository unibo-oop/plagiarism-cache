package model;


import model.BulletImpl.Target;

public interface Bullet extends GameObject {
	
	/**
	 * 
	 * 	@return
	 * 		Return bullet damage
	 */
	public double getDamage();
	
	/**
	 * 
	 *	 @param newDamage
	 *		Set bullet damage
	 */
	public void setDamage(int newDamage);
	
	/**
	 * 
	 * 	@return	
	 * 		Return bullet target
	 */
	public Target getTarget();
	
	/**
	 * Set the bullet target
	 * 	@param target
	 */
	public void setTarget(Target target);
	
	/**
	 * Set the new bullet position
	 * @return
	 * 		Return true if the bullet is within the window,
	 * 		else return false
	 */
	public boolean updateMovement();
}
