package model;



public interface LivingEntity extends BorderGameObject, MovableXY, Knockable {
	/**
	 * 
	 * @return
	 * 		Return true if is removable
	 */
	public boolean getRemovable();
	
	/**
	 * Set removable or not the living entity
	 * @param remove
	 */
	public void setRemovable(boolean remove);
	
	/**
	 * Set fire speed
	 * @param newSpeed
	 * 		Set newSpeed long value > 0
	 */
	public void setFireSpeed(long newSpeed);

	/**
	 * 
	 * @return
	 * 		Return the fire speed
	 */
	public long getFireSpeed();
	
	/**
	 * 
	 * @return
	 * 		Return this entity health
	 */
	public double getHealth();
	
	/**
	 * 
	 * @return
	 * 		Return this entity max health
	 */
	public double getMaxHealth();
	
	/**
	 * Set entity health, if new health is bigger then max health, max health is update with new health
	 * @param newHealth
	 */
	public void setHealth(double newHealth);
	
	/**
	 * Set new value for damage
	 * @param
	 * 		newDamage is the value to set for this entity
	 */
	public void setDamage(double newDamage);
	
	/**
	 * Return the entity damage 
	 * @return 	
	 * 		Damage
	 */
	public double getDamage();
	


}


