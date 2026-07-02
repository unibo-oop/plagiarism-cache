package model;

import controller.InputController.Direction;

/**
 * 	interface for playerImpl
 * 
 *
 */

public interface Player extends LivingEntity{
	
	/**
	 * 	set if player can fire 
	 * 	@param flag
	 */
	public void setCanFire(boolean flag);
	
	/**
	 * 
	 *	 @return
	 *	return true if player can fire
	 */
	public boolean canFire();
	
	
	/**
	 * 
	 * @return
	 * 		return the collision damage
	 */
	public double getCollisionDamage();
	
	
	/**
	 * 
	 * 	@param blink
	 * 		set if player is blinking
	 */
	public void setBlinking(boolean blink);
	
	
	/**
	 *	 @return
	 *		return true if player is blinking
	 */		
	public boolean isBlinking();
	



	/** 
	 * 
	 * 	@param s
	 */
	public void setShield(boolean s);

	/**
	 * 
	 *	 @return
	 *		return true if the player has shield
	 */
	public boolean getShield();
		

	
	


	
	
	/**
	 *  
	 */
	public void fire();
	
	
	/** 
	 * 	set player health less the damage
	 * 	@param damage
	 * 	
	 */
	public void hit(double damage);


	


	
	/**
	 * 	set direction variables to set movement
	 * 	@param d
	 */
	public void setDirection(Direction d);
		
	/**
	 * 	set direction variables to false to stop player movement 
	 * 	@param d
	 */
	public void deleteDirection(Direction d);


}
