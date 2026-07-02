package model;

public interface Knockable {
	
	/**
	 * apply damage on health
	 */
	public void hit(double damage);
	
	/**
	 * Set the entity to explode state
	 * @param explode
	 */
	public void setExplode(boolean explode);
	
	/**
	 * Check if entity is exploding
	 * @return
	 */
	public boolean isExploding();

	/**
	 * If exploding state is true call explode method
	 */
	public void checkExplode();
	

	
	/**
	 * Manage explode animation and states
	 */
	public void explode();
	
	/**
	 * Make entity fire a bullet
	 */
	public void fire();

	/**
	 * 
	 * 	@param newDamage
	 */
	public void setDamage(double newDamage);
	
	/**
	 * 
	 * 	@return 
	 */
	public double getDamage();
}
